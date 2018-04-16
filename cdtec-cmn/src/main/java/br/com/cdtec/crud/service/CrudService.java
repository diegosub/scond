package br.com.cdtec.crud.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.cdtec.crud.repository.GenericRepository;

/**
 * Classe extenda o CrudService deve ser annotada com @Transaction
 * apontando para o transctionManager que deseja usar na classe,
 * caso não seja annotada com @Transaction, os metodos não irão abrir transação 
 * a não ser que já estejam incluidos em uma transação 
 */
public abstract class CrudService<Entity, IdClass extends Serializable, Repository extends GenericRepository<Entity, IdClass>> extends GenericService<Entity, IdClass> {

	private static final long serialVersionUID = 1L;
		
	@Autowired
	private Repository repository;

		
	public Optional<Entity> get(IdClass id) throws Exception {
		return getRepository().findById(id);
	}

	public Entity inserir(Entity entity) throws Exception {
		validarInserir(entity);
		getRepository().save(entity);
		return entity;
	}
	
	public Entity alterar(Entity entity) throws Exception {
		validarAlterar(entity);
		getRepository().save(entity);
		return entity;
	}	
	
	public Page<Entity> listarTodos(int page, int count, Sort sort) {
		Pageable pages = PageRequest.of(page, count, sort);
		return getRepository().findAll(pages);
	}
	
	public void validarInserir(Entity entity) throws Exception {}
	public void validarAlterar(Entity entity) throws Exception {}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}	
}