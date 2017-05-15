package com.test.mvc.cusinfo;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = CusinfoService.serviceName)
public class CusinfoService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(CusinfoService.class);
	
	public static final String serviceName = "cusinfoService";
	
}
