package br.com.cdtec.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.dao.repository.EstabelecimentoRepository;
import br.com.cdtec.dao.specifications.EstabelecimentoSpecifications;
import br.com.cdtec.entity.Estabelecimento;


@Service
public class EstabelecimentoService extends CrudService<Estabelecimento, BigInteger, EstabelecimentoRepository> {

	private static final long serialVersionUID = 1L;	
	private final String fieldSort = "dsEstabelecimento";

	@Override
	public void validarInserir(Estabelecimento entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsEstabelecimento());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um estabelecimento cadastrado com esta descrição.");
		}
	}
	
	@Override
	public void validarAlterar(Estabelecimento entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsEstabelecimento(), entity.getIdEstabelecimento());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um estabelecimento cadastrado com esta descrição.");
		}
	}

	public String getFieldSort() {
		return fieldSort;
	}
	
	@Override
	public List<Estabelecimento> implementarPesquisar(Estabelecimento estabelecimento, Sort sort) throws Exception {		
		return getRepository().findAll(Specification.where(EstabelecimentoSpecifications.dsEstabelecimentoLike(estabelecimento.getDsEstabelecimento())
										              .and(EstabelecimentoSpecifications.fgAtivoIgual(estabelecimento.getFgAtivo()))), sort);
	}
	
}