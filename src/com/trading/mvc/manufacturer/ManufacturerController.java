package com.trading.mvc.manufacturer;

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
 * /trading/manufacturer
 * /trading/manufacturer/save
 * /trading/manufacturer/edit
 * /trading/manufacturer/update
 * /trading/manufacturer/view
 * /trading/manufacturer/delete
 * /common/manufacturer/add.html
 * 
 */
@Controller("/trading/manufacturer")
public class ManufacturerController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ManufacturerController.class);
	
	private ManufacturerService manufacturerService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Manufacturer.sqlId_splitPageFrom);
		render("/trading/manufacturer/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(ManufacturerValidator.class)
	public void save() {
		getModel(Manufacturer.class).save();
		forwardAction("/trading/manufacturer/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Manufacturer manufacturer = Manufacturer.dao.findById(getPara());
		setAttr("manufacturer", manufacturer);
		render("/trading/manufacturer/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(ManufacturerValidator.class)
	public void update() {
		getModel(Manufacturer.class).update();
		forwardAction("/trading/manufacturer/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Manufacturer manufacturer = Manufacturer.dao.findById(getPara());
		setAttr("manufacturer", manufacturer);
		render("/trading/manufacturer/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		manufacturerService.baseDelete(Manufacturer.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/manufacturer/backOff");
	}
	
}
