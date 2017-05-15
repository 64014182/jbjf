package com.trading.mvc.salesorder;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = SalesOrderService.serviceName)
public class SalesOrderService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesOrderService.class);
	
	public static final String serviceName = "salesOrderService";
	
}
