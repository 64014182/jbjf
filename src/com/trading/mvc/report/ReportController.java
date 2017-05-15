package com.trading.mvc.report;

import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;

/**
 * 报表 管理 描述：
 */
@Controller("/trading/report")
public class ReportController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ReportController.class);

	/**
	 * 发货未结算报表
	 */
	public void fhn() {
		paging(splitPage, "trading.report.fhnSelect", "trading.report.fhnFrom");
		render("/trading/report/fhn.html");
	}
	
	/**
	 * 追溯数据查询
	 */
	public void ret() {
		paging(splitPage, "trading.report.retSelect", "trading.report.retFrom");
		render("/trading/report/ret.html");
	}
	
	/**
	 * 应收账款明细查询
	 */
	public void paymentPage() {
		render("/trading/report/paymentPage.html");
	}
	/**
	 * 应收账款明细查询
	 */
	public void payment() {
		paging(splitPage, "trading.report.paymentSelect", "trading.report.paymentFrom");
		render("/trading/report/payment.html");
	}
}
