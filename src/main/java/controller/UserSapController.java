package controller;


import java.util.Map;

import entities.ServiceEntity;
import entities.UserSapEntity;
import entities.UserSapFileException;
import service.UserSapService;

public class UserSapController {

	public static UserSapEntity getUserSap(String userId) throws UserSapFileException{
		try {
			UserSapService userSap = new UserSapService();
			return userSap.findUser(userId);
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage(), err.getCode());
			
		}
	}
	
	public static ServiceEntity getServiceByFields(Map<String, String> fieldsValues, UserSapEntity userSap) throws UserSapFileException{
		try {
			UserSapService userSapService = new UserSapService();
			return userSapService.findByServicesFields(fieldsValues, userSap);
			
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage(), err.getCode());
			
		}
	}
	
}
