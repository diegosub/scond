package br.com.cdtec.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String CLAIM_KEY_LOGIN = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_EXPIRED = "exp";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	
	public String generateToken(UserDetails userDatails) {
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put(CLAIM_KEY_LOGIN, userDatails.getUsername());
		
		final Date createDate = new Date();
		claims.put(CLAIM_KEY_CREATED, createDate);
		
		return doGenerateToken(claims);
		
	}
	
	public String refreshToken(String token) {
		
		String refreshedToken;
		
		try {
			
			final Claims claims = this.getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = this.doGenerateToken(claims);						
		} catch (Exception e) {
			refreshedToken = null;
		}
		
		return refreshedToken;		
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		JwtUser user = (JwtUser) userDetails;
		final String login = this.getLoginFromToken(token);
		return (login.equals(user.getUsername()) && !isTokenExpired(token));
		
	}
	
	public Boolean conTokenBeRefreshed(String token) {
		
		return (!isTokenExpired(token));
		
	}
	
	public String getLoginFromToken(String token) {

		String login;

		try {
			final Claims claims = this.getClaimsFromToken(token);
			login = claims.getSubject();
		} catch (Exception e) {
			login = null;
		}

		return login;
	}
	
	public Date getExpirationDateFromTokem(String token) {
		
		Date expiration;
		
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {

		Claims claims;
		
		try {
			claims = Jwts.parser()
						.setSigningKey(secret)
						.parseClaimsJws(token)
						.getBody();
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}
	
	private Boolean isTokenExpired(String token) {
		
		final Date expiration = getExpirationDateFromTokem(token);
		return expiration.before(new Date());
		
	}
	
	private String doGenerateToken(Map<String, Object> claims) {
		
		final Date createdDate = (Date) claims.get(CLAIM_KEY_CREATED);
		final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
				
	}
}
