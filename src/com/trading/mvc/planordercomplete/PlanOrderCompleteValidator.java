package com.trading.mvc.planordercomplete;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class PlanOrderCompleteValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PlanOrderCompleteValidator.class);
	
	@SuppressWarnings("unused")
	private PlanOrderCompleteService planOrderCompleteService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/planOrderComplete/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/planOrderComplete/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(PlanOrderComplete.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/planOrderComplete/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/planOrderComplete/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
