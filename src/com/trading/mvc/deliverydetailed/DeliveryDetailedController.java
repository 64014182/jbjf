package com.trading.mvc.deliverydetailed;

import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;

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
	
	/**
	 * 列表
	 */
	public void index() {
//		setCurDateToQueryParam("contractMonth", ToolDateTime.pattern_yymm);
		String[] tags = getParaMap().get("_query.tag");
		deliveryDetailedService.pagin(ConstantInit.db_dataSource_main, splitPage, DeliveryDetailed.sqlId_splitPageSelect, DeliveryDetailed.sqlId_splitPageFrom,tags);
		//paging(ConstantInit.db_dataSource_main, splitPage, DeliveryDetailed.sqlId_splitPageSelect, DeliveryDetailed.sqlId_splitPageFrom);
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
		deliveryDetailedService.saveByExcel(uploadFile,sql,indexKey);
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
	 * trading/deliveryDetailed/procurementToExcel
	 * 入库导出
	 * @throws Exception
	 */
	public void procurementToExcel() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"procurement.xml","采购入库单");
		renderFile(new File(filePath));
	}
	
	/**
	 * /trading/deliveryDetailed/outToExcel
	 * 入库导出
	 * @throws Exception
	 */
	public void outToExcel() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"out.xml","采购出库单");
		renderFile(new File(filePath));
	}
	
	/**
	 * /trading/deliveryDetailed/pPut
	 * 入库导出
	 * @throws Exception
	 */
	public void pPut() throws Exception{
		String filePath = deliveryDetailedService.exportExcel(ids,"pOut.xml","出货单导入");
		renderFile(new File(filePath));
	}
}
