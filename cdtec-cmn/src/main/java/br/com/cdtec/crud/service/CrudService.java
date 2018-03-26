package br.com.cdtec.crud.service;

import java.io.Serializable;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.util.ApplicationContextProvider;
import br.com.cdtec.util.GenericUtils;

/**
 * Classe extenda o CrudService deve ser annotada com @Transaction
 * apontando para o transctionManager que deseja usar na classe,
 * caso não seja annotada com @Transaction, os metodos não irão abrir transação 
 * a não ser que já estejam incluidos em uma transação 
 */
public abstract class CrudService<Entity, IdClass extends Serializable, Repository extends GenericRepository<Entity, IdClass>> extends GenericService<Entity, IdClass> {

	private static final long serialVersionUID = 1L;
		
	private Repository repository;

	@SuppressWarnings("unchecked")
	public Repository getRepository() {
		if(repository == null) {
			Class<Repository> daoClass = (Class<Repository>) GenericUtils.discoverClass(this.getClass(), 2);
			repository = ApplicationContextProvider.getApplicationContext().getBean( daoClass );
		}
		
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	@SuppressWarnings("unchecked")
	public Entity findById(IdClass pk){
		return (Entity) getRepository().findById(pk);
	}
	
	public Entity insert(Entity entity){
		getRepository().save(entity);
		return entity;
	}
	
	public Entity update(Entity entity){
		getRepository().save(entity);
		return entity;
	}
	
	public Entity remover(Entity entity ){
		getRepository().delete(entity);
		return entity;
	}
	
	/**
	 * Chamado antes da execução do insert
	 * @param entity
	 * @param exceptions
	 */
	public void validaInsert(Entity entity){
		
	}
	
	/**
	 * Chamado antes da execução do update 
	 * @param entity
	 * @param exceptions
	 */
	public void validaUpdate(Entity entity){
		
	}

	/**
	 * Chamado para todos os itens do removeAll(), e para o item caso usando o remover()
	 * @param entity
	 * @param exceptions
	 */
	public void validaRemocao(Entity entity){
		
	}
	
	
	
}