package com.trading.mvc.excelinhistory;

import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;

import java.io.File;

import com.jfinal.aop.Before;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/excelInHistory
 * /trading/excelInHistory/save
 * /trading/excelInHistory/edit
 * /trading/excelInHistory/update
 * /trading/excelInHistory/view
 * /trading/excelInHistory/delete
 * /common/excelInHistory/add.html
 * 
 */
@Controller("/trading/excelInHistory")
public class ExcelInHistoryController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ExcelInHistoryController.class);
	
	private ExcelInHistoryService excelInHistoryService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, ExcelInHistory.sqlId_splitPageFrom);
		render("/trading/excelInHistory/list.html");
	}
	
	/**
	 * 下载文件
	 */
	public void download() {
		ExcelInHistory excelInHistory = ExcelInHistory.dao.findById(getPara());
		String file = excelInHistory.getUploadpath() + File.separator + excelInHistory.getUploadname();
		renderFile(new File(file));
	}
	
	/**
	 * 保存
	 */
	@Before(ExcelInHistoryValidator.class)
	public void save() {
		getModel(ExcelInHistory.class).save();
		forwardAction("/trading/excelInHistory/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		ExcelInHistory excelInHistory = ExcelInHistory.dao.findById(getPara());
		setAttr("excelInHistory", excelInHistory);
		render("/trading/excelInHistory/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(ExcelInHistoryValidator.class)
	public void update() {
		getModel(ExcelInHistory.class).update();
		forwardAction("/trading/excelInHistory/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		ExcelInHistory excelInHistory = ExcelInHistory.dao.findById(getPara());
		setAttr("excelInHistory", excelInHistory);
		render("/trading/excelInHistory/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		excelInHistoryService.baseDelete(ExcelInHistory.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/excelInHistory/backOff");
	}
	
}
