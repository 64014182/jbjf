package com.platform.mvc.gc.gccolumnconf;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 自动生成表字段配置 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = GcColumnConf.table_name)
public class GcColumnConf extends BaseModel<GcColumnConf> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(GcColumnConf.class);
	
	public static final GcColumnConf dao = new GcColumnConf().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_fun_gc_columnconf";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：表名 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_tablename = "tablename";
	
	/**
	 * 字段描述：字段名 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_columnname = "columnname";
	
	/**
	 * 字段描述：显示名 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_viewname = "viewname";
	
	/**
	 * 字段描述：显示类型 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_formType = "formType";
	
	/**
	 * 字段描述：数据来源 
	 * 字段类型：varchar(2000)  长度：2000
	 */
	public static final String column_formDataSorce = "formDataSorce";
	
	/**
	 * 字段描述：查询段 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_hasQuery = "hasQuery";
	
	/**
	 * 字段描述：列表显示 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_hasListView = "hasListView";
	
	
	/**
	 * sqlId : platform.gcColumnConf.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.gcColumnConf.splitPageFrom";

	private String ids;
	private String tablename;
	private String columnname;
	private String viewname;
	private String formType;
	private String formDataSorce;
	private Integer hasQuery;
	private Integer hasListView;
	
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
	public void setColumnname(String columnname){
		set(column_columnname, columnname);
	}
	public String getColumnname() {
		return get(column_columnname);
	}
	public void setViewname(String viewname){
		set(column_viewname, viewname);
	}
	public String getViewname() {
		return get(column_viewname);
	}
	public void setFormType(String formType){
		set(column_formType, formType);
	}
	public String getFormType() {
		return get(column_formType);
	}
	public void setFormDataSorce(String formDataSorce){
		set(column_formDataSorce, formDataSorce);
	}
	public String getFormDataSorce() {
		return get(column_formDataSorce);
	}
	public void setHasQuery(Integer hasQuery){
		set(column_hasQuery, hasQuery);
	}
	public Integer getHasQuery() {
		return get(column_hasQuery);
	}
	public void setHasListView(Integer hasListView){
		set(column_hasListView, hasListView);
	}
	public Integer getHasListView() {
		return get(column_hasListView);
	}
	
}
