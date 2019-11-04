package com.wms.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.wms.pojo.Destination;
import com.wms.pojo.DestinationUpload;
import com.wms.pojo.ExcelOrder;
import com.wms.pojo.Property;
import com.wms.pojo.TransportOrdersUpload;


@Service
public class TransportOrdersUploadServiceImpl implements TransportOrdersUploadService {
	RestTemplate restTemplate= new RestTemplate();
	 @Value("${open.tcs.order.upload.url}")
	 String saveTransportOrders;
	 
	 
	@Override
	public List<ExcelOrder> upload(MultipartFile file) throws Exception {
		List<ExcelOrder> excelOrdersList= new ArrayList<ExcelOrder>();
		try {
				Path tempDir = Files.createTempDirectory("");
				
				File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
		
				file.transferTo(tempFile);
		
				Workbook workbook = WorkbookFactory.create(tempFile);
		
				Sheet sheet = workbook.getSheetAt(0);
		
				Iterator rows = sheet.iterator();
				int rowNumber = 0;
				List<TransportOrdersUpload> extractedObjects = new ArrayList<>();	
				// started to read row 
				while (rows.hasNext()) {
					String orderName="";
					List <DestinationUpload >destinations= new ArrayList<DestinationUpload>();
					DestinationUpload  destination= new DestinationUpload();
					
					Row currentRow = (Row) rows.next();
					TransportOrdersUpload jsonObj = new TransportOrdersUpload();
					ExcelOrder  excelOrder= new ExcelOrder();
					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}
					Iterator cellsInRow = currentRow.iterator();			
					int cellIndex = 0;
					
					while (cellsInRow.hasNext()) {
						
						Cell currentCell = (Cell) cellsInRow.next();
						if (cellIndex == 0) {
							jsonObj.setDeadline(currentCell.getStringCellValue());
							excelOrder.setDeadLine(currentCell.getStringCellValue());
							
						} else if (cellIndex == 1) {
							 orderName=currentCell.getStringCellValue();
							 excelOrder.setName(orderName);
						} else if (cellIndex == 2) {
							jsonObj.setIntendedVehicle(currentCell.getStringCellValue()==""||currentCell.getStringCellValue().equalsIgnoreCase("Automatic")
									?null:currentCell.getStringCellValue());
							excelOrder.setIntendedVehicle(currentCell.getStringCellValue());
						}else if (cellIndex == 3) {
							List<Property>properties= new ArrayList<>();
							Property property= new Property();
								property.setKey("key1");
								property.setValue("Value1");
								properties.add(property);
							destination.setLocationName(currentCell.getStringCellValue());
							excelOrder.setTarget(currentCell.getStringCellValue());
							destination.setProperties(properties); destinations.add(destination);
							jsonObj.setDestinations(destinations);	
						}if (cellIndex == 4) {
				        	destination.setOperation(currentCell.getStringCellValue());
						}
						cellIndex++;
					}
					excelOrder.setOrderUploadStatus("success");
					excelOrdersList.add(excelOrder);
			        try {
					restTemplate.postForObject(saveTransportOrders+"/"+orderName,jsonObj,TransportOrdersUpload.class);
			        }catch(Exception ex) {
			        	//System.out.println("-----inside--------"+ex.getMessage());
			        	if(ex.getMessage().equals("409 Conflict")) {
			        		excelOrder.setOrderUploadStatus("Order alredy exist");
			        	}else if(ex.getMessage().contains("Connection refused")) {
			        		excelOrder.setOrderUploadStatus("Open TCS is down");
			        	}else if(ex.getMessage().equals("404 Not Found")) {
			        		excelOrder.setOrderUploadStatus("Not Found");
			        	}
			        	
			        }
				
				}// row end
		}catch(Exception ex) {
			//System.out.println("-----outside--------"+ex.getMessage());
		}
		
		return excelOrdersList;
	}


	@Override
	public TransportOrdersUpload postJson(TransportOrdersUpload ordersUpload) {
		return ordersUpload;
	}
	

}
