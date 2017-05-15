package com.trading.mvc.salessettlement;

import java.io.File;
import java.io.IOException;
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
import com.platform.annotation.Service;
import com.platform.dto.SplitPage;
import com.platform.mvc.base.BaseService;
import com.platform.tools.ToolDateTime;
import com.platform.tools.ToolFreemarkParse;
import com.platform.tools.code.handler.BaseHandler;

@Service(name = SalesSettlementService.serviceName)
public class SalesSettlementService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(SalesSettlementService.class);

	public static final String serviceName = "salesSettlementService";
	
	@SuppressWarnings("unchecked")
	public void pagin(String dataSource, SplitPage splitPage, String selectSqlId, String fromSqlId,String[]invoices) {
		String selectSql = getSqlByBeetl(selectSqlId, splitPage.getQueryParam());

		Map<String, Object> map = getFromSql(splitPage, fromSqlId);
		String fromSql = (String) map.get("sql");
		LinkedList<Object> paramValue = (LinkedList<Object>) map.get("paramValue");
		
		if (null != invoices && invoices.length > 0 && StringUtils.isNotEmpty(invoices[0])) {
			fromSql = fromSql + "and wl.invoice IN ( " + sqlIn(invoices[0]) + " ) ";
		}
		
		// 分页封装
		Page<?> page = Db.use(dataSource).paginate(splitPage.getPageNumber(), splitPage.getPageSize(), selectSql, null,fromSql, paramValue.toArray());
		splitPage.setTotalPage(page.getTotalPage());
		splitPage.setTotalRow(page.getTotalRow());
		splitPage.setList(page.getList());
		splitPage.compute();
	}
	
	/**
	 * 销售统计
	 * 
	 * @throws TemplateException
	 * @throws IOException
	 */
	public String exportExcel(String ids,String orderUnit) throws Exception {
		String sqlIn = sqlIn(ids);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.salesSettlement.selectIn", param);
		List<Record> list = Db.find(sql);
		
		String sumSql = getSqlByBeetl("trading.salesSettlement.selectInSum", param);
		Record sum = Db.findFirst(sumSql);
		String filePath = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + "files"+ File.separator + "export";
		//String templateDir = filePath + File.separator + "template";
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdir();
		}
		String generalFilePath = filePath + File.separator + orderUnit + "销售结算" + ToolDateTime.format(new Date(), "_yyyy_MM_dd_HH_mm_ss_SSS")+ ".xls";

		// 导出EXCEL
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entitys", list);
		sum.set("sumWeight", sum.get("sumWeight").toString());
		data.put("sum", sum);
		data.put("orderUnit", orderUnit);
		ToolFreemarkParse.parse(BaseHandler.class.getResource("/com/platform/tools/code/tpl/excel/").getPath(), "salesSettlement.xml", generalFilePath, data);
		return generalFilePath;
	}

	public static void main(String []args){
		System.out.println(BaseHandler.class.getResource("/com/platform/tools/code/tpl/excel/").getPath());
	}

	public void paging(String dbDatasourceMain, SplitPage splitPage, String sqlidSplitpageselect,
			String sqlidSplitpagefrom, String[] invoiceArray) {
		// TODO Auto-generated method stub
		
	}
}
