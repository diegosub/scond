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

import br.com.cdtec.entity.Segmento;
import br.com.cdtec.service.SegmentoService;

@RestController
@RequestMapping("/api/segmento")
@CrossOrigin(origins = "*")
public class SegmentoController extends CDTecController<Segmento, BigInteger, SegmentoService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void completarInserir(Segmento entity, HttpServletRequest request) {
		entity.setIdUsuario(getUsuarioFromRequest(request).getIdUsuario());
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}

	@Override
	protected void completarAlterar(Segmento entity, HttpServletRequest request) {
		entity.setDtManutencao(new Date());
	}

	@Override
	protected void validarInserir(Segmento entity, BindingResult result) {
		if (entity.getDsSegmento() == null || entity.getDsSegmento().trim().equals("")) {
			result.addError(new ObjectError("Segmento", "Descrição obrigatória."));
			return;
		}
		
		if (entity.getIdCategoria() == null || entity.getIdCategoria().longValue() <= 0) {
			result.addError(new ObjectError("Segmento", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void validarAlterar(Segmento entity, BindingResult result) {
		if (entity.getIdSegmento() == null) {
			result.addError(new ObjectError("Segmento", "Código informado"));
			return;
		}

		if (entity.getDsSegmento() == null || entity.getDsSegmento().trim().equals("")) {
			result.addError(new ObjectError("Segmento", "Descrição obrigatória."));
			return;
		}
		
		if (entity.getIdCategoria() == null || entity.getIdCategoria().longValue() <= 0) {
			result.addError(new ObjectError("Segmento", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void atualizarStatusEntidade(Segmento entity, String status) {
		entity.setFgAtivo(status);
		entity.setDtManutencao(new Date());
	}

	@Override
	protected Sort sortField() {
		return new Sort(Direction.ASC, getService().getFieldSort());
	}
}
