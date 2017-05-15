package com.test.mvc.cusinfo;

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
 * /test/cusinfo
 * /test/cusinfo/save
 * /test/cusinfo/edit
 * /test/cusinfo/update
 * /test/cusinfo/view
 * /test/cusinfo/delete
 * /common/cusinfo/add.html
 * 
 */
@Controller("/test/cusinfo")
public class CusinfoController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(CusinfoController.class);
	
	private CusinfoService cusinfoService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Cusinfo.sqlId_splitPageFrom);
		render("/test/cusinfo/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(CusinfoValidator.class)
	public void save() {
		getModel(Cusinfo.class).save();
		forwardAction("/test/cusinfo/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Cusinfo cusinfo = Cusinfo.dao.findById(getPara());
		setAttr("cusinfo", cusinfo);
		render("/test/cusinfo/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(CusinfoValidator.class)
	public void update() {
		getModel(Cusinfo.class).update();
		forwardAction("/test/cusinfo/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Cusinfo cusinfo = Cusinfo.dao.findById(getPara());
		setAttr("cusinfo", cusinfo);
		render("/test/cusinfo/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		cusinfoService.baseDelete(Cusinfo.table_name, getPara() == null ? ids : getPara());
		forwardAction("/test/cusinfo/backOff");
	}
	
}
