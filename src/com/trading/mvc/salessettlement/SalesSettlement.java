package com.trading.mvc.salessettlement;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp;

import com.jfinal.log.Log;

/**
 * 销售结算 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = SalesSettlement.table_name)
public class SalesSettlement extends BaseModel<SalesSettlement> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(SalesSettlement.class);
	
	public static final SalesSettlement dao = new SalesSettlement().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_salessettlement";
	
	/**
	 * 字段描述：主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：销售订单编号 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_salesOrderIds = "salesOrderIds";
	
	/**
	 * 字段描述：销售结算Ids 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_wiscoSettlementIds = "wiscoSettlementIds";
	
	/**
	 * 字段描述：销售不含税价 
	 * 字段类型：varchar(2056)  长度：2056
	 */
	public static final String column_noTaxPrice = "noTaxPrice";
	
	/**
	 * 字段描述：货款金额 
	 * 字段类型：varchar(2056)  长度：2056
	 */
	public static final String column_goodsAmount = "goodsAmount";
	
	/**
	 * 字段描述：税款金额 
	 * 字段类型：varchar(2056)  长度：2056
	 */
	public static final String column_taxPrice = "taxPrice";
	
	/**
	 * 字段描述：总金额 
	 * 字段类型：varchar(2056)  长度：2056
	 */
	public static final String column_totalAmount = "totalAmount";
	
	/**
	 * 字段描述：是否开票 
	 * 字段类型：varchar(1)  长度：1
	 */
	public static final String column_hasDraw = "hasDraw";
	
	/**
	 * 字段描述：发票号 
	 * 字段类型：varchar(1)  长度：1024
	 */
	public static final String column_invoiceNo = "invoiceNo";
	
	/**
	 * 字段描述：销售合同价 
	 * 字段类型：varchar(1)  长度：1024
	 */
	public static final String column_invoicePrice = "invoicePrice";
	
	/**
	 * sqlId : trading.salesSettlement.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.salesSettlement.splitPageFrom";
	/**
	 * 描述：通用select *
	 */
	public static final String sqlId_splitPageSelect = "trading.salesSettlement.splitPageSelect";
	
	/**
	 * 字段描述：销售合同价 
	 * 字段类型：varchar(1)  长度：1024
	 */
	public static final String column_flag = "flag";
	public static final String column_saveDate = "saveDate";
	
	private String ids;
	private String salesOrderIds;
	private String salesSettlementIds;
	private String noTaxPrice;
	private String goodsAmount;
	private String taxPrice;
	private String totalAmount;
	private String hasDraw;
	private String invoiceNo;
	private String invoicePrice;
	private	String flag;
	private Timestamp saveDate;
	
	public void setIds(String ids) {
		set(column_ids, ids);
	}

	public String getIds() {
		return get(column_ids);
	}

	public void setSalesOrderIds(String salesOrderIds) {
		set(column_salesOrderIds, salesOrderIds);
	}

	public String getSalesOrderIds() {
		return get(column_salesOrderIds);
	}

	public void setWiscoSettlementIds(String wiscoSettlementIds) {
		set(column_wiscoSettlementIds, wiscoSettlementIds);
	}

	public String getWiscoSettlementIds() {
		return get(column_wiscoSettlementIds);
	}

	public void setNoTaxPrice(String noTaxPrice) {
		set(column_noTaxPrice, noTaxPrice);
	}

	public String getNoTaxPrice() {
		return get(column_noTaxPrice);
	}

	public void setGoodsAmount(String goodsAmount) {
		set(column_goodsAmount, goodsAmount);
	}

	public String getGoodsAmount() {
		return get(column_goodsAmount);
	}

	public void setTaxPrice(String taxPrice) {
		set(column_taxPrice, taxPrice);
	}

	public String getTaxPrice() {
		return get(column_taxPrice);
	}

	public void setTotalAmount(String totalAmount) {
		set(column_totalAmount, totalAmount);
	}

	public String getTotalAmount() {
		return get(column_totalAmount);
	}

	public void setHasDraw(String hasDraw) {
		set(column_hasDraw, hasDraw);
	}

	public String getHasDraw() {
		return get(column_hasDraw);
	}

	public void setInvoiceNo(String invoiceNo) {
		set(column_invoiceNo, invoiceNo);
	}

	public String getInvoiceNo() {
		return get(column_invoiceNo);
	}

	public void setInvoicePrice(String invoicePrice) {
		set(column_invoicePrice, invoicePrice);
	}

	public String getInvoicePrice() {
		return get(column_invoicePrice);
	}

	public void setFlag(String flag) {
		set(column_flag, flag);
	}

	public String getFlag() {
		return get(column_flag);
	}
	
	public void setSaveDate(Timestamp saveDate) {
		set(column_saveDate, saveDate);
	}

	public String getSaveDate() {
		return get(column_saveDate);
	}
}
