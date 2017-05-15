package com.trading.mvc.declareplan;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;
import com.jfinal.aop.Before;

/**
 * 每月订货计划 管理	
 * 描述：
 * 
 * /trading/declareplan
 * /trading/declareplan/save
 * /trading/declareplan/edit
 * /trading/declareplan/update
 * /trading/declareplan/view
 * /trading/declareplan/delete
 * /common/declareplan/add.html
 * 
 */
@Controller("/trading/declareplan")
public class DeclareplanController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeclareplanController.class);
	
	private DeclareplanService declareplanService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Declareplan.sqlId_splitPageFrom);
		render("/trading/declareplan/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(DeclareplanValidator.class)
	public void save() {
		getModel(Declareplan.class).save();
		forwardAction("/trading/declareplan/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Declareplan declareplan = Declareplan.dao.findById(getPara());
		setAttr("declareplan", declareplan);
		render("/trading/declareplan/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(DeclareplanValidator.class)
	public void update() {
		getModel(Declareplan.class).update();
		forwardAction("/trading/declareplan/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Declareplan declareplan = Declareplan.dao.findById(getPara());
		setAttr("declareplan", declareplan);
		render("/trading/declareplan/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		declareplanService.baseDelete(Declareplan.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/declareplan/backOff");
	}
	
}
