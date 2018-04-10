package br.com.cdtec.crud.service;

import java.io.Serializable;

/**
 * Classe que extender o CrudService deve ser annotada com @Transaction
 * apontando para o transctionManager que deseja usar na classe,
 * caso não seja annotada com @Transaction, os metodos não irão abrir transação 
 * a não ser que já estejam incluidos em uma transação 
 */
public abstract class GenericService<Entity, IdClass> implements Serializable{
	
	private static final long serialVersionUID = 8402381475468042717L;

	public abstract Entity findById(IdClass pk);
	
	public abstract Entity insert(Entity entity) throws Exception;
	
	public abstract Entity update(Entity entity);
	
	public abstract Entity remover(Entity entity);
	
}