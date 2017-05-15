package com.trading.mvc.deliverydetailed;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class DeliveryDetailedValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeliveryDetailedValidator.class);
	
	@SuppressWarnings("unused")
	private DeliveryDetailedService deliveryDetailedService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/deliveryDetailed/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/deliveryDetailed/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(DeliveryDetailed.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/deliveryDetailed/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/deliveryDetailed/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
