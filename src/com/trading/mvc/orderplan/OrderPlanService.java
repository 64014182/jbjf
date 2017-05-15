package com.trading.mvc.orderplan;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = OrderPlanService.serviceName)
public class OrderPlanService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderPlanService.class);
	
	public static final String serviceName = "orderPlanService";
	
}
