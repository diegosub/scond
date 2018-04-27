package br.com.cdtec.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.dao.repository.CartaoRepository;
import br.com.cdtec.dao.specifications.CartaoSpecifications;
import br.com.cdtec.entity.Cartao;


@Service
public class CartaoService extends CrudService<Cartao, BigInteger, CartaoRepository> {

	private static final long serialVersionUID = 1L;	
	private final String fieldSort = "dsCartao";

	@Override
	public void validarInserir(Cartao entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsCartao());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um cartão cadastrado com esta descrição.");
		}
	}
	
	@Override
	public void validarAlterar(Cartao entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsCartao(), entity.getIdCartao());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um cartão cadastrado com esta descrição.");
		}
	}

	public String getFieldSort() {
		return fieldSort;
	}
	
	@Override
	public List<Cartao> implementarPesquisar(Cartao cartao, Sort sort) throws Exception {		
		return getRepository().findAll(Specification.where(CartaoSpecifications.dsCartaoLike(cartao.getDsCartao())
										              .and(CartaoSpecifications.fgAtivoIgual(cartao.getFgAtivo()))), sort);
	}
	
}