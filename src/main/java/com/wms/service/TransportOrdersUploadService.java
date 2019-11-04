package com.wms.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.wms.pojo.ExcelOrder;
import com.wms.pojo.TransportOrdersUpload;

public interface TransportOrdersUploadService {
	
	public List<ExcelOrder> upload(MultipartFile file) throws Exception;
	
	public TransportOrdersUpload postJson(TransportOrdersUpload ordersUpload );
}
