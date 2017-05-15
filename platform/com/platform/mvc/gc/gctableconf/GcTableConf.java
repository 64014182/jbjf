package com.platform.mvc.gc.gctableconf;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 生成界面类信息 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = GcTableConf.table_name)
public class GcTableConf extends BaseModel<GcTableConf> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(GcTableConf.class);
	
	public static final GcTableConf dao = new GcTableConf().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_fun_gc_tableconf";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：表名 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_tablename = "tablename";
	
	/**
	 * 字段描述：类名 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_classname = "classname";
	
	/**
	 * 字段描述：包名 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_packagename = "packagename";
	
	/**
	 * 字段描述：源目录路径 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_srcFolder = "srcFolder";
	
	/**
	 * 字段描述：显示名 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_viewname = "viewname";
	
	
	/**
	 * sqlId : platform.gcTableConf.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.gcTableConf.splitPageFrom";

	private String ids;
	private String tablename;
	private String classname;
	private String packagename;
	private String srcFolder;
	private String viewname;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setTablename(String tablename){
		set(column_tablename, tablename);
	}
	public String getTablename() {
		return get(column_tablename);
	}
	public void setClassname(String classname){
		set(column_classname, classname);
	}
	public String getClassname() {
		return get(column_classname);
	}
	public void setPackagename(String packagename){
		set(column_packagename, packagename);
	}
	public String getPackagename() {
		return get(column_packagename);
	}
	public void setSrcFolder(String srcFolder){
		set(column_srcFolder, srcFolder);
	}
	public String getSrcFolder() {
		return get(column_srcFolder);
	}
	public void setViewname(String viewname){
		set(column_viewname, viewname);
	}
	public String getViewname() {
		return get(column_viewname);
	}
	
}
