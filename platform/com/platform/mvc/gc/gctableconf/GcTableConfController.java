package com.platform.mvc.gc.gctableconf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseService;
import com.platform.mvc.operator.Operator;
import com.platform.tools.ToolString;

/**
 * XXX 管理	
 * 描述：
 * 
 * /platform/gcTableConf
 * /platform/gcTableConf/save
 * /platform/gcTableConf/edit
 * /platform/gcTableConf/update
 * /platform/gcTableConf/view
 * /platform/gcTableConf/delete
 * /common/gcTableConf/add.html
 * 
 */
@Controller("/platform/gcTableConf")
public class GcTableConfController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GcTableConfController.class);
	
	private GcTableConfService gcTableConfService;
	
	/**
	 * 列表
	 */
	public void index() {
		Map<String, Object> params = new HashMap<String,Object>();
		List<Record> tables = BaseService.find("platform.gcTableConf.showTables", params);
		setAttr("entits", tables);
		render("/platform/gcTableConf/list.html");
	}
	
	public void getTables() {
		String tablename = getPara();
		Record r = gcTableConfService.findByTablename(tablename);

		String renderUrl = "/platform/gcTableConf/add.html";
		if (r != null) {
			setAttr("gcTableConf", r);
			renderUrl = "/platform/gcTableConf/update.html";
		}
		render(renderUrl);
	}
	
	public void getTableColumns() {
		String tablename = getPara();
		List<Record> columns = gcTableConfService.findColumnsByTablename(tablename);
		setAttr("columns", columns);
		setAttr("tableName", tablename);
		render("/platform/gcColumnConf/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(GcTableConfValidator.class)
	public void save() {
		getModel(GcTableConf.class).save();
		forwardAction("/platform/gcTableConf/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		GcTableConf gcTableConf = GcTableConf.dao.findById(getPara());
		setAttr("gcTableConf", gcTableConf);
		render("/platform/gcTableConf/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(GcTableConfValidator.class)
	public void update() {
		getModel(GcTableConf.class).update();
		forwardAction("/platform/gcTableConf/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		GcTableConf gcTableConf = GcTableConf.dao.findById(getPara());
		setAttr("gcTableConf", gcTableConf);
		render("/platform/gcTableConf/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		gcTableConfService.baseDelete(GcTableConf.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/gcTableConf/backOff");
	}
	
	/**
	 * 生成界面
	 */
	public void generatePage() {
		String tableName = getPara();
		Record table = gcTableConfService.findByTablename(tableName);
		List<Record> columnConfs = gcTableConfService.findGcColumnsByTablename(tableName);
		String path = gcTableConfService.generatePage(table, columnConfs);
		System.out.println(path);
		renderJson("生成界面成功！  " + path);
	}
	
	public void generateOperatorPage() {
		String tableName = getPara();
		setAttr("tableName", tableName);
		render("/platform/gcTableConf/operatorConfig.html");
	}
	
	public void saveOperator() {
		String modulenames = getPara("modulenames");
		String moduleids = getPara("moduleids");
		String tableName = getPara("tableName");
		
		GcTableConf cpci = getModel(GcTableConf.class).findFirst("SELECT * FROM pt_fun_gc_tableconf WHERE tablename = ?", tableName);
		String classname = cpci.getClassname();
		classname = ToolString.toLowerCaseFirstOne(classname);
		Map<String, String> map = new HashMap<String, String>();
		String basePath = cpci.getPackagename();
		basePath = basePath.split("\\.")[1];
		map.put("列表", "/" + basePath + "/" + classname);
		map.put("保存", "/" + basePath + "/" + classname + "/save");
		map.put("删除", "/" + basePath + "/" + classname + "/delete");
		map.put("添加", "/" + basePath + "/" + classname + "/add.html");
		map.put("编辑", "/" + basePath + "/" + classname + "/edit");
		map.put("更新", "/" + basePath + "/" + classname + "/update");
		map.put("查看", "/" + basePath + "/" + classname + "/view");
		
		Operator o;
		for (String key : map.keySet()) {
			o = new Operator();
			o.setModuleids(moduleids);
			o.setModulenames(modulenames);
			o.setNames(key);
			o.setUrl(map.get(key));
			String splitPage = "列表".equals(key) == true ? "1" : "0";
			o.setSplitpage(splitPage);
			o.setRowfilter("0");
			o.setFormtoken("0");
			o.setIpblack("0");
			o.setReferer("0");
			o.setCsrf("");
			o.setMethod("0");
			o.setSyslog("1");
			o.setEnctype("0");
			o.setPrivilegess("1");
			o.save();
		}
		render("/platform/gcTableConf/operatorConfig.html");
	}
}
