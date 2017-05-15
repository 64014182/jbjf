package com.trading.mvc.declareplan;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = DeclareplanService.serviceName)
public class DeclareplanService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeclareplanService.class);
	
	public static final String serviceName = "declareplanService";
	
}
