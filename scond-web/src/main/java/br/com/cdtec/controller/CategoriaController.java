package br.com.cdtec.controller;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.entity.Categoria;
import br.com.cdtec.response.Response;
import br.com.cdtec.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController extends CDTecController<Categoria, BigInteger, CategoriaService> {

	private static final long serialVersionUID = 1L;	
	
	@GetMapping(value = "{page}/{count}/{dsCategoria}/{fgAtivo}")	
    public  ResponseEntity<Response<Page<Categoria>>> pesquisar(HttpServletRequest request, 
    		 							@PathVariable int page, 
    		 							@PathVariable int count,    		 							
    		 							@PathVariable String dsCategoria,
    		 							@PathVariable String fgAtivo) {
		Response<Page<Categoria>> response = new Response<Page<Categoria>>();
		try {
			Page<Categoria> listaCategoria = null;
			
			Categoria categoria = new Categoria();
			categoria.setDsCategoria(dsCategoria);
			categoria.setFgAtivo(fgAtivo);
			
			listaCategoria = getService().pesquisar(page, count, categoria, sortField());
			response.setData(listaCategoria);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
    }
	
	@Override
	protected void completarInserir(Categoria entity) {
		entity.setDtCadastro(new Date());
		entity.setFgAtivo("S");
	}
	
	@Override
	protected void completarAlterar(Categoria entity) {
		entity.setDtManutencao(new Date());		
	}
	
	@Override
	protected void validarInserir(Categoria entity, BindingResult result) {
		if (entity.getDsCategoria() == null
				|| entity.getDsCategoria().trim().equals("")) {
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
		
		if (entity.getDsCategoria() == null
				|| entity.getDsCategoria().trim().equals("")) {
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
