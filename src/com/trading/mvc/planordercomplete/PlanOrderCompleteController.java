package com.trading.mvc.planordercomplete;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.tools.ToolDateTime;
import com.trading.mvc.deliverydetailed.DeliveryDetailed;
import com.trading.mvc.excelinhistory.ExcelInHistoryService;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/planOrderComplete
 * /trading/planOrderComplete/save
 * /trading/planOrderComplete/edit
 * /trading/planOrderComplete/update
 * /trading/planOrderComplete/view
 * /trading/planOrderComplete/delete
 * /common/planOrderComplete/add.html
 * /trading/planOrderComplete/radioSelectDialog
 */
@Controller("/trading/planOrderComplete")
public class PlanOrderCompleteController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PlanOrderCompleteController.class);
	
	private PlanOrderCompleteService planOrderCompleteService;
	private ExcelInHistoryService excelInHistoryService;
	/**
	 * 列表
	 */
	public void index() {
		//setCurDateToQueryParam("cDate", ToolDateTime.pattern_yymm);
		//paging(ConstantInit.db_dataSource_main, splitPage, PlanOrderComplete.sqlId_splitPageSelect, PlanOrderComplete.sqlId_splitPageFrom);
		pagingSum(ConstantInit.db_dataSource_main, splitPage, PlanOrderComplete.sqlId_splitPageSelect, PlanOrderComplete.sqlId_splitPageFrom,"trading.planOrderComplete.splitPageSum");
		render("/trading/planOrderComplete/list.html");
	}
	
	public void getPlanOrderCompletes(){
		String sql = "SELECT *,CONCAT_WS('*',thickness,width,length) as spec FROM b_trading_planordercomplete LIMIT 1,10;";
		@SuppressWarnings("rawtypes")
		List datas = Db.find(sql);
		renderJson(datas);
	}
	
	/**
	 * 保存
	 */
	@Before(PlanOrderCompleteValidator.class)
	public void save() {
		getModel(PlanOrderComplete.class).save();
		forwardAction("/trading/planOrderComplete/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		PlanOrderComplete planOrderComplete = PlanOrderComplete.dao.findById(getPara());
		setAttr("planOrderComplete", planOrderComplete);
		render("/trading/planOrderComplete/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(PlanOrderCompleteValidator.class)
	public void update() {
		getModel(PlanOrderComplete.class).update();
		forwardAction("/trading/planOrderComplete/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		PlanOrderComplete planOrderComplete = PlanOrderComplete.dao.findById(getPara());
		setAttr("planOrderComplete", planOrderComplete);
		render("/trading/planOrderComplete/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		planOrderCompleteService.baseDelete(PlanOrderComplete.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/planOrderComplete/backOff");
	}
	
	/**
	 * 计划完成弹出框
	 */
	public void radioSelectDialog() {
		setAttr("settlementIds", getPara("settlementIds"));
		paging(ConstantInit.db_dataSource_main, splitPage, PlanOrderComplete.sqlId_splitPageSelect, PlanOrderComplete.sqlId_splitPageFrom);
		render("/trading/planOrderComplete/radioSelectDialog.html");
	}
	
	/**
	 * @throws Exception 
	 * */
	public void saveExcelData() throws Exception {
		UploadFile uploadFile = getFile();
		String indexKey = getPara("indexKey");
		int count = planOrderCompleteService.savePlanOrders(uploadFile,indexKey);
		excelInHistoryService.save(uploadFile, String.valueOf(count), "采购计划");
		redirect("/trading/planOrderComplete/");
	}
	
	/**
	 * 
	 */
	public void toImportPage() {
		String indexKey = getPara("indexKey");
		String action = getPara("action");
		if (StringUtils.isEmpty(action)) {
			action = "/platform/iedtd/saveExcelData";
		}
		setAttr("indexKey", indexKey);
		setAttr("action", action);
		render("/platform/iedtd/excelIn.html");
	}
	
	/**
	 * 计划完成弹出框
	 */
	public void settFromDialog() {
//		String invoiceNo = getPara("invoiceNo");
//		List<WiscoSettlement> wsList = WiscoSettlement.dao.findByColumnValue("settlementNo", settlementNo);	
//		setAttr("settlementNo", settlementNo);
//		setAttr("entitys", wsList);
//		render("/trading/wiscoSettlement/settFromDialog.html");
	}
}
