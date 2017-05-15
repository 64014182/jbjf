package com.platform.mvc.dimensional;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class DimensionalValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DimensionalValidator.class);
	
	@SuppressWarnings("unused")
	private DimensionalService dimensionalService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/dimensional/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/dimensional/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Dimensional.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/dimensional/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/dimensional/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
