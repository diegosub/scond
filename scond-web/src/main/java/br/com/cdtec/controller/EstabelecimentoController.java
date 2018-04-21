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

import br.com.cdtec.entity.Estabelecimento;
import br.com.cdtec.service.EstabelecimentoService;

@RestController
@RequestMapping("/api/estabelecimento")
@CrossOrigin(origins = "*")
public class EstabelecimentoController extends CDTecController<Estabelecimento, BigInteger, EstabelecimentoService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void completarInserir(Estabelecimento entity, HttpServletRequest request) {
		entity.setIdUsuario(getUsuarioFromRequest(request).getIdUsuario());
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}

	@Override
	protected void completarAlterar(Estabelecimento entity, HttpServletRequest request) {
		entity.setDtManutencao(new Date());
	}

	@Override
	protected void validarInserir(Estabelecimento entity, BindingResult result) {
		if (entity.getDsEstabelecimento() == null || entity.getDsEstabelecimento().trim().equals("")) {
			result.addError(new ObjectError("Estabelecimento", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void validarAlterar(Estabelecimento entity, BindingResult result) {
		if (entity.getIdEstabelecimento() == null) {
			result.addError(new ObjectError("Estabelecimento", "Código informado"));
			return;
		}

		if (entity.getDsEstabelecimento() == null || entity.getDsEstabelecimento().trim().equals("")) {
			result.addError(new ObjectError("Estabelecimento", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void atualizarStatusEntidade(Estabelecimento entity, String status) {
		entity.setFgAtivo(status);
		entity.setDtManutencao(new Date());
	}

	@Override
	protected Sort sortField() {
		return new Sort(Direction.ASC, getService().getFieldSort());
	}
}
