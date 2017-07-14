package com.trading.mvc.retrospect;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 追溯设置 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Retrospect.table_name)
public class Retrospect extends BaseModel<Retrospect> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Retrospect.class);
	
	public static final Retrospect dao = new Retrospect().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_retrospect";
	
	/**
	 * 字段描述：编辑 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：月份 
	 * 字段类型：varchar(4)  长度：4
	 */
	public static final String column_year = "year";
	
	/**
	 * 字段描述：品名 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_amount = "amount";
	
	
	/**
	 * sqlId : trading.retrospect.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.retrospect.splitPageFrom";

	private String ids;
	private String year;
	private String amount;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setYear(String year){
		set(column_year, year);
	}
	public String getYear() {
		return get(column_year);
	}
	public void setAmount(String amount){
		set(column_amount, amount);
	}
	public String getAmount() {
		return get(column_amount);
	}
	
}
