package com.trading.mvc.salessettlement;

import java.io.File;
import java.io.IOException;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.trading.mvc.wiscosettlement.WiscoSettlementService;

import freemarker.template.TemplateException;

/**
 * 销售订单
 * 描述：
 * 
 * /trading/salesSettlement
 * /trading/salesSettlement/save
 * /trading/salesSettlement/edit
 * /trading/salesSettlement/update
 * /trading/salesSettlement/view
 * /trading/salesSettlement/delete
 * /trading/salesSettlement/add.html
 * /trading/salesSettlement/saveSalesSetl
 * 
 */
@Controller("/trading/salesSettlement")
public class SalesSettlementController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesSettlementController.class);
	
	private SalesSettlementService salesSettlementService;
	private WiscoSettlementService wiscoSettlementService;
	/**
	 * 列表
	 */
	public void index() {
		String[] invoiceArray = getParaMap().get("_query.invoices");
		salesSettlementService.pagin(ConstantInit.db_dataSource_main, splitPage, SalesSettlement.sqlId_splitPageSelect, SalesSettlement.sqlId_splitPageFrom,invoiceArray);
		render("/trading/salesSettlement/list.html");
	}
	
	public void saveOther() {
		SalesSettlement ss = getModel(SalesSettlement.class);
		wiscoSettlementService.saveSalesSettleOther(ss);
		redirect("/trading/salesSettlement");
	}
	
	/**
	 * 保存
	 */
	@Before(SalesSettlementValidator.class)
	public void save() {
		getModel(SalesSettlement.class).save();
		forwardAction("/trading/salesSettlement/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		SalesSettlement salesSettlement = SalesSettlement.dao.findById(getPara());
		setAttr("salesSettlement", salesSettlement);
		render("/trading/salesSettlement/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(SalesSettlementValidator.class)
	public void update() {
		getModel(SalesSettlement.class).update();
		forwardAction("/trading/salesSettlement/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		SalesSettlement salesSettlement = SalesSettlement.dao.findById(getPara());
		setAttr("salesSettlement", salesSettlement);
		render("/trading/salesSettlement/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		salesSettlementService.baseDelete(SalesSettlement.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/salesSettlement/backOff");
	}
	
	/**
	 * /trading/salesSettlement/summary
	 * 销售统计
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void summary() throws Exception {
		String orderUnit = getPara("orderUnit");
		String filePath = salesSettlementService.exportExcel(ids, orderUnit);
		renderFile(new File(filePath));
	}
}
