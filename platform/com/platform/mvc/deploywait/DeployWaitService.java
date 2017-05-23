package com.platform.mvc.deploywait;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = DeployWaitService.serviceName)
public class DeployWaitService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeployWaitService.class);
	
	public static final String serviceName = "deployWaitService";
	
}
