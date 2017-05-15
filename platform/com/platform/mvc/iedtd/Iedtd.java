package com.platform.mvc.iedtd;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * Excel数据倒入数据库 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Iedtd.table_name)
public class Iedtd extends BaseModel<Iedtd> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Iedtd.class);
	
	public static final Iedtd dao = new Iedtd().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_fun_iedtd";
	
	/**
	 * 字段描述：主键 : 主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：索引字 : 索引字 
	 * 字段类型：varchar(64)  长度：64
	 */
	public static final String column_indexkey = "indexkey";
	
	/**
	 * 字段描述：插入表的SQL语句 : 插入表的SQL语句 
	 * 字段类型：varchar(8192)  长度：8192
	 */
	public static final String column_intoDbSQL = "intoDbSQL";
	
	/**
	 * 字段描述：EXCEL列数，逗号分隔 : EXCEL列数，逗号分隔 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_excelDataColNo = "excelDataColNo";
	
	
	/**
	 * sqlId : platform.iedtd.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.iedtd.splitPageFrom";

	private String ids;
	private String indexkey;
	private String intoDbSQL;
	private String excelDataColNo;
	
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
	public void setIntoDbSQL(String intoDbSQL){
		set(column_intoDbSQL, intoDbSQL);
	}
	public String getIntoDbSQL() {
		return get(column_intoDbSQL);
	}
	public void setExcelDataColNo(String excelDataColNo){
		set(column_excelDataColNo, excelDataColNo);
	}
	public String getExcelDataColNo() {
		return get(column_excelDataColNo);
	}
	
}
