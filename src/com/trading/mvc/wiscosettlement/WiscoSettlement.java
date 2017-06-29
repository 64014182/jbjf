package com.trading.mvc.wiscosettlement;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp;

import com.jfinal.log.Log;

/**
 * 武钢结算表 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = WiscoSettlement.table_name)
public class WiscoSettlement extends BaseModel<WiscoSettlement> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(WiscoSettlement.class);
	
	public static final WiscoSettlement dao = new WiscoSettlement().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_wiscosettlement";
	
	/**
	 * 字段描述：ids 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：结算清单号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_settlementNo = "settlementNo";
	
	/**
	 * 字段描述：开立日期 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_issuanceDate = "issuanceDate";
	
	/**
	 * 字段描述：订单项次编号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_orderItemNo = "orderItemNo";
	
	/**
	 * 字段描述：品种 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_pName = "pName";
	
	/**
	 * 字段描述：牌号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_grade = "grade";
	
	/**
	 * 字段描述：规格 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_specification = "specification";
	
	/**
	 * 字段描述：合同月份 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_contractMonth = "contractMonth";
	
	/**
	 * 字段描述：出货重量 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_weight = "weight";
	
	/**
	 * 字段描述：单价 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_price = "price";
	
	/**
	 * 字段描述：人民币货款金额 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_loan = "loan";
	
	/**
	 * 字段描述：税额 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_tax = "tax";
	
	/**
	 * 字段描述：发票号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_invoice = "invoice";
	
	/**
	 * 字段描述：运费 
	 * 字段类型：varchar(1024)  长度：1024
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
	 * 字段描述：开立原因 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_openCause = "openCause";
	
	/**
	 * 字段描述：是否确认结算
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_hasConfirm = "hasConfirm";
	
	/**
	 * 字段描述：数据类型
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_dtype = "dtype";
	
	/**
	 * 字段描述：数据类型
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_saveDate = "saveDate";
	
	
	/**
	 * sqlId : trading.wiscoSettlement.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.wiscoSettlement.splitPageFrom";
	
	/**
	 * sqlId : trading.wiscoSettlement.splitPageSelect
	 * 描述：通用select *
	 */
	public static final String sqlId_splitPageSelect = "trading.wiscoSettlement.splitPageSelect";
	
	/**
	 * 字段描述：是否确认结算
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_saveInvoceDate = "saveInvoceDate";
	
	private String ids;
	private String settlementNo;
	private String issuanceDate;
	private String orderItemNo;
	private String pName;
	private String grade;
	private String specification;
	private String contractMonth;
	private String weight;
	private String price;
	private String loan;
	private String tax;
	private String invoice;
	private String freight;
	private String railwayTIP;
	private String waterTIP;
	private String extensionFreight;
	private String openCause;
	private String hasConfirm;
	private String saveInvoceDate;
	private String dtype;
	private String saveDate;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setSettlementNo(String settlementNo){
		set(column_settlementNo, settlementNo);
	}
	public String getSettlementNo() {
		return get(column_settlementNo);
	}
	public void setIssuanceDate(String issuanceDate){
		set(column_issuanceDate, issuanceDate);
	}
	public String getIssuanceDate() {
		return get(column_issuanceDate);
	}
	public void setOrderItemNo(String orderItemNo){
		set(column_orderItemNo, orderItemNo);
	}
	public String getOrderItemNo() {
		return get(column_orderItemNo);
	}
	public void setPName(String pName){
		set(column_pName, pName);
	}
	public String getPName() {
		return get(column_pName);
	}
	public void setGrade(String grade){
		set(column_grade, grade);
	}
	public String getGrade() {
		return get(column_grade);
	}
	public void setSpecification(String specification){
		set(column_specification, specification);
	}
	public String getSpecification() {
		return get(column_specification);
	}
	public void setContractMonth(String contractMonth){
		set(column_contractMonth, contractMonth);
	}
	public String getContractMonth() {
		return get(column_contractMonth);
	}
	public void setWeight(String weight){
		set(column_weight, weight);
	}
	public String getWeight() {
		return get(column_weight);
	}
	public void setPrice(String price){
		set(column_price, price);
	}
	public String getPrice() {
		return get(column_price);
	}
	public void setLoan(String loan){
		set(column_loan, loan);
	}
	public String getLoan() {
		return get(column_loan);
	}
	public void setTax(String tax){
		set(column_tax, tax);
	}
	public String getTax() {
		return get(column_tax);
	}
	public void setInvoice(String invoice){
		set(column_invoice, invoice);
	}
	public String getInvoice() {
		return get(column_invoice);
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
	public void setOpenCause(String openCause){
		set(column_openCause, openCause);
	}
	public String getOpenCause() {
		return get(column_openCause);
	}
	
	public void setHasConfirm(String hasConfirm){
		set(column_hasConfirm, hasConfirm);
	}
	public String getHasConfirm() {
		return get(column_hasConfirm);
	}
	
	public void setSaveInvoceDate(String saveInvoceDate){
		set(column_saveInvoceDate, saveInvoceDate);
	}
	public String getSaveInvoceDate() {
		return get(column_saveInvoceDate);
	}
	
	public void setDtype(String dtype) {
		set(column_dtype, dtype);
	}

	public String getDtype() {
		return get(column_dtype);
	}
	
	public void setSaveDate(String saveDate) {
		set(column_saveDate, saveDate);
	}
	
	public String getSaveDate() {
		return get(column_saveDate);
	}
}
