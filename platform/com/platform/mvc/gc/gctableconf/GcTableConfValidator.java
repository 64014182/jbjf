package com.platform.mvc.gc.gctableconf;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class GcTableConfValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GcTableConfValidator.class);
	
	@SuppressWarnings("unused")
	private GcTableConfService gcTableConfService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/gcTableConf/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/gcTableConf/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(GcTableConf.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/gcTableConf/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/gcTableConf/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
