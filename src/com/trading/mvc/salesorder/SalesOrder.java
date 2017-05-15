package com.trading.mvc.salesorder;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 销售订单 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = SalesOrder.table_name)
public class SalesOrder extends BaseModel<SalesOrder> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(SalesOrder.class);
	
	public static final SalesOrder dao = new SalesOrder().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_salesorder";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：订货单位 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_orderUnit = "orderUnit";
	
	/**
	 * 字段描述：生产厂家 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_manufacturer = "manufacturer";
	
	/**
	 * 字段描述：销售订单号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_salesOrderNo = "salesOrderNo";
	
	/**
	 * 字段描述：销售合同价格 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_salesPrice = "salesPrice";
	
	/**
	 * 字段描述：代垫运货 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_freightage = "freightage";
	
	/**
	 * 字段描述：代垫仓储费 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_storag = "storag";
	
	/**
	 * 字段描述：其它费用 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_other = "other";
	
	/**
	 * 字段描述：订货计划完成情况 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_pocIds = "pocIds";
	
	/**
	 * 字段描述：订单项次号 : 标题 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_orderItemNo = "orderItemNo";
	
	/**
	 * sqlId : trading.salesOrder.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.salesOrder.splitPageFrom";

	public static final String sqlId_selectByPocIds = "trading.salesOrder.selectByPocIds";
	private String ids;
	private String orderUnit;
	private String manufacturer;
	private String salesOrderNo;
	private String salesPrice;
	private String freightage;
	private String storag;
	private String other;
	private String pocIds;
	private String orderItemNo;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setOrderUnit(String orderUnit){
		set(column_orderUnit, orderUnit);
	}
	public String getOrderUnit() {
		return get(column_orderUnit);
	}
	public void setManufacturer(String manufacturer){
		set(column_manufacturer, manufacturer);
	}
	public String getManufacturer() {
		return get(column_manufacturer);
	}
	public void setSalesOrderNo(String salesOrderNo){
		set(column_salesOrderNo, salesOrderNo);
	}
	public String getSalesOrderNo() {
		return get(column_salesOrderNo);
	}
	public void setSalesPrice(String salesPrice){
		set(column_salesPrice, salesPrice);
	}
	public String getSalesPrice() {
		return get(column_salesPrice);
	}
	public void setFreightage(String freightage){
		set(column_freightage, freightage);
	}
	public String getFreightage() {
		return get(column_freightage);
	}
	public void setStorag(String storag){
		set(column_storag, storag);
	}
	public String getStorag() {
		return get(column_storag);
	}
	public void setOther(String other){
		set(column_other, other);
	}
	public String getOther() {
		return get(column_other);
	}
	public void setPocIds(String pocIds){
		set(column_pocIds, pocIds);
	}
	public String getPocIds() {
		return get(column_pocIds);
	}
	public void setOrderItemNo(String orderItemNo){
		set(column_orderItemNo, orderItemNo);
	}
	public String getOrderItemNo() {
		return get(column_orderItemNo);
	}
}
