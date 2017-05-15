package com.trading.mvc.wiscosettlement;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.trading.mvc.salessettlement.SalesSettlement;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/wiscoSettlement/summary
 * /trading/wiscoSettlement/save
 * /trading/wiscoSettlement/edit
 * /trading/wiscoSettlement/update
 * /trading/wiscoSettlement/view
 * /trading/wiscoSettlement/delete
 * /common/wiscoSettlement/add.html
 * 
 */
@Controller("/trading/wiscoSettlement")
public class WiscoSettlementController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(WiscoSettlementController.class);
	
	private WiscoSettlementService wiscoSettlementService;
	
	/**
	 * 列表
	 */
	public void index() {
		String[] invoiceArray = getParaMap().get("_query.invoices");
		wiscoSettlementService.pagin(ConstantInit.db_dataSource_main, splitPage, WiscoSettlement.sqlId_splitPageSelect, WiscoSettlement.sqlId_splitPageFrom,invoiceArray);
		render("/trading/wiscoSettlement/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(WiscoSettlementValidator.class)
	public void save() {
		getModel(WiscoSettlement.class).save();
		forwardAction("/trading/wiscoSettlement/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		WiscoSettlement wiscoSettlement = WiscoSettlement.dao.findById(getPara());
		setAttr("wiscoSettlement", wiscoSettlement);
		render("/trading/wiscoSettlement/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(WiscoSettlementValidator.class)
	public void update() {
		getModel(WiscoSettlement.class).update();
		forwardAction("/trading/wiscoSettlement/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		WiscoSettlement wiscoSettlement = WiscoSettlement.dao.findById(getPara());
		setAttr("wiscoSettlement", wiscoSettlement);
		render("/trading/wiscoSettlement/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		wiscoSettlementService.baseDelete(WiscoSettlement.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/wiscoSettlement/backOff");
	}
	
	/**
	 * 计划完成弹出框
	 */
	public void settFromDialog() {
		String settlementNo = getPara("settlementNo");
		List<WiscoSettlement> wsList = WiscoSettlement.dao.findByColumnValue("settlementNo", settlementNo);	
		setAttr("settlementNo", settlementNo);
		setAttr("entitys", wsList);
		render("/trading/wiscoSettlement/settFromDialog.html");
	}
	
	public void excelIn() throws Exception{
		UploadFile uploadFile = getFile();
		String indexKey = getPara("indexKey");
		int countRecords = wiscoSettlementService.saveExcelDatas(uploadFile,indexKey);
		setAttr("countRecords", countRecords);
		redirect("/trading/wiscoSettlement");
	}
	
	public void saveSalesSetl()  {
		String settlementNo = getPara("settlementNo"); // 勾选的采购结算明细
		String invoiceNo = getPara("invoiceNo");
		SalesSettlement ssl = getModel(SalesSettlement.class);
		
		String err = "";
		try {
			wiscoSettlementService.saveSalesSetl(settlementNo, invoiceNo, ssl);
		} catch (Exception e) {
			err = e.getMessage();
		}
		
		if (StringUtils.isNotEmpty(err)) {
			renderJson("{\"message\":\"" + "保存失败！" +  err + "\"}");
		}else{
			renderJson("{\"message\":\"" + "保存成功！" + "\"}");
		}
	}
	
	public void summary() throws Exception{
		String orderUnit = getPara("orderUnit");
		String filePath = wiscoSettlementService.exportExcel(ids,orderUnit);
		renderFile(new File(filePath));
	}
	
	public void searchInvoice() {
		String invoiceText = getPara("invoiceText");
		String sqlId = "trading.wiscoSettlement.selectLikeWlByInvoice";
		invoiceText = "%" + invoiceText + "%";
		List<Record> list = Db.find(getSqlMy(sqlId), invoiceText);
		renderJson(list);
	}
	
	public static void main(String[] args) {
		String s = "370.00";
		String s1 = s.replaceAll(",", "");
		System.out.println(s1);
		BigDecimal bd = new BigDecimal(s1);
		System.out.println(bd);
	}
}
