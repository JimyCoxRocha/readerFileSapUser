package main;

import controller.ReaderController;
import entities.UserSapFileException;

public class Main {
public static void main(String[] args) throws UserSapFileException {
	try {
		ReaderController reader =  new ReaderController();
		System.out.println(reader.getUserSap("101"));		
	}catch(UserSapFileException err) {
		System.out.println(err.getMessage());
	}
}
}
