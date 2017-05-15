package com.test.mvc.cusinfo;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class CusinfoValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(CusinfoValidator.class);
	
	@SuppressWarnings("unused")
	private CusinfoService cusinfoService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/test/cusinfo/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/test/cusinfo/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Cusinfo.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/test/cusinfo/save")){
			controller.render("/test/xxx.html");
		
		} else if (actionKey.equals("/test/cusinfo/update")){
			controller.render("/test/xxx.html");
		
		}
	}
	
}
