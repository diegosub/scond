package br.com.cdtec.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.entity.Categoria;
import br.com.cdtec.repository.CategoriaRepository;


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

	public String getFieldSort() {
		return fieldSort;
	}
	
}