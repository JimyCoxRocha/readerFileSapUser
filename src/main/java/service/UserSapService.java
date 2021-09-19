package service;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Utilities.Helpers;
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
			throw new UserSapFileException(err.getMessage());
		}catch(FileNotFoundException err) {
			throw new UserSapFileException("El usuario que intenta especificar no existe en la ruta: "+UserSapService.route+idUSer+".json");
		}catch(JsonParseException err) {
			throw new UserSapFileException("Ocurrió un error al leer el archivo en la ruta: "+UserSapService.route+idUSer+".json, por favor, revise que el formato sea el indicado");
		}catch(Exception err) {
			throw new UserSapFileException("No se ha podido acceder a la ruta: "+UserSapService.route+idUSer+".json, "+err.getClass());	
		}
	}
	
}
