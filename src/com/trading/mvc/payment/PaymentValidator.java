package com.trading.mvc.payment;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class PaymentValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PaymentValidator.class);
	
	@SuppressWarnings("unused")
	private PaymentService paymentService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/payment/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/payment/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Payment.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/payment/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/payment/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
