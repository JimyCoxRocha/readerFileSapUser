package Utilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import entities.ComunicationEntity;
import entities.ServiceEntity;
import entities.UserSapEntity;
import entities.UserSapFileException;

public class Helpers {

	public static void validateData(UserSapEntity user) throws UserSapFileException, IllegalArgumentException, IllegalAccessException {
		try {
			List<String> errorsComunication = new ArrayList<>();
			List<String> errorsServices = new ArrayList<>();
			
			user.getDescription();
			ComunicationEntity com = user.getComunication();
			List<ServiceEntity> services = user.getServices();
			
			errorsComunication = analizerFields(com.getClass().getDeclaredFields(), com, "ComunicationEntity");
			
			
			for(ServiceEntity service : services) {
				errorsServices.addAll(analizerFields(service.getClass().getDeclaredFields(), service, " Servicio "+service.getCode()));
			}

			String errorsDetected = "Los siguientes datos se deben solucionar: ";
			for(String result : errorsComunication)
				errorsDetected = errorsDetected + result;
			
			for(String result : errorsServices)
				errorsDetected = errorsDetected + result;
			
					
			if(errorsComunication.size()>0 || errorsServices.size()>0)
				throw new UserSapFileException(errorsDetected);
		}catch(UserSapFileException err) {
			throw new UserSapFileException(err.getMessage());
		}catch(NullPointerException err) {
			throw new UserSapFileException("Por favor, asegúrese que los datos esten ingresados correctamente dentro del JSON: ");
		}catch(Exception err) {
			throw new UserSapFileException("Ha ocurrido un error al momento de analizar los campos del usuario de conexión a SAP");
		}
	}
	
	public static <T> List<String> analizerFields(Field[] fields, T type, String information) throws IllegalArgumentException, IllegalAccessException{
		List<String> errors = new ArrayList<String>();
		for(Field field : fields) {
			field.setAccessible(true);
			if(field.get(type) == null) {
				errors.add(" ["+information + " " + field.getName() + ": null]");
			}
		}
		return errors;
		
	}


}
