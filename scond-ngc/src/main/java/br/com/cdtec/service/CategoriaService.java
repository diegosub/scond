package br.com.cdtec.service;

import java.math.BigInteger;
import java.util.List;

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
	public List<Categoria> implementarPesquisar(Categoria categoria, Sort sort) throws Exception {		
		return getRepository().findAll(Specification.where(CategoriaSpecifications.dsCategoriaLike(categoria.getDsCategoria())
										              .and(CategoriaSpecifications.fgAtivoIgual(categoria.getFgAtivo()))), sort);
	}
	
}