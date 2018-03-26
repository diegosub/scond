package br.com.cdtec.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cdtec.crud.view.BaseController;
import br.com.cdtec.entity.Categoria;
import br.com.cdtec.service.CategoriaService;

@RestController
public class CategoriaController extends BaseController<CategoriaService> {
	
	private static final long serialVersionUID = 1L;

	@RequestMapping(method = RequestMethod.POST, value = "/categoria")
	public ResponseEntity<Categoria> cadastrar() {
		
		Categoria categoria = getService().pesquisarPorNome("TESTE1");
		
		return new ResponseEntity<Categoria>(categoria, HttpStatus.ACCEPTED);
	}
	
}
