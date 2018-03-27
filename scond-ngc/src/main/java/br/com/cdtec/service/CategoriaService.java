package br.com.cdtec.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.entity.Categoria;
import br.com.cdtec.repository.CategoriaRepository;


@Service
public class CategoriaService extends CrudService<Categoria, BigInteger, CategoriaRepository> {

	private static final long serialVersionUID = 1L;

	public Categoria pesquisarPorNome(String dsCategoria) {		
		Categoria categoria = getRepository().pesquisarPorDescricao(dsCategoria);
		return categoria;
	}
	
	public Long getTotalCategoria() {
		return getRepository().count();
	}	

}