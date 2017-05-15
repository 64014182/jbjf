package com.trading.mvc.retrospect;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class RetrospectValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(RetrospectValidator.class);
	
	@SuppressWarnings("unused")
	private RetrospectService retrospectService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/retrospect/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/retrospect/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Retrospect.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/retrospect/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/retrospect/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
