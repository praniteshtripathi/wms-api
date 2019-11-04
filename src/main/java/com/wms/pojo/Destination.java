package com.wms.pojo;

import java.util.ArrayList;
import java.util.List;

public class Destination {
	private String locationName;
	 private String operation;
	 private String state;
	 List < Property > properties = new ArrayList <  > ();
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	 
	 
}