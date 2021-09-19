package main;

import java.util.HashMap;
import java.util.Map;

import controller.UserSapController;
import entities.ServiceEntity;
import entities.UserSapEntity;
import entities.UserSapFileException;

public class Main {
public static void main(String[] args) throws UserSapFileException {
	try {
		System.out.println(UserSapController.getUserSap("101"));		
		UserSapEntity userSapEnttiy = UserSapController.getUserSap("101");
		
		Map<String, String> campos = new HashMap<>();
		campos.put("description", "Consulta Vinculacion Individual");
		campos.put("urlMethod", "consultaVinculacionInd");
		ServiceEntity service = UserSapController.getServiceByFields(campos, userSapEnttiy);
		System.out.println(service.toString());
		
		Map<String, String> campos2 = new HashMap<>();
		campos2.put("description", "Consulta Transporte Ind FA");
		campos2.put("urlMethod", "consultaTransportistaInd");
		ServiceEntity service2 = UserSapController.getServiceByFields(campos2, userSapEnttiy);
		System.out.println(service2.toString());
		
		
		Map<String, String> campo3 = new HashMap<>();
		campo3.put("description", "Consulta Producto Lote ");
		campo3.put("urlMethod", "consultaProductoLote");
		ServiceEntity service3 = UserSapController.getServiceByFields(campo3, userSapEnttiy);
		System.out.println(service3.toString());
		
	}catch(UserSapFileException err) {
		System.out.println(err.getMessage() + " and " + err.getCode());
	}
}
}
