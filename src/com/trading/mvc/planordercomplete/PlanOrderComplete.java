package com.trading.mvc.planordercomplete;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;

/**
 * 订货计划完成情况 model
 * 
 * @author 董华健 dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = PlanOrderComplete.table_name)
public class PlanOrderComplete extends BaseModel<PlanOrderComplete> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(PlanOrderComplete.class);

	public static final PlanOrderComplete dao = new PlanOrderComplete().dao();
	/**
	 * sqlId : platform.baseModel.splitPageSelect 描述：通用select *
	 */
	public static final String sqlId_splitPageSelect = "trading.planOrderComplete.splitPageSelect";

	public static final String sqlId_splitPageSelectDialog = "trading.planOrderComplete.splitPageSelectDialog";

	public static final String sqlId_selectByOrderItemIds = "trading.planOrderComplete.selectByOrderItemIds";

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_planordercomplete";

	/**
	 * 字段描述：主键 : 主键 字段类型：varchar(32) 长度：32
	 */
	public static final String column_ids = "ids";

	/**
	 * 字段描述：订单项次号 : 标题 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_orderItemNo = "orderItemNo";

	/**
	 * 字段描述：执行标准 : 内容 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_standard = "standard";

	/**
	 * 字段描述：品名 : 创建时间 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_pName = "pName";

	/**
	 * 字段描述：牌号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_grade = "grade";

	/**
	 * 字段描述：规格型号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_sssss = "sssss";

	/**
	 * 字段描述：项次量 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_itemWeight = "itemWeight";

	/**
	 * 字段描述：项次价格 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_price = "price";

	/**
	 * 字段描述：到站名称 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_rrived = "rrived";

	/**
	 * 字段描述：运输方式 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_specialName = "specialName";

	/**
	 * 字段描述：运费 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_freight = "freight";

	/**
	 * 字段描述：产品形态 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_pForm = "pForm";

	/**
	 * 字段描述：厚度 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_thickness = "thickness";

	/**
	 * 字段描述：宽度 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_width = "width";

	/**
	 * 字段描述：长度 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_length = "length";

	/**
	 * 字段描述：月份 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_cDate = "cDate";

	/**
	 * 字段描述：合同编号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_cNo = "cNo";

	/**
	 * sqlId : trading.planOrderComplete.splitPageFrom 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.planOrderComplete.splitPageFrom";

	private String ids;
	private String orderItemNo;
	private String standard;
	private String pName;
	private String grade;
	private String sssss;
	private String itemWeight;
	private String price;
	private String rrived;
	private String specialName;
	private String freight;
	private String pForm;
	private String thickness;
	private String width;
	private String length;
	private String cDate;
	private String cNo;

	public void setIds(String ids) {
		set(column_ids, ids);
	}

	public String getIds() {
		return get(column_ids);
	}

	public void setOrderItemNo(String orderItemNo) {
		set(column_orderItemNo, orderItemNo);
	}

	public String getOrderItemNo() {
		return get(column_orderItemNo);
	}

	public void setStandard(String standard) {
		set(column_standard, standard);
	}

	public String getStandard() {
		return get(column_standard);
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

	public void setSssss(String sssss) {
		set(column_sssss, sssss);
	}

	public String getSssss() {
		return get(column_sssss);
	}

	public void setItemWeight(String itemWeight) {
		set(column_itemWeight, itemWeight);
	}

	public String getItemWeight() {
		return get(column_itemWeight);
	}

	public void setPrice(String price) {
		set(column_price, price);
	}

	public String getPrice() {
		return get(column_price);
	}

	public void setRrived(String rrived) {
		set(column_rrived, rrived);
	}

	public String getRrived() {
		return get(column_rrived);
	}

	public void setSpecialName(String specialName) {
		set(column_specialName, specialName);
	}

	public String getSpecialName() {
		return get(column_specialName);
	}

	public void setFreight(String freight) {
		set(column_freight, freight);
	}

	public String getFreight() {
		return get(column_freight);
	}

	public void setPForm(String pForm) {
		set(column_pForm, pForm);
	}

	public String getPForm() {
		return get(column_pForm);
	}

	public void setThickness(String thickness) {
		set(column_thickness, thickness);
	}

	public String getThickness() {
		return get(column_thickness);
	}

	public void setWidth(String width) {
		set(column_width, width);
	}

	public String getWidth() {
		return get(column_width);
	}

	public void setLength(String length) {
		set(column_length, length);
	}

	public String getLength() {
		return get(column_length);
	}

	public void setCDate(String cDate) {
		set(column_cDate, cDate);
	}

	public String getCDate() {
		return get(column_cDate);
	}

	public void setCNo(String cNo) {
		set(column_cNo, cNo);
	}

	public String getCNo() {
		return get(column_cNo);
	}

}
