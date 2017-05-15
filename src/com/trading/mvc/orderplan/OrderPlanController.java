package com.trading.mvc.orderplan;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * 订货计划完成情况，武钢系统下载EXCEL导入
 * 描述：
 * 
 * /trading/orderPlan
 * /trading/orderPlan/save
 * /trading/orderPlan/edit
 * /trading/orderPlan/update
 * /trading/orderPlan/view
 * /trading/orderPlan/delete
 * /common/orderPlan/add.html
 * 
 */
@Controller("/trading/orderPlan")
public class OrderPlanController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderPlanController.class);
	
	private OrderPlanService orderPlanService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, OrderPlan.sqlId_splitPageFrom);
		render("/trading/orderPlan/list.html");
	}
	
	/**
	 * 列表
	 */
	public void radioSelectDialog() {
		setAttr("declareplanIds", getPara("declareplanIds"));
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, OrderPlan.sqlId_splitPageFrom);
		render("/trading/orderPlan/radioSelectDialog.html");
	}
	
	/**
	 * 形成销售订单
	 */
	public void generateSalesOrder() {
//		String declareplanIds = getPara("declareplanIds");
//		String olderPlanIds = getPara("olderPlanIds");
//		Declareplan dp = getModel(Declareplan.class).findById(declareplanIds);
//		OrderPlan op = getModel(OrderPlan.class).findById(olderPlanIds);
//		
//		SalesOrder so = getModel(SalesOrder.class);
//		so.setOrderUnit(dp.getOrderUnit());
//		so.setPName(op.getPName());
//		so.setGrade(op.getGrade());
//		String length = op.getLength();
//		if (null != length || !"0".equals(length)) {
//			so.setSpecification(op.getThickness() + "*" + op.getWidth() + "*" + op.getLength());
//		}
//		//so.setCount(dp.getcou);
//		so.setWeight(op.getItemWeight());
//		so.setManufacturer(dp.getManufacturer());
//		so.setOlderPlanIds(olderPlanIds);
//		so.setStandard(op.getStandard());
//		so.save();
//		redirect("/trading/declareplan");
	}
	
	/**
	 * 保存
	 */
	@Before(OrderPlanValidator.class)
	public void save() {
		getModel(OrderPlan.class).save();
		forwardAction("/trading/orderPlan/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		OrderPlan orderPlan = OrderPlan.dao.findById(getPara());
		setAttr("orderPlan", orderPlan);
		render("/trading/orderPlan/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(OrderPlanValidator.class)
	public void update() {
		getModel(OrderPlan.class).update();
		forwardAction("/trading/orderPlan/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		OrderPlan orderPlan = OrderPlan.dao.findById(getPara());
		setAttr("orderPlan", orderPlan);
		render("/trading/orderPlan/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		orderPlanService.baseDelete(OrderPlan.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/orderPlan/backOff");
	}
	
}
