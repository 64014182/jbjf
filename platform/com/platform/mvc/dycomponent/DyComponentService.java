package com.platform.mvc.dycomponent;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = DyComponentService.serviceName)
public class DyComponentService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DyComponentService.class);
	
	public static final String serviceName = "dyComponentService";
	
}
