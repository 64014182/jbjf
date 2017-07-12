package com.platform.mvc.dycomponent;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /platform/dyComponent/ajaxSelcet2
 * /platform/dyComponent/save
 * /platform/dyComponent/edit
 * /platform/dyComponent/update
 * /platform/dyComponent/view
 * /platform/dyComponent/delete
 * /common/dyComponent/add.html
 * /common/dyComponent/ajaxDyComponent
 * 
 */
@Controller("/platform/dyComponent")
public class DyComponentController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DyComponentController.class);
	
	private DyComponentService dyComponentService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, DyComponent.sqlId_splitPageFrom);
		render("/platform/dyComponent/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(DyComponentValidator.class)
	public void save() {
		getModel(DyComponent.class).save();
		forwardAction("/platform/dyComponent/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		DyComponent dyComponent = DyComponent.dao.findById(getPara());
		setAttr("dyComponent", dyComponent);
		render("/platform/dyComponent/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(DyComponentValidator.class)
	public void update() {
		getModel(DyComponent.class).update();
		forwardAction("/platform/dyComponent/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		DyComponent dyComponent = DyComponent.dao.findById(getPara());
		setAttr("dyComponent", dyComponent);
		render("/platform/dyComponent/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		dyComponentService.baseDelete(DyComponent.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/dyComponent/backOff");
	}
	
	public void ajaxDyComponent() {
		String sqlKey = getPara();
		Record r = Db.findFirst("SELECT * FROM pt_fun_dycomponent where sqlkey = ?", sqlKey);
		String sql = r.getStr("sqlvalue");
		List<Record> datas = Db.find(sql);
		renderJson(datas);
	}
	
	public void ajaxSelcet2() throws UnsupportedEncodingException {
		Map<String, String> map = getParamMap();
		String sqlIndex = map.get("si");
		String param = map.get("q");
		Record r = Db.findFirst("SELECT * FROM pt_fun_dycomponent where sqlkey = ?", sqlIndex);
		String sql = r.getStr("sqlvalue");
		param = URLDecoder.decode(param,"UTF-8");
		sql = "SELECT ids AS value, NAME AS text FROM b_trading_manufacturer where name LIKE '%" + param + "%'";
		List<Record> datas = Db.find(sql);
		renderJson(datas);
	}
}
