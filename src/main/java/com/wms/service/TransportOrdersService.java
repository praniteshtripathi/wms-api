package com.wms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wms.pojo.TransportOrders;

public interface TransportOrdersService {
	public List <TransportOrders > getTransportOrders(String intendedVehicle) throws Exception;
	public List <TransportOrders > getAllTransportOrders() throws Exception;
}
