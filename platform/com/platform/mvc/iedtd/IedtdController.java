package com.platform.mvc.iedtd;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolExcel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /platform/iedtd
 * /platform/iedtd/save
 * /platform/iedtd/edit
 * /platform/iedtd/update
 * /platform/iedtd/view
 * /platform/iedtd/delete
 * /common/iedtd/add.html
 * 
 */
@Controller("/platform/iedtd")
public class IedtdController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(IedtdController.class);
	
	private IedtdService iedtdService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Iedtd.sqlId_splitPageFrom);
		render("/platform/iedtd/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(IedtdValidator.class)
	public void save() {
		getModel(Iedtd.class).save();
		forwardAction("/platform/iedtd/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Iedtd iedtd = Iedtd.dao.findById(getPara());
		setAttr("iedtd", iedtd);
		render("/platform/iedtd/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(IedtdValidator.class)
	public void update() {
		getModel(Iedtd.class).update();
		forwardAction("/platform/iedtd/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Iedtd iedtd = Iedtd.dao.findById(getPara());
		setAttr("iedtd", iedtd);
		render("/platform/iedtd/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		iedtdService.baseDelete(Iedtd.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/iedtd/backOff");
	}
	
	/**
	 * 
	 */
	public void toImportPage() {
		String indexKey = getPara("indexKey");
		String action = getPara("action");
		String redirec = getPara("redirec");
		if (StringUtils.isEmpty(action)) {
			action = "/platform/iedtd/saveExcelData";
		}
		setAttr("indexKey", indexKey);
		setAttr("action", action);
		setAttr("redirec", redirec);
		render("/platform/iedtd/excelIn.html");
	}
	
	/**
	 * @throws Exception 
	 * */
	public void saveExcelData() throws Exception {
		UploadFile uploadFile = getFile();
		String indexKey = getPara("indexKey");
		String redirec = getPara("redirec");
		String sql = getSqlMy("platform.iedtd.getIedtdByIndexKey");
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();
		String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), "Sheet1", 3, true,ToolExcel.getColNo(columnsNo));
		Db.batch(insertSql, excelData, 100);
		if (StringUtils.isNotEmpty(redirec))
			redirect(redirec);
	}
}
