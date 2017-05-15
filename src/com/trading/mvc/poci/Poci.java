package com.trading.mvc.poci;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import com.jfinal.log.Log;

/**
 * 订货计划完成发票 model
 * 
 * @author 董华健 dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = Poci.table_name)
public class Poci extends BaseModel<Poci> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(Poci.class);

	public static final Poci dao = new Poci().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "b_trading_poci";

	/**
	 * 字段描述：主键 : 主键 字段类型：varchar(32) 长度：32
	 */
	public static final String column_ids = "ids";

	/**
	 * 字段描述：发票号 : 标题 字段类型：varchar(128) 长度：128
	 */
	public static final String column_invoceNo = "invoceNo";

	/**
	 * 字段描述：月份 字段类型：varchar(4) 长度：4
	 */
	public static final String column_cDate = "cDate";

	/**
	 * sqlId : trading.poci.splitPageFrom 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "trading.poci.splitPageFrom";
	
	/**
	 * sqlId : platform.baseModel.splitPageSelect
	 * 描述：通用select *
	 */
	public static final String sqlId_splitPageSelect = "trading.poci.splitPageSelect";
	
	private String ids;
	private String invoceNo;
	private String cDate;

	public void setIds(String ids) {
		set(column_ids, ids);
	}

	public String getIds() {
		return get(column_ids);
	}

	public void setInvoceNo(String invoceNo) {
		set(column_invoceNo, invoceNo);
	}

	public String getInvoceNo() {
		return get(column_invoceNo);
	}

	public void setCDate(String cDate) {
		set(column_cDate, cDate);
	}

	public String getCDate() {
		return get(column_cDate);
	}

	public Poci() {
	}

	public Poci(String ids, String invoceNo, String cDate) {
		super();
		setIds(ids);
		setInvoceNo(invoceNo);
		setCDate(cDate);
	}
}
