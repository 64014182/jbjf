package com.trading.mvc.salessettlement;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;
import com.trading.mvc.salesorder.SalesOrder;
import com.trading.mvc.wiscosettlement.WiscoSettlement;

import java.sql.Timestamp;

import com.jfinal.log.Log;

/**
 * 销售结算 model
 * 
 * @author 董华健 dongcb678@163.com
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
	 * 字段描述：主键 字段类型：varchar(32) 长度：32
	 */
	public static final String column_ids = "ids";

	/**
	 * 字段描述：销售订单编号 字段类型：varchar(32) 长度：32
	 */
	public static final String column_salesOrderIds = "salesOrderIds";

	/**
	 * 字段描述：销售结算Ids 字段类型：varchar(32) 长度：32
	 */
	public static final String column_wiscoSettlementIds = "wiscoSettlementIds";

	/**
	 * 字段描述：销售不含税价 字段类型：varchar(128) 长度：128
	 */
	public static final String column_noTaxPrice = "noTaxPrice";

	/**
	 * 字段描述：货款金额 字段类型：varchar(128) 长度：128
	 */
	public static final String column_goodsAmount = "goodsAmount";

	/**
	 * 字段描述：税款金额 字段类型：varchar(128) 长度：128
	 */
	public static final String column_taxPrice = "taxPrice";

	/**
	 * 字段描述：总金额 字段类型：varchar(128) 长度：128
	 */
	public static final String column_totalAmount = "totalAmount";

	/**
	 * 字段描述：是否开票 字段类型：varchar(1) 长度：1
	 */
	public static final String column_hasDraw = "hasDraw";

	/**
	 * 字段描述：发票号 字段类型：varchar(128) 长度：128
	 */
	public static final String column_invoiceNo = "invoiceNo";

	/**
	 * 字段描述： 字段类型：varchar(128) 长度：128
	 */
	public static final String column_invoicePrice = "invoicePrice";

	/**
	 * 字段描述： 字段类型：timestamp 长度：null
	 */
	public static final String column_saveDate = "saveDate";

	/**
	 * 字段描述： 字段类型：varchar(128) 长度：128
	 */
	public static final String column_flag = "flag";

	/**
	 * 字段描述：订货单位 字段类型：varchar(32) 长度：32
	 */
	public static final String column_orderUnitId = "orderUnitId";

	/**
	 * 字段描述：生产厂家 字段类型：varchar(32) 长度：32
	 */
	public static final String column_manufacturerId = "manufacturerId";

	/**
	 * 字段描述：品名 字段类型：varchar(256) 长度：256
	 */
	public static final String column_pName = "pName";

	/**
	 * 字段描述：牌号 字段类型：varchar(256) 长度：256
	 */
	public static final String column_grade = "grade";

	/**
	 * 字段描述：规格 字段类型：varchar(256) 长度：256
	 */
	public static final String column_specification = "specification";

	/**
	 * 字段描述：实结重量 字段类型：varchar(256) 长度：256
	 */
	public static final String column_weight = "weight";

	/**
	 * 字段描述：订单项次号 字段类型：varchar(256) 长度：256
	 */
	public static final String column_orderItemNo = "orderItemNo";

	/**
	 * 字段描述：合同月份 字段类型：varchar(256) 长度：256
	 */
	public static final String column_contractMonth = "contractMonth";

	/**
	 * 字段描述：单价 字段类型：varchar(256) 长度：256
	 */
	public static final String column_price = "price";

	/**
	 * 字段描述：销售加价 字段类型：varchar(256) 长度：256
	 */
	public static final String column_addPrice = "addPrice";

	/**
	 * 字段描述：销售订单号 字段类型：varchar(256) 长度：256
	 */
	public static final String column_salesOrderNo = "salesOrderNo";

	/**
	 * sqlId : trading.salesSettlement.splitPageFrom 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.salesSettlement.splitPageFrom";

	private String ids;
	private String salesOrderIds;
	private String wiscoSettlementIds;
	private String noTaxPrice;
	private String goodsAmount;
	private String taxPrice;
	private String totalAmount;
	private String hasDraw;
	private String invoiceNo;
	private String invoicePrice;
	private String saveDate;
	private String flag;
	private String orderUnitId;
	private String manufacturerId;
	private String pName;
	private String grade;
	private String specification;
	private String weight;
	private String orderItemNo;
	private String contractMonth;
	private String price;
	private String addPrice;
	private String salesOrderNo;

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

	public void setSaveDate(String saveDate) {
		set(column_saveDate, saveDate);
	}

	public String getSaveDate() {
		return get(column_saveDate);
	}

	public void setFlag(String flag) {
		set(column_flag, flag);
	}

	public String getFlag() {
		return get(column_flag);
	}

	public void setOrderUnitId(String orderUnitId) {
		set(column_orderUnitId, orderUnitId);
	}

	public String getOrderUnitId() {
		return get(column_orderUnitId);
	}

	public void setManufacturerId(String manufacturerId) {
		set(column_manufacturerId, manufacturerId);
	}

	public String getManufacturerId() {
		return get(column_manufacturerId);
	}

	public void setPName(String pName) {
		set(column_pName, pName);
	}

	public String getPName() {
		return get(column_pName);
	}

	public void setGrade(String grade) {
		set(column_grade, grade);
	}

	public String getGrade() {
		return get(column_grade);
	}

	public void setSpecification(String specification) {
		set(column_specification, specification);
	}

	public String getSpecification() {
		return get(column_specification);
	}

	public void setWeight(String weight) {
		set(column_weight, weight);
	}

	public String getWeight() {
		return get(column_weight);
	}

	public void setOrderItemNo(String orderItemNo) {
		set(column_orderItemNo, orderItemNo);
	}

	public String getOrderItemNo() {
		return get(column_orderItemNo);
	}

	public void setContractMonth(String contractMonth) {
		set(column_contractMonth, contractMonth);
	}

	public String getContractMonth() {
		return get(column_contractMonth);
	}

	public void setPrice(String price) {
		set(column_price, price);
	}

	public String getPrice() {
		return get(column_price);
	}

	public void setAddPrice(String addPrice) {
		set(column_addPrice, addPrice);
	}

	public String getAddPrice() {
		return get(column_addPrice);
	}

	public void setSalesOrderNo(String salesOrderNo) {
		set(column_salesOrderNo, salesOrderNo);
	}

	public String getSalesOrderNo() {
		return get(column_salesOrderNo);
	}
	
	/**
	 * 将WiscoSettlement和SalesOrder的数据复制到SalesSettlement
	 * @param ws
	 * @param so
	 */
	public void setWsAndSo(WiscoSettlement ws, SalesOrder so) {
		if (ws != null) {
			setWiscoSettlementIds(ws.getIds());
			setPName(ws.getPName());
			setGrade(ws.getGrade());
			setSpecification(ws.getSpecification());
			setWeight(ws.getWeight());
			setOrderItemNo(ws.getOrderItemNo());
			setContractMonth(ws.getContractMonth());
		}

		if (so != null) {
			setSalesOrderIds(so.getIds());
			setOrderUnitId(so.getOrderUnit());
			setManufacturerId(so.getManufacturer());
			setSalesOrderNo(so.getSalesOrderNo());
			setAddPrice(so.getSalesPrice());
		}
	}
}
