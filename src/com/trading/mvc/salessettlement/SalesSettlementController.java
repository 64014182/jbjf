package com.trading.mvc.salessettlement;

import java.io.File;
import java.io.IOException;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
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
		pagingSum(ConstantInit.db_dataSource_main, splitPage, "trading.salesSettlement.splitPageSelect", SalesSettlement.sqlId_splitPageFrom,"trading.salesSettlement.splitPageSum");
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
//		SalesSettlement salesSettlement = SalesSettlement.dao.findById(getPara());
		Record r = Db.findFirst(getSqlMy("trading.salesSettlement.findbyid"), getPara());
		setAttr("salesSettlement", r);
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
	 * 更新
	 */
	@Before(SalesSettlementValidator.class)
	public void update2() {
		String orderUnit = getPara("orderUnit");
		String noTaxPrice = getPara("noTaxPrice");
		SalesSettlement ss = getModel(SalesSettlement.class);
		salesSettlementService.save2(ss.getIds(), orderUnit, noTaxPrice);
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
	 * 销售结算表管理 Excel导出
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void summary() throws Exception {
		String orderUnit = getPara("orderUnit");
		String filePath = salesSettlementService.exportExcel(ids, orderUnit);
		renderFile(new File(filePath));
	}
	
	/**
	 * /trading/salesSettlement/updateFlag
	 * 销售结算表管理 开发票并导出
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void updateFlag() throws Exception {
		String orderUnit = getPara("orderUnit");
		String filePath = salesSettlementService.updateFlag(ids, orderUnit);
		renderFile(new File(filePath));
	}
}
