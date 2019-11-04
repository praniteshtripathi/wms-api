package com.wms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wms.WMSApplication;
import com.wms.pojo.TransportOrders;
import com.wms.service.TransportOrdersService;

import  org.springframework.stereotype.Service;
@RestController

public class TransportOrdersController {
	private static Logger logger = LogManager.getLogger(TransportOrdersController.class);
	@Autowired
	TransportOrdersService transportOrdersService;
	   @GetMapping("/api/alltransportOrders")
		public ResponseEntity<?> getAllTransportOrders() {
	    	List<TransportOrders> transportOrdersList=new ArrayList<TransportOrders>();
			try {
				transportOrdersList = transportOrdersService.getAllTransportOrders();
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}			
			return new ResponseEntity(transportOrdersList, HttpStatus.OK);
		}
	   
	   @GetMapping("/api/alltransportOrdersWithVehicles")
		public ResponseEntity<?> getAllTransportOrdersWithVehicles() {
		   List<TransportOrders> transportOrdersList=new ArrayList<TransportOrders>();
	    	try {
				transportOrdersList = transportOrdersService.getAllTransportOrders();
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
	    	return new ResponseEntity(transportOrdersList, HttpStatus.OK);
		}
	   
	   @GetMapping("/api/testorder")
		public ResponseEntity<?> getTestOrder() {
		   logger.info("--------------getTestOrder execution start-----------------------------");
		   List<TransportOrders> transportOrdersList=new ArrayList<TransportOrders>();
		   TransportOrders orders= new TransportOrders();
		   orders.setName("Test order 1");
		   orders.setIntendedVehicle("vehicles01");
		   orders.setProcessingVehicle("VH-02");
		   orders.setState("IDEAL");
		   transportOrdersList.add(orders);
		logger.debug("--------------getTestOrder  execution end -----------------------------");
	    	return new ResponseEntity(transportOrdersList, HttpStatus.OK);
		}
}
