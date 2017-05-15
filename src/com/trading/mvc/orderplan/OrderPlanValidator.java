package com.trading.mvc.orderplan;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class OrderPlanValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderPlanValidator.class);
	
	@SuppressWarnings("unused")
	private OrderPlanService orderPlanService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/orderPlan/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/orderPlan/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(OrderPlan.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/orderPlan/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/orderPlan/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
