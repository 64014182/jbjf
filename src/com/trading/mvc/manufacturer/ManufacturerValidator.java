package com.trading.mvc.manufacturer;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class ManufacturerValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ManufacturerValidator.class);
	
	@SuppressWarnings("unused")
	private ManufacturerService manufacturerService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/manufacturer/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/manufacturer/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Manufacturer.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/manufacturer/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/manufacturer/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
