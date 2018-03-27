package br.com.cdtec.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.crud.view.BaseController;
import br.com.cdtec.response.Response;

public class CDTecController<Entity, IdClass extends Serializable, Service extends CrudService<Entity, IdClass, ?>> 
					extends BaseController<Service> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PostMapping()
	public ResponseEntity<Response<Entity>> inserir(HttpServletRequest request, @RequestBody Entity entity, 
			BindingResult result) {
		
		Response<Entity> response = new Response<Entity>();
		
		try {
			validarInserir(entity, result);
			
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			completarInserir(entity);			
			Entity objInsert = getService().insert(entity);
			response.setData(objInsert);
			
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		
		
		return ResponseEntity.ok(response);
		
	}
	
	protected void validarInserir(Entity entity, BindingResult result) {}
	
	protected void completarInserir(Entity entity) {}

}
