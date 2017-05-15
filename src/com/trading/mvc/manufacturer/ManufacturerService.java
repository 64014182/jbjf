package com.trading.mvc.manufacturer;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = ManufacturerService.serviceName)
public class ManufacturerService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ManufacturerService.class);
	
	public static final String serviceName = "manufacturerService";
	
}
