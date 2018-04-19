package br.com.cdtec.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.dao.repository.SegmentoRepository;
import br.com.cdtec.dao.specifications.SegmentoSpecifications;
import br.com.cdtec.entity.Segmento;


@Service
public class SegmentoService extends CrudService<Segmento, BigInteger, SegmentoRepository> {

	private static final long serialVersionUID = 1L;	
	private final String fieldSort = "dsSegmento";

	@Override
	public void validarInserir(Segmento entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsSegmento());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um segmento cadastrado com esta descrição.");
		}
	}
	
	@Override
	public void validarAlterar(Segmento entity) throws Exception {
		Integer quantidade = getRepository().quantidadePorDescricao(entity.getDsSegmento(), entity.getIdSegmento());
		
		if(quantidade != null
				&& quantidade > 0) {
			throw new Exception("Já existe um segmento cadastrado com esta descrição.");
		}
	}

	public String getFieldSort() {
		return fieldSort;
	}
	
	@Override
	public List<Segmento> implementarPesquisar(Segmento segmento, Sort sort) throws Exception {		
		return getRepository().findAll(Specification.where(SegmentoSpecifications.dsSegmentoLike(segmento.getDsSegmento())
													  .and (SegmentoSpecifications.idCategoriaIgual(segmento.getIdCategoria()))
										              .and(SegmentoSpecifications.fgAtivoIgual(segmento.getFgAtivo()))), sort);
	}
	
}