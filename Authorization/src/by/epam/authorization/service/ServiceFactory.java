package by.epam.authorization.service;

import java.util.HashMap;
import java.util.Map;

import by.epam.authorization.service.impl.DeclarationService;
import by.epam.authorization.service.impl.DeclarationSubmissionService;
import by.epam.authorization.service.impl.LoginService;
import by.epam.authorization.service.impl.MaxDeclarationNumberService;
import by.epam.authorization.service.impl.RegistrationService;
import by.epam.authorization.service.impl.RestoreService;

/**
 * ServiceFactory.java
 * Class-factory for classes that implemented interface Service.java
 * @author MasSword
 */

public final class ServiceFactory {
	private static ServiceFactory instance;
	private Map <ServiceName, Service> services = new HashMap<>();
	
	private ServiceFactory(){
		services.put(ServiceName.LOGIN, new LoginService());
		services.put(ServiceName.REGISTRATION, new RegistrationService());
		services.put(ServiceName.RESTORE, new RestoreService());
		services.put(ServiceName.DECLARATION, new DeclarationService());
		services.put(ServiceName.MAX_DECL_NUMBER, new MaxDeclarationNumberService());
		services.put(ServiceName.DECLARATION_SUBMISSION, new DeclarationSubmissionService());
	}
	
	/**
     * Method returns ServiceFactory.java object
     * if it is not created, method creates it 
     * @return ServiceFactory.java object
     */
	
	public static ServiceFactory getServiceFactory(){
		ServiceFactory localInstance = instance;
		if(localInstance == null){
			synchronized (ServiceFactory.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = new ServiceFactory();
				}
			}
		}
		return instance;
	}
	
	/**
	 * getter-method
     * @return UserService.java object
     */
	
	public UserService getUserService(ServiceName serviceName){
		UserService service = (UserService) services.get(serviceName);
		return service;
	}
	
	/**
	 * getter-method
     * @return InfService.java object
     */
	
	public InfService getInfService(ServiceName serviceName){
		InfService service = (InfService) services.get(serviceName);
		return service;
	}
	
	/**
	 * getter-method
     * @return DeclService.java object
     */
	
	public DeclService getDeclService(ServiceName serviceName){
		DeclService service = (DeclService) services.get(serviceName);
		return service;
	}
}
