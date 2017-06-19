package com.trading.mvc.deliverydetailed;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.PathKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Service;
import com.platform.dto.SplitPage;
import com.platform.mvc.base.BaseService;
import com.platform.mvc.iedtd.Iedtd;
import com.platform.tools.ToolDateTime;
import com.platform.tools.ToolExcel;
import com.platform.tools.ToolFreemarkParse;
import com.platform.tools.code.handler.BaseHandler;

@Service(name = DeliveryDetailedService.serviceName)
public class DeliveryDetailedService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeliveryDetailedService.class);

	public static final String serviceName = "deliveryDetailedService";

	public void saveByExcel(UploadFile uploadFile, String sql, String indexKey) throws Exception {
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();

		// 读取EXCEL文件数据
		String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), "Sheet1", 3, true,ToolExcel.getColNo(columnsNo));

//		// 添加价格 生产厂家 等数据到EXCLE行
//		String[] lows = null;
//		String[][] saveData = new String[excelData.length][excelData[0].length + 5];
//		int i = 0;
//		for (String[] ed : excelData) {
//			String orderItemNo = ed[1];
//			String sNo = orderItemNo.substring(0, 8);
//			SalesOrder so = SalesOrder.dao.findFirstByColumnValue("orderItemNo", sNo);
//			int edLength = ed.length;
//			lows = new String[edLength + 5];
//			System.arraycopy(ed, 0, lows, 0, ed.length);
//
//			if (so != null) {
//				PlanOrderComplete poc = PlanOrderComplete.dao.findFirstByColumnValue("orderItemNo", orderItemNo);
//				lows[edLength] = so.getManufacturer();
//				lows[edLength + 1] = so.getOrderUnit();
//				lows[edLength + 2] = poc.getPrice();
//				lows[edLength + 3] = so.getSalesPrice();
//				lows[edLength + 4] = so.getSalesOrderNo();
//			}
//
//			saveData[i] = lows;
//			i++;
//		}
		// 保存数据
		Db.batch(insertSql, excelData, 100);
	}
	
	public String exportExcel(String ids,String tempfile,String genrFileName) throws Exception {
		String sqlIn = sqlIn(ids);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.deliveryDetailed.procurement", param);
		List<Record> list = Db.find(sql);
		for (Record r : list) {
			String price = r.getStr("price");
			String salesPrice = r.getStr("salesPrice");
			
			BigDecimal bdsalesPrice = new BigDecimal(salesPrice.replaceAll(",", ""));
			BigDecimal bdprice = new BigDecimal(price.replaceAll(",", ""));
			
			BigDecimal bdBit = new BigDecimal(1.17d);
			BigDecimal itemSalesprice = bdprice.multiply(bdBit).setScale(2, BigDecimal.ROUND_HALF_UP).add(bdsalesPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
			r.set("salesPrice", itemSalesprice.toString());
		}
		String sumSql = getSqlByBeetl("trading.deliveryDetailed.procurementSum", param);
		Record sum = Db.findFirst(sumSql);
		
		String filePath = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + "files"+ File.separator + "export";
		//String templateDir = filePath + File.separator + "template";
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdir();
		}
		String generalFilePath = filePath + File.separator  + genrFileName + ToolDateTime.format(new Date(), "_yyyy_MM_dd_HH_mm_ss_SSS")+ ".xls";

		// 导出EXCEL
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entitys", list);
		data.put("sum", sum);
		ToolFreemarkParse.parse(BaseHandler.class.getResource("/com/platform/tools/code/tpl/excel/").getPath(), tempfile, generalFilePath, data);
		return generalFilePath;
	}
	
	@SuppressWarnings("unchecked")
	public void pagin(String dataSource, SplitPage splitPage, String selectSqlId, String fromSqlId,String[]tag) {
		String selectSql = getSqlByBeetl(selectSqlId, splitPage.getQueryParam());

		Map<String, Object> map = getFromSql(splitPage, fromSqlId);
		String fromSql = (String) map.get("sql");
		LinkedList<Object> paramValue = (LinkedList<Object>) map.get("paramValue");
		
		if (null != tag && tag.length > 0 && StringUtils.isNotEmpty(tag[0])) {
			fromSql = fromSql + "and dd.tag IN ( " + sqlIn(tag[0]) + " ) ";
		}
		// 分页封装
		Page<?> page = Db.use(dataSource).paginate(splitPage.getPageNumber(), splitPage.getPageSize(), selectSql, null,fromSql, paramValue.toArray());
		splitPage.setTotalPage(page.getTotalPage());
		splitPage.setTotalRow(page.getTotalRow());
		splitPage.setList(page.getList());
		splitPage.compute();
	}

	public void updateState(String table, String ids, String state) {
		String sqlIn = sqlIn(ids);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("table", table);
		param.put("sqlIn", sqlIn);

		String sql = "";
		String no = ToolDateTime.getCurrent("yyMMddhhss");

		if (state.equals("1")) {
			no = "R" + no;
			sql = getSqlByBeetl("trading.deliveryDetailed.updateStateIn", param);
		} else if (state.equals("2")) {
			sql = getSqlByBeetl("trading.deliveryDetailed.updateStateOut", param);
			no = "C" + no;
		}else{
			throw new RuntimeException("updateState: " + state + "非法输入！");
		}
		Db.use().update(sql, state, no);
	}
	
	public static void main(String[] args) {
		
	}

}
