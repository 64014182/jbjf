package com.trading.mvc.wiscosettlement;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class WiscoSettlementValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(WiscoSettlementValidator.class);
	
	@SuppressWarnings("unused")
	private WiscoSettlementService wiscoSettlementService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/wiscoSettlement/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/wiscoSettlement/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(WiscoSettlement.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/wiscoSettlement/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/wiscoSettlement/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
