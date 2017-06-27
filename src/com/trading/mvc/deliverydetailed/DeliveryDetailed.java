package com.trading.mvc.deliverydetailed;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 武钢采购发货明细 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = DeliveryDetailed.table_name)
public class DeliveryDetailed extends BaseModel<DeliveryDetailed> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(DeliveryDetailed.class);
	
	public static final DeliveryDetailed dao = new DeliveryDetailed().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_deliverydetailed";
	
	/**
	 * 字段描述：ids 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：订单项次编号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_orderItemNo = "orderItemNo";
	
	/**
	 * 字段描述：合同月份 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_contractMonth = "contractMonth";
	
	/**
	 * 字段描述：标签 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_tag = "tag";
	
	/**
	 * 字段描述：重量 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_weight = "weight";
	
	/**
	 * 字段描述：数量 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_quantity = "quantity";
	
	/**
	 * 字段描述：销售日期 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_writeOffDate = "writeOffDate";
	
	/**
	 * 字段描述：运费 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_freight = "freight";
	
	/**
	 * 字段描述：代收铁运保险费 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_railwayTIP = "railwayTIP";
	
	/**
	 * 字段描述：代收水运保险费 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_waterTIP = "waterTIP";
	
	/**
	 * 字段描述：代办运输延伸费 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_extensionFreight = "extensionFreight";
	
	/**
	 * 字段描述：厚 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_thickness = "thickness";
	
	/**
	 * 字段描述：宽 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_width = "width";
	
	/**
	 * 字段描述：长 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_length = "length";
	
	/**
	 * 字段描述：到站(港口)名称 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_destination = "destination";
	
	/**
	 * 字段描述：专用线 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_privateLine = "privateLine";
	
	/**
	 * 字段描述：追溯金额 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_gapPrice = "gapPrice";
	
	/**
	 * 字段描述：追溯税额 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_dvPrice = "dvPrice";
	
	/**
	 * 字段描述：追溯文号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_docNo = "docNo";
	
	/**
	 * 字段描述：是否追溯已经结算0否，1是 
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_hasSet = "hasSet";
	
	/**
	 * 字段描述：状态 0-未入库 1-已入库 2-已出库 
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_state = "state";
	
	/**
	 * 字段描述：采购结算状态
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_settState = "settState";
	
	/**
	 * 字段描述：入库号 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_inNo = "inNo";
	
	/**
	 * 字段描述：出库号 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_outNo = "outNo";
	
	/**
	 * 字段描述：追溯幅度 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_traceRange = "traceRange";
	
	/**
	 * 字段描述：价税合计 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_pricetax = "pricetax";
	
	/**
	 * 字段描述：数据类型
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_dtype = "dtype";
	/**
	 * sqlId : trading.deliveryDetailed.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.deliveryDetailed.splitPageFrom";

	/**
	 * sqlId : trading.deliveryDetailed.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageSelect = "trading.deliveryDetailed.splitPageSelect";
	private String ids;
	private String orderItemNo;
	private String contractMonth;
	private String tag;
	private String weight;
	private String quantity;
	private String writeOffDate;
	private String freight;
	private String railwayTIP;
	private String waterTIP;
	private String extensionFreight;
	private String thickness;
	private String width;
	private String length;
	private String destination;
	private String privateLine;
	private String gapPrice;
	private String dvPrice;
	private String docNo;
	private String hasSet;
	private String state;
	private String inNo;
	private String outNo;
	private String traceRange;
	private String pricetax;
	private String settState;
	private String dtype;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setOrderItemNo(String orderItemNo){
		set(column_orderItemNo, orderItemNo);
	}
	public String getOrderItemNo() {
		return get(column_orderItemNo);
	}
	public void setContractMonth(String contractMonth){
		set(column_contractMonth, contractMonth);
	}
	public String getContractMonth() {
		return get(column_contractMonth);
	}
	public void setTag(String tag){
		set(column_tag, tag);
	}
	public String getTag() {
		return get(column_tag);
	}
	public void setWeight(String weight){
		set(column_weight, weight);
	}
	public String getWeight() {
		return get(column_weight);
	}
	public void setQuantity(String quantity){
		set(column_quantity, quantity);
	}
	public String getQuantity() {
		return get(column_quantity);
	}
	public void setWriteOffDate(String writeOffDate){
		set(column_writeOffDate, writeOffDate);
	}
	public String getWriteOffDate() {
		return get(column_writeOffDate);
	}
	public void setFreight(String freight){
		set(column_freight, freight);
	}
	public String getFreight() {
		return get(column_freight);
	}
	public void setRailwayTIP(String railwayTIP){
		set(column_railwayTIP, railwayTIP);
	}
	public String getRailwayTIP() {
		return get(column_railwayTIP);
	}
	public void setWaterTIP(String waterTIP){
		set(column_waterTIP, waterTIP);
	}
	public String getWaterTIP() {
		return get(column_waterTIP);
	}
	public void setExtensionFreight(String extensionFreight){
		set(column_extensionFreight, extensionFreight);
	}
	public String getExtensionFreight() {
		return get(column_extensionFreight);
	}
	public void setThickness(String thickness){
		set(column_thickness, thickness);
	}
	public String getThickness() {
		return get(column_thickness);
	}
	public void setWidth(String width){
		set(column_width, width);
	}
	public String getWidth() {
		return get(column_width);
	}
	public void setLength(String length){
		set(column_length, length);
	}
	public String getLength() {
		return get(column_length);
	}
	public void setDestination(String destination){
		set(column_destination, destination);
	}
	public String getDestination() {
		return get(column_destination);
	}
	public void setPrivateLine(String privateLine){
		set(column_privateLine, privateLine);
	}
	public String getPrivateLine() {
		return get(column_privateLine);
	}
	public void setGapPrice(String gapPrice){
		set(column_gapPrice, gapPrice);
	}
	public String getGapPrice() {
		return get(column_gapPrice);
	}
	public void setDvPrice(String dvPrice){
		set(column_dvPrice, dvPrice);
	}
	public String getDvPrice() {
		return get(column_dvPrice);
	}
	public void setDocNo(String docNo){
		set(column_docNo, docNo);
	}
	public String getDocNo() {
		return get(column_docNo);
	}
	public void setHasSet(String hasSet){
		set(column_hasSet, hasSet);
	}
	public String getHasSet() {
		return get(column_hasSet);
	}
	public void setState(String state){
		set(column_state, state);
	}
	public String getState() {
		return get(column_state);
	}
	public void setSettState(String settState){
		set(column_settState, settState);
	}
	public String getSettState() {
		return get(column_settState);
	}
	public void setInNo(String inNo){
		set(column_inNo, inNo);
	}
	public String getInNo() {
		return get(column_inNo);
	}
	public void setOutNo(String outNo){
		set(column_outNo, outNo);
	}
	public String getOutNo() {
		return get(column_outNo);
	}
	public void setTraceRange(String traceRange){
		set(column_traceRange, traceRange);
	}
	public String getTraceRange() {
		return get(column_traceRange);
	}
	public void setPricetax(String pricetax){
		set(column_pricetax, pricetax);
	}
	public String getPricetax() {
		return get(column_pricetax);
	}
	public void setDtype(String dtype) {
		set(column_dtype, dtype);
	}

	public String getDtype() {
		return get(column_dtype);
	}
}
