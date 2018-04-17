package br.com.cdtec.service;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.dao.repository.CategoriaRepository;
import br.com.cdtec.dao.specifications.CategoriaSpecifications;
import br.com.cdtec.entity.Categoria;


@Service
public class CategoriaService extends CrudService<Categoria, BigInteger, CategoriaRepository> {

	private static final long serialVersionUID = 1L;	
	private final String fieldSort = "dsCategoria";

	@Override
	public void validarInserir(Categoria entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsCategoria());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe uma categoria cadastrada com esta descrição.");
		}
	}
	
	@Override
	public void validarAlterar(Categoria entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsCategoria(), entity.getIdCategoria());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe uma categoria cadastrada com esta descrição.");
		}
	}

	public String getFieldSort() {
		return fieldSort;
	}
	
	@Override
	public Page<Categoria> implementarPesquisar(int page, int count, Categoria categoria, Sort sort) throws Exception {
		Pageable pages = PageRequest.of(page, count, sort);	
		
		categoria.setDsCategoria(categoria.getDsCategoria().equals("uniformed") ? null : categoria.getDsCategoria());
		categoria.setFgAtivo(categoria.getFgAtivo().equals("T") ? null : categoria.getFgAtivo());
		
		return getRepository().findAll(Specification.where(CategoriaSpecifications.dsCategoriaLike(categoria.getDsCategoria())
										              .and(CategoriaSpecifications.fgAtivoIgual(categoria.getFgAtivo()))), pages);
	}
	
}