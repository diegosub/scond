package br.com.cdtec.crud.view;

import java.io.Serializable;

import br.com.cdtec.util.ApplicationContextProvider;
import br.com.cdtec.util.GenericUtils;

public class BaseController<Service> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Service service;
	protected Class<Service> serviceClass;
		
	public Service getService() {
		if(service == null) {
			service = ApplicationContextProvider.getApplicationContext().getBean(getServiceClass());
		}		
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	@SuppressWarnings("unchecked")
	public Class<Service> getServiceClass() {
		if(serviceClass == null) {
			serviceClass = (Class<Service>) GenericUtils.discoverClass(getClass(), 0);
		}				
		return serviceClass;
	}
	
	public void setServiceClass(Class<Service> serviceClass) {
		this.serviceClass = serviceClass;
	}

}
