package com.platform.mvc.dimensional;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = DimensionalService.serviceName)
public class DimensionalService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DimensionalService.class);
	
	public static final String serviceName = "dimensionalService";
	
}
