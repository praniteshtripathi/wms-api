package com.wms.pojo;
import java.util.List;
import java.util.ArrayList;
public class TransportOrdersUpload{
	
	 private String deadline;
	 private String intendedVehicle;
	 private List<DestinationUpload> destinations;
	 
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getIntendedVehicle() {
		return intendedVehicle;
	}
	public void setIntendedVehicle(String intendedVehicle) {
		this.intendedVehicle = intendedVehicle;
	}
	public List<DestinationUpload> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<DestinationUpload> destinations) {
		this.destinations = destinations;
	}
	
	
}
