package com.trading.mvc.retrospect;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;

/**
 * 追溯设置 model
 * 
 * @author 董华健 dongcb678@163.com
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
	 * 字段描述：编辑 字段类型：varchar(32) 长度：32
	 */
	public static final String column_ids = "ids";

	/**
	 * 字段描述：月份 字段类型：varchar(4) 长度：4
	 */
	public static final String column_mon = "mon";

	/**
	 * 字段描述：品名 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_pName = "pName";

	/**
	 * 字段描述：牌号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_grade = "grade";

	/**
	 * 字段描述：规格型号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_specification = "specification";

	/**
	 * 字段描述：追溯单价 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_price = "price";

	/**
	 * 字段描述：追溯文号 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_docNo = "docNo";
	
	/**
	 * 字段描述：追溯单价差额 字段类型：varchar(1024) 长度：1024
	 */
	public static final String column_gapPrice = "gapPrice";
	
	/**
	 * sqlId : trading.retrospect.splitPageFrom 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.retrospect.splitPageFrom";

	private String ids;
	private String mon;
	private String pName;
	private String grade;
	private String specification;
	private String price;
	private String docNo;
	private String gapPrice;
	public void setIds(String ids) {
		set(column_ids, ids);
	}

	public String getIds() {
		return get(column_ids);
	}

	public void setMon(String mon) {
		set(column_mon, mon);
	}

	public String getMon() {
		return get(column_mon);
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

	public void setPrice(String price) {
		set(column_price, price);
	}

	public String getPrice() {
		return get(column_price);
	}

	public void setDocNo(String docNo) {
		set(column_docNo, docNo);
	}

	public String getDocNo() {
		return get(column_docNo);
	}

	public void setGapPrice(String gapPrice) {
		set(column_gapPrice, gapPrice);
	}

	public String getGapPrice() {
		return get(column_gapPrice);
	}
}
