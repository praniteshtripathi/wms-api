package com.wms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wms.pojo.TransportOrders;
@Service
public class TransportOrdersServiceImpl implements TransportOrdersService {
	RestTemplate restTemplate= new RestTemplate();
	 @Value( "${open.tcs.order.list.url}" )
	 String allTransportOrders;
	@Override
	public List < TransportOrders > getTransportOrders(String intendedVehicle) throws Exception {
		List < TransportOrders > transportOrders2= new ArrayList<>();
		try {
	    UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(allTransportOrders)
			    .queryParam("intendedVehicle",intendedVehicle);
	    ResponseEntity < List < TransportOrders >> response = restTemplate.exchange(builder.toUriString(),
	    	    HttpMethod.GET, null, new ParameterizedTypeReference < List < TransportOrders >> () {});

	    	transportOrders2 = response.getBody(); 
		}catch (Exception e) {
			e.printStackTrace();	
		}
		return transportOrders2;
	}
	
	@Override
	public List<TransportOrders> getAllTransportOrders() throws Exception {
		List < TransportOrders > transportOrders2= new ArrayList<>();
		try {
			
	    UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(allTransportOrders);
	    ResponseEntity < List < TransportOrders >> response = restTemplate.exchange(builder.toUriString(),
	    	    HttpMethod.GET, null, new ParameterizedTypeReference < List < TransportOrders >> () {});
	    	transportOrders2 = response.getBody(); 
		}catch (Exception e) {
			e.printStackTrace();	
		}
		return transportOrders2;
	}

}
