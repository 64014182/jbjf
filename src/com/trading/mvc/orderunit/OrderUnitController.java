package com.trading.mvc.orderunit;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;
import com.jfinal.aop.Before;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/orderUnit
 * /trading/orderUnit/save
 * /trading/orderUnit/edit
 * /trading/orderUnit/update
 * /trading/orderUnit/view
 * /trading/orderUnit/delete
 * /common/orderUnit/add.html
 * 
 */
@Controller("/trading/orderUnit")
public class OrderUnitController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(OrderUnitController.class);
	
	private OrderUnitService orderUnitService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, OrderUnit.sqlId_splitPageFrom);
		render("/trading/orderUnit/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(OrderUnitValidator.class)
	public void save() {
		getModel(OrderUnit.class).save();
		forwardAction("/trading/orderUnit/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		OrderUnit orderUnit = OrderUnit.dao.findById(getPara());
		setAttr("orderUnit", orderUnit);
		render("/trading/orderUnit/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(OrderUnitValidator.class)
	public void update() {
		getModel(OrderUnit.class).update();
		forwardAction("/trading/orderUnit/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		OrderUnit orderUnit = OrderUnit.dao.findById(getPara());
		setAttr("orderUnit", orderUnit);
		render("/trading/orderUnit/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		orderUnitService.baseDelete(OrderUnit.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/orderUnit/backOff");
	}
	
}
