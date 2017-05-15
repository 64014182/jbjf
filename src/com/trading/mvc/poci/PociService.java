package com.trading.mvc.poci;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = PociService.serviceName)
public class PociService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PociService.class);
	
	public static final String serviceName = "pociService";
	
}
