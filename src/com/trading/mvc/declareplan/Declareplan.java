package com.trading.mvc.declareplan;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import com.jfinal.log.Log;

/**
 * 每月订单计划 model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Declareplan.table_name)
public class Declareplan extends BaseModel<Declareplan> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Declareplan.class);
	
	public static final Declareplan dao = new Declareplan().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_declareplan";
	
	/**
	 * 字段描述：主键 : 主键 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：订货单位 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_orderUnit = "orderUnit";
	
	/**
	 * 字段描述：品名 : 创建时间 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_pName = "pName";
	
	/**
	 * 字段描述：牌号 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_grade = "grade";
	
	/**
	 * 字段描述：重量 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_weight = "weight";
	
	/**
	 * 字段描述：生产厂家 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_manufacturer = "manufacturer";
	
	/**
	 * 字段描述：规格型号 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_specification = "specification";
	
	/**
	 * 字段描述：计划年月 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_planDate = "planDate";
	
	
	/**
	 * sqlId : trading.declareplan.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.declareplan.splitPageFrom";

	private String ids;
	private String orderUnit;
	private String pName;
	private String grade;
	private String weight;
	private String manufacturer;
	private String specification;
	private String planDate;
	
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
	public void setWeight(String weight){
		set(column_weight, weight);
	}
	public String getWeight() {
		return get(column_weight);
	}
	public void setManufacturer(String manufacturer){
		set(column_manufacturer, manufacturer);
	}
	public String getManufacturer() {
		return get(column_manufacturer);
	}
	public void setSpecification(String specification){
		set(column_specification, specification);
	}
	public String getSpecification() {
		return get(column_specification);
	}
	public void setPlanDate(String planDate){
		set(column_planDate, planDate);
	}
	public String getPlanDate() {
		return get(column_planDate);
	}
	
}
