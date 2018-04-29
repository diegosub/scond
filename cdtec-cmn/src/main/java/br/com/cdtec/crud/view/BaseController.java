package br.com.cdtec.crud.view;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<Entity, Service> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Service service;

	private Entity searchObject;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Entity getSearchObject() {
		try {
			if (searchObject == null) {
				return getNewEntityInstance();
			}
		} catch (Exception e) {
			System.out.println("Os objetos não puderam ser instanciados corretamente.");
			// não relança a exceção porque esses objetos podem ser informados
			// através de propriedades.
		}
		
		
		return null;
	}

	public void setSearchObject(Entity searchObject) {
		this.searchObject = searchObject;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Entity getNewEntityInstance() {
		try {
			return (Entity) ((Class) ((java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass())
					.getActualTypeArguments()[0]).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
