package com.trading.mvc;

public class TradingConst {
	/**
	 * 武钢采购结算追溯前辍标识
	 */
	public static final String WiscoSettlement_cz = "CZ";
	
	/**
	 * 武钢销售结算追溯前辍标识
	 */
	public static final String WiscoSettlement_xz = "XZ";
	
	/**
	 * 武钢销售结算明细前辍标识
	 */
	public static final String SalesSettlement_xz = "X";
	
	/**
	 * 非武钢结算前辍标识
	 */
	public static final String WiscoSettlement_Sett = "JS";
	
	/**
	 * 入库前辍标识
	 */
	public static final String DeliveryDetailed_In = "RK";

	/**
	 * 出库前辍标识
	 */
	public static final String DeliveryDetailed_Out = "CK";
	
	/**
	 * 入库导出前辍标识
	 */
	public static final String DeliveryDetailed_In_Ex = "RKE";

	/**
	 * 出库导出前辍标识
	 */
	public static final String DeliveryDetailed_Out_Ex = "CKE";
	
	/**
	 * 采购结算明细Excel前辍标识
	 */
	public static final String WSettlement_Out = "C";
	
	/**
	 * 发货明细状态
	 * 未入库
	 */
	public static final String DeliveryDetailedState_no = "0";
	
	/**
	 * 发货明细状态
	 * 入库
	 */
	public static final String DeliveryDetailedState_in = "1";
	
	/**
	 * 发货明细状态
	 * 出库
	 */
	public static final String DeliveryDetailedState_out = "2";
	
}
