package com.platform.mvc.dycomponent;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class DyComponentValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DyComponentValidator.class);
	
	@SuppressWarnings("unused")
	private DyComponentService dyComponentService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/dyComponent/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/dyComponent/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(DyComponent.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/dyComponent/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/dyComponent/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
