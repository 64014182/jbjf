package com.trading.mvc.salessettlement;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class SalesSettlementValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesSettlementValidator.class);
	
	@SuppressWarnings("unused")
	private SalesSettlementService salesSettlementService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/salesSettlement/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/salesSettlement/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(SalesSettlement.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/salesSettlement/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/salesSettlement/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
