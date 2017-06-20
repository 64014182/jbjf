package com.trading.mvc.excelinhistory;

import com.jfinal.log.Log;
import com.jfinal.core.Controller;

import com.platform.mvc.base.BaseValidator;

public class ExcelInHistoryValidator extends BaseValidator {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ExcelInHistoryValidator.class);
	
	@SuppressWarnings("unused")
	private ExcelInHistoryService excelInHistoryService;
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/excelInHistory/save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals("/trading/excelInHistory/update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(ExcelInHistory.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/trading/excelInHistory/save")){
			controller.render("/trading/xxx.html");
		
		} else if (actionKey.equals("/trading/excelInHistory/update")){
			controller.render("/trading/xxx.html");
		
		}
	}
	
}
