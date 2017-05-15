package com.trading.mvc.declareplan;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class DeclareplanValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeclareplanValidator.class);
	
	@SuppressWarnings("unused")
	private DeclareplanService declareplanService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/declareplan/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/declareplan/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Declareplan.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/declareplan/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/declareplan/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
