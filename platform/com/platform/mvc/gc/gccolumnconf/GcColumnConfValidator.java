package com.platform.mvc.gc.gccolumnconf;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class GcColumnConfValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GcColumnConfValidator.class);
	
	@SuppressWarnings("unused")
	private GcColumnConfService gcColumnConfService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/gcColumnConf/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/gcColumnConf/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(GcColumnConf.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/gcColumnConf/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/gcColumnConf/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
