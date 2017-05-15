package com.platform.mvc.dimensional;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 参数纬度表 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Dimensional.table_name)
public class Dimensional extends BaseModel<Dimensional> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Dimensional.class);
	
	public static final Dimensional dao = new Dimensional().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_fun_dimensional";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：索引 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_indexkey = "indexkey";
	
	/**
	 * 字段描述：值 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_paravalue = "paravalue";
	
	/**
	 * 字段描述：显示顺序 
	 * 字段类型：int(11)  长度：null
	 */
	public static final String column_vieworder = "vieworder";
	
	
	/**
	 * sqlId : platform.dimensional.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.dimensional.splitPageFrom";

	private String ids;
	private String indexkey;
	private String paravalue;
	private Integer vieworder;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setIndexkey(String indexkey){
		set(column_indexkey, indexkey);
	}
	public String getIndexkey() {
		return get(column_indexkey);
	}
	public void setParavalue(String paravalue){
		set(column_paravalue, paravalue);
	}
	public String getParavalue() {
		return get(column_paravalue);
	}
	public void setVieworder(Integer vieworder){
		set(column_vieworder, vieworder);
	}
	public Integer getVieworder() {
		return get(column_vieworder);
	}
	
}
