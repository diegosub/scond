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

import br.com.cdtec.dto.CartaoDTO;
import br.com.cdtec.entity.Cartao;
import br.com.cdtec.service.CartaoService;

@RestController
@RequestMapping("/api/cartao")
@CrossOrigin(origins = "*")
public class CartaoController extends CDTecController<Cartao, CartaoDTO, BigInteger, CartaoService> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void completarInserir(Cartao entity, HttpServletRequest request) {
		entity.setIdUsuario(getUsuarioFromRequest(request).getIdUsuario());
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}

	@Override
	protected void completarAlterar(Cartao entity, HttpServletRequest request) {
		entity.setDtManutencao(new Date());
	}

	@Override
	protected void validarInserir(Cartao entity, BindingResult result) {
		if (entity.getDsCartao() == null || entity.getDsCartao().trim().equals("")) {
			result.addError(new ObjectError("Cartao", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void validarAlterar(Cartao entity, BindingResult result) {
		if (entity.getIdCartao() == null) {
			result.addError(new ObjectError("Cartao", "Código informado"));
			return;
		}

		if (entity.getDsCartao() == null || entity.getDsCartao().trim().equals("")) {
			result.addError(new ObjectError("Cartao", "Descrição obrigatória."));
			return;
		}
	}

	@Override
	protected void atualizarStatusEntidade(Cartao entity, String status) {
		entity.setFgAtivo(status);
		entity.setDtManutencao(new Date());
	}

	@Override
	protected Sort sortField() {
		return new Sort(Direction.ASC, getService().getFieldSort());
	}
}
