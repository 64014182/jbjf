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
import com.platform.tools.ToolDateTime;
import com.trading.mvc.deliverydetailed.DeliveryDetailed;
import com.trading.mvc.excelinhistory.ExcelInHistoryService;
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
	private ExcelInHistoryService excelInHistoryService;
	/**
	 * 列表
	 */
	public void index() {
		//String[] invoiceArray = getParaMap().get("_query.invoices");
		//pagin(ConstantInit.db_dataSource_main, splitPage, WiscoSettlement.sqlId_splitPageSelect, WiscoSettlement.sqlId_splitPageFrom,"trading.deliveryDetailed.splitPageSum");
		pagingSum(ConstantInit.db_dataSource_main, splitPage, WiscoSettlement.sqlId_splitPageSelect, WiscoSettlement.sqlId_splitPageFrom,"trading.wiscoSettlement.splitPageSum");
		render("/trading/wiscoSettlement/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(WiscoSettlementValidator.class)
	public void save() {
		String salesAddPrice = getPara("salesAddPrice");
		String salesWeight = getPara("salesWeight");
		String quantity = getPara("quantity");
		WiscoSettlement ws = getModel(WiscoSettlement.class);
		wiscoSettlementService.save(ws, salesAddPrice, salesWeight,quantity);
		forwardAction("/trading/wiscoSettlement/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		WiscoSettlement wiscoSettlement = WiscoSettlement.dao.findById(getPara());
		String orderItemNo = wiscoSettlement.getOrderItemNo();
		SalesSettlement ss = SalesSettlement.dao.findFirstByColumnValue("orderItemNo", orderItemNo);
		DeliveryDetailed dd = DeliveryDetailed.dao.findFirstByColumnValue("orderItemNo", orderItemNo);
		String salesAddPrice = ss.getAddPrice();
		String salesWeight = ss.getWeight();
		String quantity = dd.getQuantity();
		setAttr("wiscoSettlement", wiscoSettlement);
		setAttr("salesAddPrice", salesAddPrice);
		setAttr("quantity", quantity);
		setAttr("salesWeight", salesWeight);
		render("/trading/wiscoSettlement/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(WiscoSettlementValidator.class)
	public void update() {
		String salesAddPrice = getPara("salesAddPrice");
		String salesWeight = getPara("salesWeight");
		String quantity = getPara("quantity");
		WiscoSettlement ws = getModel(WiscoSettlement.class);
		wiscoSettlementService.update(ws, salesAddPrice, salesWeight, quantity);
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
	//	wiscoSettlementService.baseDelete(WiscoSettlement.table_name, getPara() == null ? ids : getPara());
		wiscoSettlementService.del(getPara() == null ? ids : getPara());
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
	
	/**
	 * 追溯弹出框
	 * @throws Exception
	 */
	public void traceDialog() {
		setAttr("selIds", ids);
		render("/trading/wiscoSettlement/traceDialog.html");
	}
	
	/**
	 * 追溯
	 */
	public void trace(){
		String selIds = getPara("selIds");				//所选结算明细
		String invoiceNo = getPara("invoiceNo");		//追溯发票号
		String traceRange = getPara("traceRange");		//追溯幅度
		String docNo = getPara("docNo");
		
		wiscoSettlementService.saveTrace(selIds,invoiceNo,traceRange,docNo);
		redirect("/trading/deliveryDetailed/");
	}
	
	public void excelIn() throws Exception{
		UploadFile uploadFile = getFile();
		String indexKey = getPara("indexKey");
		String dtype = getPara("dtype");
		if (StringUtils.isEmpty(indexKey) || StringUtils.isEmpty(dtype)) {
			throw new RuntimeException("indexKey或dtype不能为空！");
		}
		String currentTime = ToolDateTime.getCurrent(ToolDateTime.pattern_yymmdd);
		int countRecords = wiscoSettlementService.saveExcelDatas(uploadFile,indexKey,dtype,currentTime);
		excelInHistoryService.save(uploadFile, String.valueOf(countRecords), "采购结算明细",currentTime);
		setAttr("countRecords", countRecords);
		redirect("/trading/wiscoSettlement/");
	}
	
	public void saveSalesSetl() {
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
			renderJson("{\"message\":\"" + "保存失败！" + err + "\"}");
		} else {
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
