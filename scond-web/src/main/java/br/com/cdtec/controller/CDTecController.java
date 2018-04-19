package br.com.cdtec.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.cdtec.crud.service.CrudService;
import br.com.cdtec.crud.view.BaseController;
import br.com.cdtec.response.Response;
import br.com.cdtec.security.entity.Usuario;
import br.com.cdtec.security.jwt.JwtTokenUtil;
import br.com.cdtec.security.service.UsuarioService;

public class CDTecController<Entity, IdClass extends Serializable, Service extends CrudService<Entity, IdClass, ?>>
		extends BaseController<Service> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Class default de insert full permission Para restringir a classe, devera ser
	 * sobrescrita com o @PreAuthorize(role)
	 * 
	 * @param request
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Response<Entity>> inserir(HttpServletRequest request, @RequestBody Entity entity,
			BindingResult result) {
		Response<Entity> response = new Response<Entity>();
		try {
			validarInserir(entity, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			completarInserir(entity, request);
			Entity objInsert = getService().inserir(entity);
			response.setData(objInsert);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping()		
	public ResponseEntity<Response<Entity>> alterar(HttpServletRequest request, @RequestBody Entity entity,
			BindingResult result) {
		Response<Entity> response = new Response<Entity>();
		try {
			validarAlterar(entity, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			completarAlterar(entity, request);			
			Entity userPersisted = (Entity) getService().alterar(entity);
			response.setData(userPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Entity>> get(HttpServletRequest request, @PathVariable("id") IdClass id) {
		Response<Entity> response = new Response<Entity>();
		try {
			Optional<Entity> entityOptional = getService().get(id);
			Entity entity = entityOptional.get();
			
			if (entity == null) {
				response.getErrors().add("Registro não encontrado com o código:" + id);
				return ResponseEntity.badRequest().body(response);
			}
			response.setData(entity);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path = "/pesquisar")
	public ResponseEntity<Response<List<Entity>>> pesquisar(HttpServletRequest request,
			@RequestBody Entity entity) {
		Response<List<Entity>> response = new Response<List<Entity>>();
		try {
			List<Entity> lista = null;
			lista = getService().pesquisar(entity, sortField());
			response.setData(lista);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping(value = "/{id}/{status}")
	public ResponseEntity<Response<String>> atualizarStatus(@PathVariable("id") IdClass id,
			@PathVariable("status") String status) {
		Response<String> response = new Response<String>();
		try {
			Optional<Entity> entityOptional = getService().get(id);
			Entity entity = entityOptional.get();

			if (entity == null) {
				response.getErrors().add("Registro não encontrado com o código: " + id);
				return ResponseEntity.badRequest().body(response);
			}

			atualizarStatusEntidade(entity, status);
			getService().alterar(entity);

		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(new Response<String>());
	}

	protected void validarInserir(Entity entity, BindingResult result) {}	
	protected void validarAlterar(Entity entity, BindingResult result) {}
	protected void completarInserir(Entity entity, HttpServletRequest request) {}	
	protected void completarAlterar(Entity entity,  HttpServletRequest request) {}
	protected void atualizarStatusEntidade(Entity entity, String status) {}

	
	public Usuario getUsuarioFromRequest(HttpServletRequest request) {		
		String token = request.getHeader("Authorization");
		String login = jwtTokenUtil.getLoginFromToken(token);		
		return usuarioService.pesquisarPorLogin(login);		
	}	
	
	protected Sort sortField() {
		return null;
	}

}
