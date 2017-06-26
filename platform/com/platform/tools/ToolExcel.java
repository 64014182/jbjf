package com.platform.tools;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ToolExcel {
	private static Map<String, Integer> map;
	
	public final static String DEFAULT_SHEET = "Sheet1";
	
	static {
		map = new HashMap<String, Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("D", 4);
		map.put("E", 5);
		map.put("F", 6);
		map.put("G", 7);

		map.put("H", 8);
		map.put("I", 9);
		map.put("J", 10);
		map.put("K", 11);
		map.put("L", 12);
		map.put("M", 13);
		map.put("N", 14);

		map.put("O", 15);
		map.put("P", 16);
		map.put("Q", 17);

		map.put("R", 18);
		map.put("S", 19);
		map.put("T", 20);

		map.put("U", 21);
		map.put("V", 22);
		map.put("W", 23);
		map.put("X", 24);
		map.put("Y", 25);
		map.put("Z", 26);
	}

	/**
	 * 
	 * @param file
	 * @param sheet
	 * @param columns
	 *            example: "?,name,age" 按EXCEL列自动填到Map中。？号自动省略
	 * @return
	 */
	public static List<Map<String, String>> toList(File file, String sheet, String columns) {
		List<Map<String, String>> result = new ArrayList<>();
		Map<String, String> mapRows = null;
		String columnsArr[] = columns.split(",");

		try {
			Workbook rwb = Workbook.getWorkbook(file);
			Sheet rs = rwb.getSheet(sheet);
			int rows = rs.getRows();
			int clos = rs.getColumns();
			for (int i = 0; i < rows; i++) {
				mapRows = new HashMap<String, String>();
				for (int j = 0; j < clos; j++) {
					do {
						String value = rs.getCell(j, i).getContents();
						String columnName = columnsArr[j];
						columnName = "?".equals(columnName) ? j + "" : columnName;
						mapRows.put(columnsArr[j], value);
						j++;
					} while (j < clos);
				}
				result.add(mapRows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
//		int[] reulst = getColNo("A=O=Q=R=T=Y=Z=AB=AH=AJ=AS=AU=AV");
//		String[][] r = ToolExcel.readExcelToArray(new File("c://1703.xls"), 3, reulst);
//		System.out.println(r);
		
		
		System.out.println(getNumer("AV"));
		
		
		String[][] t = {{"1","2","3"},{"4","5","6"}};
		t = ToolExcel.addIds(t);
		t = ToolExcel.addOther(t, "jj","jj2");
		System.out.println(t);
	}
	
	public static String[][] readExcelToArray(File file, int readFirstLineNo, int[] readColumnNo) {
		String[][] excelDatas = null;
		try {
			Workbook rwb = Workbook.getWorkbook(file);
			Sheet rs = rwb.getSheet(ToolExcel.DEFAULT_SHEET);
			int rows = rs.getRows();
			int clos = rs.getColumns();
			
			int rRows = rows - readFirstLineNo; // 计算行数
			int rClos = readColumnNo.length; // 计算列数
			excelDatas = new String[rRows][rClos];
			int i = readFirstLineNo;
			
			for (; i < rows; i++) {
				for (int j = 0; j < readColumnNo.length; j++) {
					if (readColumnNo[j] > clos){
						throw new RuntimeException("要读取的Excel行数超出文件总列数！");
					}
					String value = rs.getCell(readColumnNo[j] - 1, i).getContents();
					System.out.println(readColumnNo[j] + " : " + value.trim());
					excelDatas[i-readFirstLineNo][j] = value.trim();
					//System.out.println(excelDatas[i-readFirstLineNo][j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelDatas;
	}

	public static int[] getColNo(String columns) throws Exception {
		String[] columnsArr = columns.split("=");
		int[] reulst = new int[columnsArr.length];

		for (int i = 0; i < columnsArr.length; i++) {
			reulst[i] = getNumer(columnsArr[i]);
		}
		return reulst;
	}

	private static Integer getNumer(String column) throws Exception {
		column = column.toUpperCase();
		int colLen = column.length();
		switch (colLen) {
		case 1:
			return map.get(column);
		case 2:
			String firstChart = column.substring(0, 1);
			String secChart = column.substring(1, 2);
			return map.get(firstChart) * 26 + map.get(secChart);
		default:
			throw new RuntimeException("非法字符： " + column + ". 无法转换为Excel数字列！");
		}
	}

	/***************************************************************************
	 * @param fileName
	 *            EXCEL文件名称
	 * @param listTitle
	 *            EXCEL文件第一行列标题集合
	 * @param listContent
	 *            EXCEL文件正文数据集合
	 * @return
	 */
	public final static String exportExcel(HttpServletResponse response,String fileName, String[] Title, List<Object> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			//HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			/** **********创建工作簿************ */
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

			/** ************以下设置三种单元格样式，灵活备用************ */
			// 用于标题居中
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(false); // 文字是否换行

			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行

			/** ***************以下是EXCEL开头大标题，暂时省略********************* */
			// sheet.mergeCells(0, 0, colWidth, 0);
			// sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
			/** ***************以下是EXCEL第一行列标题********************* */
			for (int i = 0; i < Title.length; i++) {
				sheet.addCell(new Label(i, 0, Title[i], wcf_center));
			}
			/** ***************以下是EXCEL正文数据********************* */
			Field[] fields = null;
			int i = 1;
			for (Object obj : listContent) {
				fields = obj.getClass().getDeclaredFields();
				int j = 0;
				for (Field v : fields) {
					v.setAccessible(true);
					Object va = v.get(obj);
					if (va == null) {
						va = "";
					}
					sheet.addCell(new Label(j, i, va.toString(), wcf_left));
					j++;
				}
				i++;
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}
	
	public static String[][] addIds(String[][] eDatas) {
		String[][] newDatas = new String[eDatas.length][eDatas[0].length + 1];
		int i = 0;
		String[] newLine = null;
		for (String[] ed : eDatas) {
			newLine = ed;
			newLine = ArrayUtils.add(newLine, 0, ToolUuid.get32UUID());
			newDatas[i] = newLine;
			i++;
		}
		return newDatas;
	}
	
	/**
	 * 添加到数组未尾
	 * @param eDatas
	 * @param other
	 * @return
	 */
	public static String[][] addOther(String[][] eDatas, String... other) {
		if (other.length < 0)
			return eDatas;

		String[][] newDatas = new String[eDatas.length][eDatas[0].length + other.length];
		int i = 0;
		String[] newLine = null;
		for (String[] ed : eDatas) {
			newLine = ed;
			for (String value : other) {
				newLine = ArrayUtils.add(newLine, value);
			}
			newDatas[i] = newLine;
			i++;
		}
		return newDatas;
	}
}
