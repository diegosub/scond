package br.com.cdtec.controller;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.dto.CategoriaDTO;
import br.com.cdtec.entity.Categoria;
import br.com.cdtec.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends CDTecController<Categoria, CategoriaDTO, BigInteger, CategoriaService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void completarInserir(Categoria entity, HttpServletRequest request) {
		entity.setIdUsuario(getUsuarioFromRequest(request).getIdUsuario());
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}

	@Override
	protected void completarAlterar(Categoria entity, HttpServletRequest request) {
		entity.setDtManutencao(new Date());
	}

	@Override
	protected void validarInserir(Categoria entity, BindingResult result) {
		if (entity.getDsCategoria() == null || entity.getDsCategoria().trim().equals("")) {
			result.addError(new ObjectError("Categoria", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void validarAlterar(Categoria entity, BindingResult result) {
		if (entity.getIdCategoria() == null) {
			result.addError(new ObjectError("Categoria", "Código informado"));
			return;
		}

		if (entity.getDsCategoria() == null || entity.getDsCategoria().trim().equals("")) {
			result.addError(new ObjectError("Categoria", "Descrição obrigatória."));
			return;
		}
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
