package com.trading.mvc.retrospect;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = RetrospectService.serviceName)
public class RetrospectService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(RetrospectService.class);
	
	public static final String serviceName = "retrospectService";
	
}
