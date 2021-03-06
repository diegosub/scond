package br.com.cdtec.controller.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.security.entity.Usuario;
import br.com.cdtec.security.jwt.JwtAuthenticationRequest;
import br.com.cdtec.security.jwt.JwtTokenUtil;
import br.com.cdtec.security.model.CurrentUser;
import br.com.cdtec.security.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value = "/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getDsLogin(), 
						authenticationRequest.getDsPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getDsLogin());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final Usuario usuario = usuarioService.pesquisarPorLogin(authenticationRequest.getDsLogin());
		usuario.setDsPassword(null);
		
		return ResponseEntity.ok(new CurrentUser(token, usuario));
	}
	
	@PostMapping(value = "/api/refresh")
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		String login = jwtTokenUtil.getLoginFromToken(token);
		final Usuario usuario = usuarioService.pesquisarPorLogin(login);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, usuario));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
	
	@PostMapping(value = "/api/validate")
	public ResponseEntity<?> validateAuthenticationToken(HttpServletRequest request, @RequestBody String dsLogin) {
		
		String token = request.getHeader("Authorization");				
		final UserDetails userDetails = userDetailsService.loadUserByUsername(dsLogin);		
		
		if(jwtTokenUtil.validateToken(token, userDetails)) {			
			return ResponseEntity.ok("Token Valido!");
		} else {
			return ResponseEntity.badRequest().body(null);
		}
		
	}
}
