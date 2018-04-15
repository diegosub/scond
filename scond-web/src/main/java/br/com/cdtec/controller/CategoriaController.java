package br.com.cdtec.controller;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.entity.Categoria;
import br.com.cdtec.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends CDTecController<Categoria, BigInteger, CategoriaService> {

	private static final long serialVersionUID = 1L;	
	
	@Override
	protected void completarInserir(Categoria entity) {
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}
	
	@Override
	protected void atualizarStatusEntidade(Categoria entity, String status) {
		entity.setFgAtivo(status);
		entity.setDtManutencao(new Date());
	}
	
	@Override
	protected Sort sortField() {
		return new Sort(Direction.ASC, getService().getFieldSort());
	}
}
