package com.trading.mvc;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TableUtils {
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
		System.out.println(autoGenericCode("0", 4));
	}
}
