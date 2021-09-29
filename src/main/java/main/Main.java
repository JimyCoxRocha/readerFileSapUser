package main;
<html>
    <body>
        <div style='align-items: center;'>
            <div style='width: 100%; text-align: center;'>
                <img src="duragas.png" alt='logo familia duragas' max-width='1rem' style="margin: 0px auto;">
            </div>
            <h3 style="text-align: left;">Reporte de error</h3>
            <table>
                <tr>
                    <th style='background-color:#c1c1c1; padding: 0.5rem;'>Error</th>
                    <th style='background-color:#c1c1c1; padding: 0.5rem;'>DataBase</th>
                    <th style='background-color:#c1c1c1; padding: 0.5rem;'>Servicio</th>
                    <th style='background-color:#c1c1c1; padding: 0.5rem;'>Fecha</th>
                </tr>
                <tbody>

                    <td style='padding: 1rem; border: 1px solid #2533fd;'>${typeError}</td>
                    <td style='padding: 1rem; border: 1px solid #2533fd;'>${dataBase}</td>
                    <td style='padding: 1rem; border: 1px solid #2533fd;'>${service}</td>
                    <td style='padding: 1rem; border: 1px solid #2533fd;'>${date}</td>
                </tbody>

            </table>
        </div>
    </body>
</html>
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
