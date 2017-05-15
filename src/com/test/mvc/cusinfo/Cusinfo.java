package com.test.mvc.cusinfo;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Cusinfo.table_name)
public class Cusinfo extends BaseModel<Cusinfo> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Cusinfo.class);
	
	public static final Cusinfo dao = new Cusinfo().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "qintai_cusinfo";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_cusbank = "cusbank";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_bankNum = "bankNum";
	
	
	/**
	 * sqlId : test.cusinfo.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "test.cusinfo.splitPageFrom";

	private String ids;
	private String name;
	private String cusbank;
	private String bankNum;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setName(String name){
		set(column_name, name);
	}
	public String getName() {
		return get(column_name);
	}
	public void setCusbank(String cusbank){
		set(column_cusbank, cusbank);
	}
	public String getCusbank() {
		return get(column_cusbank);
	}
	public void setBankNum(String bankNum){
		set(column_bankNum, bankNum);
	}
	public String getBankNum() {
		return get(column_bankNum);
	}
	
}
