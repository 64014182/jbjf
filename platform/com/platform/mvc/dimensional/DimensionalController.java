package com.platform.mvc.dimensional;

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
 * /platform/dimensional
 * /platform/dimensional/save
 * /platform/dimensional/edit
 * /platform/dimensional/update
 * /platform/dimensional/view
 * /platform/dimensional/delete
 * /common/dimensional/add.html
 * 
 */
@Controller("/platform/dimensional")
public class DimensionalController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DimensionalController.class);
	
	private DimensionalService dimensionalService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Dimensional.sqlId_splitPageFrom);
		render("/platform/dimensional/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(DimensionalValidator.class)
	public void save() {
		getModel(Dimensional.class).save();
		forwardAction("/platform/dimensional/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Dimensional dimensional = Dimensional.dao.findById(getPara());
		setAttr("dimensional", dimensional);
		render("/platform/dimensional/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(DimensionalValidator.class)
	public void update() {
		getModel(Dimensional.class).update();
		forwardAction("/platform/dimensional/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Dimensional dimensional = Dimensional.dao.findById(getPara());
		setAttr("dimensional", dimensional);
		render("/platform/dimensional/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		dimensionalService.baseDelete(Dimensional.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/dimensional/backOff");
	}
	
}
