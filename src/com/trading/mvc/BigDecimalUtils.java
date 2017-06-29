package com.trading.mvc;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class BigDecimalUtils {

	public static final String WIS_BIG_117 = "1.17";
	public static final String WIS_BIG_017 = "0.17";

	public static BigDecimal getBidDecimal(String value) {
		if (null == value) {
			value = "0";
		}
		if (StringUtils.isEmpty(value))
			throw new RuntimeException("数值【" + value + "】为空或不是合法的数字！");
		if (value.indexOf(",") > -1) {
			value = value.replaceAll(",", "");
		}
		return new BigDecimal(value);
	}
	
	public static void main(String[] args) {
		//System.out.println("32,33,3.21".indexOf(","));
		System.out.println(getBidDecimal("32,33,3.21"));
	}
}
