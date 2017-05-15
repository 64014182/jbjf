package com.platform.mvc.gc.gccolumnconf;

import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /platform/gcColumnConf
 * /platform/gcColumnConf/save
 * /platform/gcColumnConf/edit
 * /platform/gcColumnConf/update
 * /platform/gcColumnConf/view
 * /platform/gcColumnConf/delete
 * /common/gcColumnConf/add.html
 * 
 */
@Controller("/platform/gcColumnConf")
public class GcColumnConfController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GcColumnConfController.class);
	
	private GcColumnConfService gcColumnConfService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, GcColumnConf.sqlId_splitPageFrom);
		render("/platform/gcColumnConf/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(GcColumnConfValidator.class)
	public void save() {
		GcColumnConf gcc = getModel(GcColumnConf.class);
		gcc.save();
		redirect("/platform/gcTableConf/getTableColumns/" + gcc.getTablename());
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		GcColumnConf gcColumnConf = GcColumnConf.dao.findById(getPara());
		setAttr("gcColumnConf", gcColumnConf);
		render("/platform/gcColumnConf/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(GcColumnConfValidator.class)
	public void update() {
		GcColumnConf gcc = getModel(GcColumnConf.class);
		gcc.update();
		redirect("/platform/gcTableConf/getTableColumns/" + gcc.getTablename());
	}

	/**
	 * 查看
	 */
	public void view() {
		GcColumnConf gcColumnConf = GcColumnConf.dao.findById(getPara());
		setAttr("gcColumnConf", gcColumnConf);
		render("/platform/gcColumnConf/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		gcColumnConfService.baseDelete(GcColumnConf.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/gcColumnConf/backOff");
	}
	
	/**
	 * 
	 */
	public void columnConfInit() {
		Map<String, String> paras = getParamMap();
		String tablename = paras.get("tableName");
		String columnname = paras.get("columnName");

		GcColumnConf gcc = null;
		List<GcColumnConf> list = getModel(GcColumnConf.class).find(getSqlMy("platform.gcColumnConf.selectPageCounfByTnameAndCname"), tablename,columnname);
		if (list.size() > 0) {
			gcc = list.get(0);
			setAttr("action", "/platform/gcColumnConf/update");
		} else {
			gcc = getModel(GcColumnConf.class);
			gcc.setTablename(tablename);
			gcc.setColumnname(columnname);
			setAttr("action", "/platform/gcColumnConf/save");
		}
		setAttr("tablename", tablename);
		setAttr("gcColumnConf", gcc);
		render("/platform/gcColumnConf/add.html");
	}
	
}
