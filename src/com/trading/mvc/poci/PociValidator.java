package com.trading.mvc.poci;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class PociValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PociValidator.class);
	
	@SuppressWarnings("unused")
	private PociService pociService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/poci/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/poci/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Poci.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/poci/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/poci/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
