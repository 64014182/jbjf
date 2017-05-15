package com.trading.mvc.orderunit;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = OrderUnitService.serviceName)
public class OrderUnitService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderUnitService.class);
	
	public static final String serviceName = "orderUnitService";
	
}
