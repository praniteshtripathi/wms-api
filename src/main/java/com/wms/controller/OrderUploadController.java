package com.wms.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wms.pojo.ExcelOrder;
import com.wms.service.TransportOrdersUploadService;

@RestController
public class OrderUploadController {

    @Autowired
    TransportOrdersUploadService transportOrdersUploadService;
	
    @PostMapping("/api/upload") 
     public ResponseEntity<?> singleFileUpload( @RequestParam("file") MultipartFile file) {
    	List<ExcelOrder> excelOrders= new ArrayList<ExcelOrder>();
    	try {
        	excelOrders=transportOrdersUploadService.upload(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
   
    	return new ResponseEntity(excelOrders, HttpStatus.OK);
    }
}