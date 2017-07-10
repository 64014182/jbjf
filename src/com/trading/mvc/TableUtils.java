package com.trading.mvc;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.tools.ToolDateTime;

public class TableUtils {
	
	public static Map<String, String> serialNo = new HashMap<>();

	public static String getSerialNo(int digit,String preFix, String date) {
		String key = preFix + date;
		String value = serialNo.get(key);
		if (StringUtils.isEmpty(value)) {
			String no = autoGenericCode("0", digit);
			serialNo.put(key, key + no);
		} else {
			String no = value.substring(value.length() - digit, value.length());
			int noInt = Integer.valueOf(no);
			no = autoGenericCode(String.valueOf(noInt), digit);
			serialNo.put(key, key + no);
		}
		return serialNo.get(key);
	}
	
	/**
	 * 统计表指定表达式的统计数
	 * ex: digit=4 table=user column=no expre=No170620 
	 * 		SELECT COUNT(*) AS count FROM user WHERE 1 = 1 AND no LIKE '%No170620%'
	 * 		如果count =1 返回 0002
	 * @param digit  返回统计数的位数
	 * @param table	表名
	 * @param column 列名
	 * @param expre 统计表达式
	 */
	private static String countNo(int digit, String table, String column, String expre) {
		String sql = "SELECT COUNT(*) AS count FROM " + table + " WHERE 1 = 1 AND " + column + " LIKE '%" + expre + "%'";
		Record r = Db.findFirst(sql);
		String count = String.valueOf(r.get("count"));
		return autoGenericCode(count,digit);
	}
	
	/**
	 * 统计表指定表达式的统计数
	 * ex: digit=4 table=user column=no expre=No170620 
	 * 		SELECT COUNT(*) AS count FROM user WHERE 1 = 1 AND no LIKE '%No170620%'
	 * 		如果count =1 返回  No1706200002
	 * @param digit  返回统计数的位数
	 * @param table	表名
	 * @param column 列名
	 * @param expre 统计表达式
	 */
	public static String getNo(int digit, String table, String column, String expre) {
		return expre + countNo(digit, table, column, expre);
	}
	
	/**
	 * 不够位数的在前面补0，保留num的长度位数字
	 * 
	 * @param code
	 * @return
	 */
	private static String autoGenericCode(String code, int num) {
		String result = "";
		// 保留num的位数
		// 0 代表前面补充0
		// num 代表长度为4
		// d 代表参数为正数型
		result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);
		return result;
	}
	
	public static void main(String[] args) {
		//String value = autoGenericCode("0", 4);
		System.out.println(getSerialNo(4, "cx", ToolDateTime.getCurrent("yyMMdd")));
		System.out.println(getSerialNo(4, "cx", ToolDateTime.getCurrent("yyMMdd")));
		System.out.println(getSerialNo(4, "cx", ToolDateTime.getCurrent("yyMMdd")));
		System.out.println(getSerialNo(4, "cx", ToolDateTime.getCurrent("yyMMdd")));
		System.out.println(getSerialNo(4, "cx", ToolDateTime.getCurrent("yyMMdd")));
		
	}
}
