package br.com.cdtec.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.dao.repository.DespesaRepository;
import br.com.cdtec.dao.specifications.DespesaSpecifications;
import br.com.cdtec.entity.Despesa;


@Service
public class DespesaService extends CrudService<Despesa, BigInteger, DespesaRepository> {

	private static final long serialVersionUID = 1L;	
	private final String fieldSort = "idDespesa";

	public String getFieldSort() {
		return fieldSort;
	}
	
	@Override
	public List<Despesa> implementarPesquisar(Despesa despesa, Sort sort) throws Exception {		
		return getRepository().findAll(Specification.where(DespesaSpecifications.idIgual(despesa.getIdDespesa()))
																				 .and(DespesaSpecifications.leftJoinEstabelecimento())
																				 .and(DespesaSpecifications.leftJoinSegmento())
																				 .and(DespesaSpecifications.fgAtivoIgual(despesa.getFgAtivo()))
																				 .and(DespesaSpecifications.betweenDatas(despesa.getDtCompra(), despesa.getDtCompra())), sort);				
	}
	
}