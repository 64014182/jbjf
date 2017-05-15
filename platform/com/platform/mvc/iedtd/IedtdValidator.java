package com.platform.mvc.iedtd;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class IedtdValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(IedtdValidator.class);
	
	@SuppressWarnings("unused")
	private IedtdService iedtdService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/iedtd/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/iedtd/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Iedtd.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/iedtd/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/iedtd/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
