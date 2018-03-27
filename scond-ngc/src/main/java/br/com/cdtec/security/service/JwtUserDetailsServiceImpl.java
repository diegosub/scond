package br.com.cdtec.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cdtec.security.entity.Usuario;
import br.com.cdtec.security.jwt.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String dsLogin) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.pesquisarPorLogin(dsLogin);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com login: ", dsLogin));
		} else {
			return JwtUserFactory.create(usuario);
		}
		
	}

	
	
}
