package com.trading.mvc.deliverydetailed;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.tools.ToolDateTime;
import com.trading.mvc.TradingConst;
import com.trading.mvc.excelinhistory.ExcelInHistoryService;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/deliveryDetailed
 * /trading/deliveryDetailed/save
 * /trading/deliveryDetailed/edit
 * /trading/deliveryDetailed/update
 * /trading/deliveryDetailed/view
 * /trading/deliveryDetailed/delete
 * /common/deliveryDetailed/add.html
 * 
 */
@Controller("/trading/deliveryDetailed")
public class DeliveryDetailedController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeliveryDetailedController.class);
	
	private DeliveryDetailedService deliveryDetailedService;
	private ExcelInHistoryService excelInHistoryService;
	/**
	 * 列表
	 */
	public void index() {
		pagingSum(ConstantInit.db_dataSource_main, splitPage, DeliveryDetailed.sqlId_splitPageSelect, DeliveryDetailed.sqlId_splitPageFrom,"trading.deliveryDetailed.splitPageSum");
		render("/trading/deliveryDetailed/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(DeliveryDetailedValidator.class)
	public void save() {
		getModel(DeliveryDetailed.class).save();
		forwardAction("/trading/deliveryDetailed/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		DeliveryDetailed deliveryDetailed = DeliveryDetailed.dao.findById(getPara());
		setAttr("deliveryDetailed", deliveryDetailed);
		render("/trading/deliveryDetailed/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(DeliveryDetailedValidator.class)
	public void update() {
		getModel(DeliveryDetailed.class).update();
		forwardAction("/trading/deliveryDetailed/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		DeliveryDetailed deliveryDetailed = DeliveryDetailed.dao.findById(getPara());
		setAttr("deliveryDetailed", deliveryDetailed);
		render("/trading/deliveryDetailed/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		deliveryDetailedService.baseDelete(DeliveryDetailed.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/deliveryDetailed/backOff");
	}
	
	/**
	 * /trading/deliveryDetailed/excelInport
	 * @throws Exception 
	 * */
	public void excelInport() throws Exception {
		UploadFile uploadFile = getFile();
		String indexKey = getPara("indexKey");
		String sql = getSqlMy("platform.iedtd.getIedtdByIndexKey");
		String dtype = getPara("dtype");
		
		if (StringUtils.isEmpty(indexKey) || StringUtils.isEmpty(dtype)) {
			throw new RuntimeException("indexKey或dtype不能为空！");
		}
		String currentTime = ToolDateTime.getCurrent(ToolDateTime.pattern_yymmdd);
		int countRecords = deliveryDetailedService.saveByExcel(uploadFile,sql,indexKey,dtype,currentTime);
		excelInHistoryService.save(uploadFile, String.valueOf(countRecords), "采购发货明细",currentTime);
		redirect("/trading/deliveryDetailed");
	}
	
	public static void main(String args[]){
		int[] a = { 0, 1, 2, 3, 4, 5, 6 };
		int[] b = new int[10] ;
		b[a.length] = 7;
		b[a.length + 1] = 8;
		b[a.length + 2] = 9;
		System.arraycopy(a, 0, b, 0, a.length);
		
		System.out.println(b);
	}

	/**
	 * 发货明细入库
	 * @throws Exception
	 */
	public void in() throws Exception {
		deliveryDetailedService.updateState(DeliveryDetailed.table_name, getPara() == null ? ids : getPara(), "1");
		forwardAction("/trading/deliveryDetailed/backOff");
	}
	
	/**
	 * 发货明细出库
	 * @throws Exception
	 */
	public void out() throws Exception {
		deliveryDetailedService.updateState(DeliveryDetailed.table_name, getPara() == null ? ids : getPara(), "2");
		forwardAction("/trading/deliveryDetailed/backOff");
	}
	
	/**
	 * trading/deliveryDetailed/procurementToExcel
	 * 入库导出
	 * @throws Exception
	 */
	public void procurementToExcel() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"procurement.xml","采购入库单",TradingConst.DeliveryDetailed_In_Ex);
		renderFile(new File(filePath));
	}
	
	/**
	 * /trading/deliveryDetailed/outToExcel
	 * 出库导出
	 * @throws Exception
	 */
	public void outToExcel() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"out.xml","采购出库单",TradingConst.DeliveryDetailed_Out_Ex);
		renderFile(new File(filePath));
	}
	
	/**
	 * /trading/deliveryDetailed/pPut
	 * 入库导出
	 * @throws Exception
	 */
	public void pPut() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"pOut.xml","出货单导出",null);
		renderFile(new File(filePath));
	}
	
	/**
	 * 结算弹出框
	 * @throws Exception
	 */
	public void settleDialog() {
		setAttr("selIds", ids);
		render("/trading/deliveryDetailed/settleDialog.html");
	}
	
	/**
	 * 结算
	 */
	public void settle(){
		String selIds = getPara("selIds");				//所选结算明细
		String invoiceNo = getPara("invoiceNo");		//发票号
		String err = "";
		try {
			deliveryDetailedService.saveSettle(selIds,invoiceNo);
			
		} catch (Exception e) {
			err = e.getMessage();
		}
		if (StringUtils.isNotEmpty(err)) {
			renderJson("{\"message\":\"" + "保存失败！" +  err + "\"}");
		}else{
			renderJson("{\"message\":\"" + "保存成功！" + "\"}");
		}
	}
}
