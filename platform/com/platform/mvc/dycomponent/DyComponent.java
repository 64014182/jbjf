package com.platform.mvc.dycomponent;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 动态组件 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = DyComponent.table_name)
public class DyComponent extends BaseModel<DyComponent> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(DyComponent.class);
	
	public static final DyComponent dao = new DyComponent().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_fun_dyComponent";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：唯一索引 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_sqlkey = "sqlkey";
	
	/**
	 * 字段描述：sql语句 
	 * 字段类型：varchar(2056)  长度：2056
	 */
	public static final String column_sqlvalue = "sqlvalue";
	
	/**
	 * 字段描述：生成的控制类型 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_comtype = "comtype";
	
	
	/**
	 * sqlId : platform.dyComponent.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.dyComponent.splitPageFrom";

	private String ids;
	private String sqlkey;
	private String sqlvalue;
	private String comtype;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setSqlkey(String sqlkey){
		set(column_sqlkey, sqlkey);
	}
	public String getSqlkey() {
		return get(column_sqlkey);
	}
	public void setSqlvalue(String sqlvalue){
		set(column_sqlvalue, sqlvalue);
	}
	public String getSqlvalue() {
		return get(column_sqlvalue);
	}
	public void setComtype(String comtype){
		set(column_comtype, comtype);
	}
	public String getComtype() {
		return get(column_comtype);
	}
	
}
