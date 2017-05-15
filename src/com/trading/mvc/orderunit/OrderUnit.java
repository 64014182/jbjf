package com.trading.mvc.orderunit;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 订货单位 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = OrderUnit.table_name)
public class OrderUnit extends BaseModel<OrderUnit> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(OrderUnit.class);
	
	public static final OrderUnit dao = new OrderUnit().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_orderunit";
	
	/**
	 * 字段描述：ids 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：名称 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_name = "name";
	
	
	/**
	 * sqlId : trading.orderUnit.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.orderUnit.splitPageFrom";

	private String ids;
	private String name;
	
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
	
}
