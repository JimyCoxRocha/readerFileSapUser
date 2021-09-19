package main;

import java.util.HashMap;
import java.util.Map;

import controller.ReaderController;
import entities.ServiceEntity;
import entities.UserSapEntity;
import entities.UserSapFileException;

public class Main {
public static void main(String[] args) throws UserSapFileException {
	try {
		ReaderController reader =  new ReaderController();
		System.out.println(reader.getUserSap("101"));		
		UserSapEntity userSapEnttiy = reader.getUserSap("10");
		
		Map<String, String> campos = new HashMap<>();
		campos.put("description", "Consulta Vinculacion Individual");
		campos.put("urlMethod", "consultaVinculacionInd");
		ServiceEntity service = reader.getServiceByFields(campos, userSapEnttiy);
		System.out.println(service.toString());
		
		
	}catch(UserSapFileException err) {
		System.out.println(err.getMessage() + " and " + err.getCode());
	}
}
}
