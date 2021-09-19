package controller;


import entities.UserSapEntity;
import entities.UserSapFileException;
import service.UserSapService;

public class ReaderController {

	public UserSapEntity getUserSap(String userId) throws UserSapFileException{
		try {
			UserSapService userSap = new UserSapService();
			return userSap.findUser(userId);
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage());
			
		}
	}
}
