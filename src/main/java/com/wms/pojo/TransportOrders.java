package com.wms.pojo;
import java.util.List;
import java.util.ArrayList;
public class TransportOrders{
	 private String name;
	 private String category;
	 private String state;
	 private String intendedVehicle;
	 private String processingVehicle;
	 List <Destination >destinations = new ArrayList <> ();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIntendedVehicle() {
		return intendedVehicle;
	}
	public void setIntendedVehicle(String intendedVehicle) {
		this.intendedVehicle = intendedVehicle;
	}
	public String getProcessingVehicle() {
		return processingVehicle;
	}
	public void setProcessingVehicle(String processingVehicle) {
		this.processingVehicle = processingVehicle;
	}
	public List<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
	 
	 
}
