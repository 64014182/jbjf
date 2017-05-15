package com.trading.mvc.salesorder;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolUuid;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/salesOrder
 * /trading/salesOrder/save
 * /trading/salesOrder/edit
 * /trading/salesOrder/update
 * /trading/salesOrder/view
 * /trading/salesOrder/delete
 * /common/salesOrder/add.html
 * 
 */
@Controller("/trading/salesOrder")
public class SalesOrderController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesOrderController.class);
	
	private SalesOrderService salesOrderService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, SalesOrder.sqlId_splitPageFrom);
		render("/trading/salesOrder/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(SalesOrderValidator.class)
	public void save() {
		getModel(SalesOrder.class).save();
		forwardAction("/trading/planOrderComplete");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		SalesOrder salesOrder = SalesOrder.dao.findById(getPara());
		setAttr("salesOrder", salesOrder);
		render("/trading/salesOrder/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(SalesOrderValidator.class)
	public void update() {
		getModel(SalesOrder.class).update();
		forwardAction("/trading/planOrderComplete");
	}

	/**
	 * 查看
	 */
	public void view() {
		SalesOrder salesOrder = SalesOrder.dao.findById(getPara());
		setAttr("salesOrder", salesOrder);
		render("/trading/salesOrder/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		salesOrderService.baseDelete(SalesOrder.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/salesOrder/backOff");
	}
	
	/**
	 * 形成销售订单
	 */
//	public void generateSalesOrder() {
//		String declareplanIds = getPara("planOrderCompleteIds");
//		PlanOrderComplete poc = getModel(PlanOrderComplete.class).findById(declareplanIds);
//		SalesOrder so = getModel(SalesOrder.class).findFirst(getSqlMy("trading.salesOrder.selectByPocIds"), declareplanIds);
//		setAttr("poc", poc);
//		setAttr("salesOrder", so);
//		if (null == so)
//			setAttr("formUrl", "trading/salesOrder/save");
//		else
//			setAttr("formUrl", "trading/salesOrder/update");
//		render("/trading/salesOrder/add.html");
//	}
	
	/**
	 * 形成销售订单
	 */
	@Before(POST.class)
	public void generateSalesOrder() {
		SalesOrder so = (SalesOrder) getJson(SalesOrder.class);
		SalesOrder entity = getModel(SalesOrder.class);
		//PlanOrderComplete poc = getModel(PlanOrderComplete.class).dao().findFirst("SELECT orderItemNo FROM b_trading_planordercomplete where ids = ?",so.getPocIds());
		
		if(StringUtils.isNoneEmpty(so.getIds())){
			entity.setIds(so.getIds());
			entity.setOrderUnit(so.getOrderUnit());
			entity.setManufacturer(so.getManufacturer());
			entity.setSalesOrderNo(so.getSalesOrderNo());
			entity.setSalesPrice(so.getSalesPrice());
			entity.setStorag(so.getStorag());
			entity.setOther(so.getOther());
			entity.setFreightage(so.getFreightage());
			entity.setPocIds(so.getPocIds());
//			entity.setManufacturerId(so.getManufacturer());
//			entity.setOrderUnitId(so.getSalesOrderNo());
			entity.update();
		}
		else{
			so.setIds(ToolUuid.get32UUID());
			//so.setOrderItemNo(poc.getOrderItemNo());
			so.save();
		}
		renderJson("{\"message\":\"" + so.getIds() + "\"}");
	}
}
