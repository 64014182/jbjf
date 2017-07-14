package com.trading.mvc.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;
import com.platform.tools.ToolDateTime;
import com.trading.mvc.BigDecimalUtils;

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
	
	public void paymentReport(){
		Object startDateObj = splitPage.getQueryParam().get("startDate");
		Object endDateObj = splitPage.getQueryParam().get("endDate");
		Object orderUnitObj = splitPage.getQueryParam().get("orderUnit");
		if (startDateObj == null || endDateObj == null || orderUnitObj == null) {
			render("/trading/report/paymentReport.html");
			return;
		}
		paging(splitPage, "trading.report.paymentReportSelect", "trading.report.paymentReportFrom");
		
		String startDate = startDateObj.toString();
		try {
			Map<String, Object> param = new HashMap<>();
			String statDateBefore = ToolDateTime.getCalDate(startDate, "yyyy-MM-dd", -1);
			String januaryFirst = ToolDateTime.getJanuaryFirst(startDate, "yyyy-mm-dd");
			String year = ToolDateTime.getDateByStr("2017-07-13","yyyy");
			
			param.put("startDate", statDateBefore);
			param.put("endDate", januaryFirst);
			param.put("orderUnit", orderUnitObj);
			String orderUnitStr = "%" +orderUnitObj.toString() + "%";
			//期初余额 = 年期初余额(设置) + 自元月1日开始到查询起始日前一天的收付金额 – 销售结算金额
			String selectSql = getSqlMy("trading.report.paymentReportSum2");
			Record payAmoun = Db.findFirst(selectSql, januaryFirst,statDateBefore,orderUnitStr);//自元月1日开始到查询起始日前一天的收付金额
			
			selectSql = getSqlMy("trading.report.paymentReportYearAmount");
			Record yearAmount = Db.findFirst(selectSql, year);//年期初余额
			
			selectSql = getSqlMy("trading.report.paymentReportSalesSum");
			Record salesSumRecord = Db.findFirst(selectSql, startDateObj,endDateObj,orderUnitStr);//销售结算金额
			
			selectSql = getSqlMy("trading.report.paymentReportPaymetSum");
			Record paymentAmount = Db.findFirst(selectSql, startDateObj,endDateObj,orderUnitStr);//收付金额
			
			String yearSum = StringUtils.isEmpty(yearAmount.getStr("amount")) == true? "0":yearAmount.getStr("amount");
			String payemSum = StringUtils.isEmpty(payAmoun.getStr("amount")) == true? "0":payAmoun.getStr("amount");
			String salesSum = StringUtils.isEmpty(salesSumRecord.getStr("amount")) == true? "0":salesSumRecord.getStr("amount");
			String paymentSum = StringUtils.isEmpty(paymentAmount.getStr("amount")) == true? "0":paymentAmount.getStr("amount");
			//期初余额
			BigDecimal initBalance = BigDecimalUtils.getBidDecimal(yearSum).add(BigDecimalUtils.getBidDecimal(payemSum)).subtract(BigDecimalUtils.getBidDecimal(salesSum));
			setAttr("initBalance", yearSum + " + " + payemSum + " - " + salesSum + " = " + initBalance.toString());
			
			//期未余额 = 期初余额 + 收付金额 – 销售结算金额 – 发货明细未结金额
			BigDecimal endBalance = initBalance.add(BigDecimalUtils.getBidDecimal(paymentSum)).subtract(BigDecimalUtils.getBidDecimal(salesSum)).subtract(BigDecimalUtils.getBidDecimal("0"));
			setAttr("endBalance", initBalance + " + " + paymentSum + " - " + salesSum + " - " + "0" + " = " + endBalance.toString());
			render("/trading/report/paymentReport.html");
		} catch (ParseException e) {
			throw new RuntimeException("时间【" + startDate + "】转换失败！ errorMessage: " + e.getMessage());
		}
		
		String endDate = endDateObj.toString();
	}
	
	
}
