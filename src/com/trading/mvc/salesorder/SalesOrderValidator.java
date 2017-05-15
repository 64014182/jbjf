package com.trading.mvc.salesorder;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class SalesOrderValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesOrderValidator.class);
	
	@SuppressWarnings("unused")
	private SalesOrderService salesOrderService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/salesOrder/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/salesOrder/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(SalesOrder.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/salesOrder/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/salesOrder/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
