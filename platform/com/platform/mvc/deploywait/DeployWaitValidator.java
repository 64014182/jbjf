package com.platform.mvc.deploywait;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class DeployWaitValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeployWaitValidator.class);
	
	@SuppressWarnings("unused")
	private DeployWaitService deployWaitService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/deployWait/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/platform/deployWait/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(DeployWait.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/platform/deployWait/save")){
			controller.render("/platform/xxx.html");
		
		} else if (actionKey.equals("/platform/deployWait/update")){
			controller.render("/platform/xxx.html");
		
		}
	}
	
}
