package entities;

import java.util.ArrayList;
import java.util.List;

public class UserSapEntity {

	public String description;
	public ComunicationEntity comunication;
	public List<ServiceEntity> services = new ArrayList<ServiceEntity>();
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ComunicationEntity getComunication() {
		return comunication;
	}
	public void setComunication(ComunicationEntity comunication) {
		this.comunication = comunication;
	}
	public List<ServiceEntity> getServices() {
		return services;
	}
	public void setServices(List<ServiceEntity> services) {
		this.services = services;
	}
	
	
}
