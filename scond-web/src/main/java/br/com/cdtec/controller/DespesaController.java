package br.com.cdtec.controller;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.dto.DespesaDTO;
import br.com.cdtec.entity.Despesa;
import br.com.cdtec.service.DespesaService;

@RestController
@RequestMapping("/api/despesa")
@CrossOrigin(origins = "*")
public class DespesaController extends CDTecController<Despesa, DespesaDTO, BigInteger, DespesaService> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void completarInserir(Despesa entity, HttpServletRequest request) {
		
		if(entity.getFgCartao().equals("N")) {
			entity.setVlMesCartao(null);
			entity.setIdCartao(null);
		}
		
		entity.setIdUsuario(getUsuarioFromRequest(request).getIdUsuario());
		entity.setDtCadastro(new Date());
		entity.setDtManutencao(new Date());
		entity.setFgAtivo("S");
		
		//SETAR DATA REFERENCIA
		GregorianCalendar gc = new GregorianCalendar();
		
		//SE FOR COMPRA CARTAO E MES CARTAO ESCOLHIDO DIFERENTE DO MES ATUAL
		if(entity.getFgCartao().equals("S")
				&& entity.getVlMesCartao().intValue() != (gc.get(Calendar.MONTH) + 1)) {
					
			gc.set(Calendar.DAY_OF_MONTH, 1);
			gc.set(Calendar.MONTH, entity.getVlMesCartao() - 1);
			gc.set(Calendar.HOUR, 0);
			gc.set(Calendar.MINUTE, 0);
			gc.set(Calendar.SECOND, 0);
			gc.set(Calendar.MILLISECOND, 0);
			
			entity.setDtReferencia(gc.getTime());
			
		} else {
			entity.setDtReferencia(entity.getDtCompra());
		}
	}

	@Override
	protected void completarAlterar(Despesa entity, HttpServletRequest request) {
		
		if(entity.getFgCartao().equals("N")) {
			entity.setVlMesCartao(null);
			entity.setIdCartao(null);
		}
		
		entity.setDtManutencao(new Date());
	}

	@Override
	protected void validarInserir(Despesa entity, BindingResult result) {
		if (entity.getIdSegmento() == null) {
			result.addError(new ObjectError("Despesa", "Segmento informado"));
			return;
		}
		
		if (entity.getIdEstabelecimento() == null) {
			result.addError(new ObjectError("Despesa", "Estabelecimento informado"));
			return;
		}
		
		if (entity.getDtCompra() == null) {
			result.addError(new ObjectError("Despesa", "Data da Compra informado"));
			return;
		}
		
		if (entity.getVlDespesa() == null) {
			result.addError(new ObjectError("Despesa", "Valor informado"));
			return;
		}
		
		if (entity.getFgCartao() == null) {
			result.addError(new ObjectError("Despesa", "Cartão informado"));
			return;
		}
		
		if(entity.getFgAtivo().equals("S") 
				&& (entity.getIdCartao() == null || entity.getIdCartao().intValue() <= 0))
		{
			result.addError(new ObjectError("Despesa", "Qual Cartão informado?"));
			return;
		}
	}

	@Override
	protected void validarAlterar(Despesa entity, BindingResult result) {
		
		if (entity.getIdDespesa() == null) {
			result.addError(new ObjectError("Despesa", "Código informado"));
			return;
		}
		
		if (entity.getIdSegmento() == null) {
			result.addError(new ObjectError("Despesa", "Segmento informado"));
			return;
		}
		
		if (entity.getIdEstabelecimento() == null) {
			result.addError(new ObjectError("Despesa", "Estabelecimento informado"));
			return;
		}
		
		if (entity.getDtCompra() == null) {
			result.addError(new ObjectError("Despesa", "Data da Compra informado"));
			return;
		}
		
		if (entity.getVlDespesa() == null) {
			result.addError(new ObjectError("Despesa", "Valor informado"));
			return;
		}
		
		if (entity.getFgCartao() == null) {
			result.addError(new ObjectError("Despesa", "Cartão informado"));
			return;
		}
		
		if(entity.getFgAtivo().equals("S") 
				&& (entity.getIdCartao() == null || entity.getIdCartao().intValue() <= 0))
		{
			result.addError(new ObjectError("Despesa", "Qual Cartão informado?"));
			return;
		}
	}

	@Override
	protected void atualizarStatusEntidade(Despesa entity, String status) {
		entity.setFgAtivo(status);
		entity.setDtManutencao(new Date());
	}

	@Override
	protected Sort sortField() {
		return new Sort(Direction.ASC, getService().getFieldSort());
	}
	
	@Override
	protected void atualizarListaResponse(List<Despesa> lista) {
		for (Despesa despesa : lista) {
			despesa.setCartao(null);
			despesa.getSegmento().setCategoria(null);
			
			despesa.setStrVlDespesa(new DecimalFormat("#,##0.00").format(despesa.getVlDespesa()));
		}
	}
	
	@Override
	protected void atualizarEntityResponse(Despesa despesa) {		
		despesa.getSegmento().setCategoria(null);
	}
}
