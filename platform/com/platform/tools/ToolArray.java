package com.platform.tools;

import java.util.ArrayList;
import java.util.List;

public class ToolArray {
	public static void oneArrayAddTwoArray() {
		String classRoom[][] = new String[2][3];

		String a[] = { "小王", "小李", "小张" };
		String b[] = { "小王2", "小李2", "小张2" };

		List<String[]> test = new ArrayList<String[]>();
		test.add(a);
		test.add(b);

		int line = 0;
		for (String[] arr : test) {
			int column = 0;
			for (String value : arr) {
				classRoom[line][column] = value;
				column++;
			}
			line++;
		}
		System.out.println(classRoom);
	}
	
	public static void main(String args[]){
		oneArrayAddTwoArray();
	}
}
