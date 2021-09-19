package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Utilities.Helpers;
import entities.CodeEnum;
import entities.ServiceEntity;
import entities.UserSapEntity;
import entities.UserSapFileException;

public class UserSapService {
	public static String route = "C:\\\\MDWV2\\\\config\\\\usersap";
	
	public UserSapEntity findUser(String idUSer) throws UserSapFileException {
		try {
			Gson gson = new Gson();
			
			Object obj = gson.fromJson(new FileReader(UserSapService.route+idUSer+".json"), Object.class);
			JsonObject jsonUser = gson.fromJson(gson.toJson(obj), JsonObject.class);
			
			UserSapEntity user = gson.fromJson(jsonUser, UserSapEntity.class);
			Helpers.validateData(user);
			
			return user;
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage(), err.getCode());
		}catch(FileNotFoundException err) {
			throw new UserSapFileException("El usuario que intenta especificar no existe en la ruta: "+UserSapService.route+idUSer+".json", CodeEnum.BAD_REQUEST);
		}catch(JsonParseException err) {
			throw new UserSapFileException("Ocurrió un error al leer el archivo en la ruta: "+UserSapService.route+idUSer+".json, por favor, revise que el formato sea el indicado", CodeEnum.BAD_REQUEST);
		}catch(Exception err) {
			throw new UserSapFileException("No se ha podido acceder a la ruta: "+UserSapService.route+idUSer+".json, "+err.getClass(), CodeEnum.INTERNAL_SERVER_ERROR);	
		}
	}
	
	public ServiceEntity findByServicesFields(Map<String, String> fieldsValues, UserSapEntity userSap) throws UserSapFileException {
		try {
			if(userSap == null || userSap.getServices() == null)
				throw new UserSapFileException("El objeto con los servicios que desea buscar, no puede llegar vacía.", CodeEnum.BAD_REQUEST);
			
			List<ServiceEntity> services = userSap.getServices();
			List<ServiceEntity> servicesSelected = new ArrayList<>();
			
			for(Map.Entry<String, String> field :fieldsValues.entrySet()) {
				servicesSelected = Helpers.analizerEqualsFields(services, field.getKey(), field.getValue());
				//personalizar la excepción porque se va a confundir cuando se un error ajeno
				if(servicesSelected.size() == 0)
					throw new UserSapFileException("No se encontró coincidencia. No tiene autorización", CodeEnum.UNAUTHORIZED);
				
				services = servicesSelected;
				
			}
			
//			if(servicesSelected.size() > 0)
				return servicesSelected.get(0);
			
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage(), err.getCode());
		}catch(Exception err) {
			throw new UserSapFileException("No se ha podido realizar la operación de búsqueda", CodeEnum.INTERNAL_SERVER_ERROR);	
		}
	}
	
}
