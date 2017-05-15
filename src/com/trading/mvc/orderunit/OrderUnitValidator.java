package com.trading.mvc.orderunit;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class OrderUnitValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderUnitValidator.class);
	
	@SuppressWarnings("unused")
	private OrderUnitService orderUnitService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/orderUnit/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/orderUnit/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(OrderUnit.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/orderUnit/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/orderUnit/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
