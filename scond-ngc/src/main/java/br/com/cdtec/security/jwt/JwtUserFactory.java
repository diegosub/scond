package br.com.cdtec.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.cdtec.security.entity.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {}
	
	public static JwtUser create(Usuario usuario) {
		
		return new JwtUser(usuario.getIdUsuario(), usuario.getDsLogin(), usuario.getDsPassword(), mapToGranteAuthorities(usuario.getDsPerfil()));
		
	}
	
	private static List<GrantedAuthority> mapToGranteAuthorities(String dsPerfil) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();		
		authorities.add(new SimpleGrantedAuthority(dsPerfil));
		return authorities;
		
	}
	
}
