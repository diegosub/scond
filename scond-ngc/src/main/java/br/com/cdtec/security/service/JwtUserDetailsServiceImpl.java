package br.com.cdtec.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cdtec.entity.Usuario;
import br.com.cdtec.jwt.JwtUserFactory;
import br.com.cdtec.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String dsLogin) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.pesquisarPorLogin(dsLogin);
		
		if(usuario != null) {
			throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com login: ", dsLogin));
		} else {
			return JwtUserFactory.create(usuario);
		}
		
	}

	
	
}
