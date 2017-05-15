package com.trading.mvc.payment;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import com.jfinal.log.Log;

/**
 * 收付款 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Payment.table_name)
public class Payment extends BaseModel<Payment> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Payment.class);
	
	public static final Payment dao = new Payment().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_payment";
	
	/**
	 * 字段描述：主键 : 主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：收货单位 : 标题 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_unit = "unit";
	
	/**
	 * 字段描述：收付款金额 
	 * 字段类型：varchar(128)  长度：128
	 */
	public static final String column_amount = "amount";
	
	/**
	 * 字段描述：摘要 
	 * 字段类型：varchar(1028)  长度：1028
	 */
	public static final String column_abs = "abs";
	
	/**
	 * 字段描述：收款方式v 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_method = "method";
	
	/**
	 * 字段描述：录入时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_sTime = "sTime";
	
	
	/**
	 * sqlId : trading.payment.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.payment.splitPageFrom";
	public static final String sqlId_splitPageSelect = "trading.payment.splitPageSelect";
	private String ids;
	private String unit;
	private String amount;
	private String abs;
	private String method;
	private Timestamp sTime;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setUnit(String unit){
		set(column_unit, unit);
	}
	public String getUnit() {
		return get(column_unit);
	}
	public void setAmount(String amount){
		set(column_amount, amount);
	}
	public String getAmount() {
		return get(column_amount);
	}
	public void setAbs(String abs){
		set(column_abs, abs);
	}
	public String getAbs() {
		return get(column_abs);
	}
	public void setMethod(String method){
		set(column_method, method);
	}
	public String getMethod() {
		return get(column_method);
	}
	public void setSTime(Timestamp sTime){
		set(column_sTime, sTime);
	}
	public Timestamp getSTime() {
		return get(column_sTime);
	}
	
}
