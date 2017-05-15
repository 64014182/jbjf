package com.platform.mvc.iedtd;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = IedtdService.serviceName)
public class IedtdService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(IedtdService.class);
	
	public static final String serviceName = "iedtdService";
	
}
