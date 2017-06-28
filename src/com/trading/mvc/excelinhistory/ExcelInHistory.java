package com.trading.mvc.excelinhistory;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = ExcelInHistory.table_name)
public class ExcelInHistory extends BaseModel<ExcelInHistory> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(ExcelInHistory.class);
	
	public static final ExcelInHistory dao = new ExcelInHistory().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_excelinhistory";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：原文件 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_fileName = "fileName";
	
	/**
	 * 字段描述：导入条数 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_recordCount = "recordCount";
	
	/**
	 * 字段描述：所属模块 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_module = "module";
	
	/**
	 * 字段描述：上传文件路径 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_uploadpath = "uploadpath";
	
	/**
	 * 字段描述：上传文件名 
	 * 字段类型：varchar(200)  长度：200
	 */
	public static final String column_uploadname = "uploadname";
	
	/**
	 * 字段描述：导入时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_saveDate = "saveDate";
	
	
	/**
	 * sqlId : trading.excelInHistory.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.excelInHistory.splitPageFrom";

	private String ids;
	private String fileName;
	private String recordCount;
	private String module;
	private String uploadpath;
	private String uploadname;
	private String saveDate;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setFileName(String fileName){
		set(column_fileName, fileName);
	}
	public String getFileName() {
		return get(column_fileName);
	}
	public void setRecordCount(String recordCount){
		set(column_recordCount, recordCount);
	}
	public String getRecordCount() {
		return get(column_recordCount);
	}
	public void setModule(String module){
		set(column_module, module);
	}
	public String getModule() {
		return get(column_module);
	}
	public void setUploadpath(String uploadpath){
		set(column_uploadpath, uploadpath);
	}
	public String getUploadpath() {
		return get(column_uploadpath);
	}
	public void setUploadname(String uploadname){
		set(column_uploadname, uploadname);
	}
	public String getUploadname() {
		return get(column_uploadname);
	}
	public void setSaveDate(String saveDate){
		set(column_saveDate, saveDate);
	}
	public String getSaveDate() {
		return get(column_saveDate);
	}
	
}
