package br.com.cdtec.crud.view;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<Service> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
}
