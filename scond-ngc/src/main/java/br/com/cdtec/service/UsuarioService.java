package br.com.cdtec.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.entity.Usuario;
import br.com.cdtec.repository.UsuarioRepository;


@Service
public class UsuarioService extends CrudService<Usuario, BigInteger, UsuarioRepository> {

	private static final long serialVersionUID = 1L;

	public Usuario pesquisarPorLogin(String dsLogin) {
		Usuario usuario = getRepository().pesquisarPorLogin(dsLogin);
		return usuario;
	}
	
}