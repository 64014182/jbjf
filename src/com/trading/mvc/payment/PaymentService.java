package com.trading.mvc.payment;

import com.jfinal.log.Log;

import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = PaymentService.serviceName)
public class PaymentService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PaymentService.class);
	
	public static final String serviceName = "paymentService";
	
}
