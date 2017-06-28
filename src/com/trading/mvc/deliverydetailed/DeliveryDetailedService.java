package com.trading.mvc.deliverydetailed;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import com.trading.mvc.BigDecimalUtils;
import com.trading.mvc.TableUtils;
import com.trading.mvc.TradingConst;
import com.trading.mvc.salessettlement.SalesSettlement;
import com.trading.mvc.wiscosettlement.WiscoSettlement;

@Service(name = DeliveryDetailedService.serviceName)
public class DeliveryDetailedService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeliveryDetailedService.class);

	public static final String serviceName = "deliveryDetailedService";

	public int saveByExcel(UploadFile uploadFile, String sql, String indexKey,String dtype,String currentTime) throws Exception {
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();

		// 读取EXCEL文件数据
		String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), 2, ToolExcel.getColNo(columnsNo));
		excelData = ToolExcel.addIds(excelData);
		excelData = ToolExcel.addOther(excelData, dtype, currentTime);
		Db.batch(insertSql, excelData, 100);
		return excelData.length;
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
			
			BigDecimal bdsalesPrice = BigDecimalUtils.getBidDecimal(salesPrice);
			BigDecimal bdprice = BigDecimalUtils.getBidDecimal(price);
			
			BigDecimal bdBit = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117);
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
		String no = ToolDateTime.getCurrent("yyMMdd");
		String countNo = null;
		if (state.equals(TradingConst.DeliveryDetailedState_in)) {
			no = TradingConst.DeliveryDetailed_In + no;
			countNo = TableUtils.getNo(4, "b_trading_deliverydetailed", "inNo", no);
			sql = getSqlByBeetl("trading.deliveryDetailed.updateStateIn", param);
		} else if (state.equals(TradingConst.DeliveryDetailedState_out)) {
			no = TradingConst.DeliveryDetailed_Out + no; 
			countNo = TableUtils.getNo(4, "b_trading_deliverydetailed", "outNo", no);
			sql = getSqlByBeetl("trading.deliveryDetailed.updateStateOut", param);
		} else {
			throw new RuntimeException("updateState: " + state + "非法输入！");
		}
		Db.use().update(sql, state,countNo);
	}
	
	/**
	 * 更新入库出库单号
	 */
	public void updateStateNo(){
		
	}
	

	/**
	 * 结算
	 * @param selIds	编号
	 * @param invoiceNo 发票号
	 */
	public void saveSettle(String selIds, String invoiceNo) {
		Timestamp currentDate = ToolDateTime.getSqlTimestamp(ToolDateTime.getDate());
		
		String sqlIn = sqlIn(selIds);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.deliveryDetailed.selectDeliverydetailedIn", param);
		
		List<Record> list = Db.find(sql);
		BigDecimal bd = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117);
		BigDecimal bdz = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_017);
		
		for (Record dd : list) {
			String weight = dd.getStr("weight");  	//重量
			String price = dd.getStr("price");  	//订货价格
			String salesPrice = dd.getStr("salesPrice");  	//销售加价
			String contractMonth = dd.getStr("contractMonth");
			String orderItemNo = dd.getStr("orderItemNo");
			String salesOrderNo = dd.getStr("salesOrderNo");
			String orderUnitId = dd.getStr("orderUnitId");
			String manufacturerId = dd.getStr("manufacturerId");
			String solesOrderIds = dd.getStr("solesOrderIds");
			String pName = dd.getStr("pName");
			String grade = dd.getStr("grade");
			String thickness = dd.getStr("thickness");
			String width = dd.getStr("width");
			String length = dd.getStr("length");
			
			BigDecimal bdw = BigDecimalUtils.getBidDecimal(weight);
			
			//采购结算
			//结算价  = 订货价格/1.17
			BigDecimal bprice = BigDecimalUtils.getBidDecimal(price).divide(bd, 7, BigDecimal.ROUND_HALF_UP);
			
			//货款 = 订货价格/1.17 * 重量
			BigDecimal goodsPrice = BigDecimalUtils.getBidDecimal(price).divide(bd, 7, BigDecimal.ROUND_HALF_UP).multiply(bdw).setScale(2, BigDecimal.ROUND_HALF_UP);
			//税款 = 货款*0.17 
			BigDecimal taxPirce = goodsPrice.multiply(bdz).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			//结算清单号
			String no = ToolDateTime.getCurrent("yyMMdd");
			no = TradingConst.WiscoSettlement_Sett + no;
			String settNo = TableUtils.getNo(4, "b_trading_wiscosettlement", "settlementNo", no);
			
			WiscoSettlement ws = new WiscoSettlement();
			ws.setContractMonth(contractMonth);
			ws.setWeight(weight);
			ws.setLoan(goodsPrice.toString());
			ws.setTax(taxPirce.toString());
			ws.setInvoice(invoiceNo);
			ws.setOrderItemNo(orderItemNo);
			ws.setSettlementNo(settNo);
			ws.setSpecification(thickness + "*" + width + "*" + length);
			ws.setPName(pName);
			ws.setGrade(grade);
			ws.setPrice(bprice.toString());
			ws.setSaveInvoceDate(currentDate);
			ws.save();
			
			//销售结算
			//销售合同价 = 订货价格 + 加价
			BigDecimal invoicePrice = BigDecimalUtils.getBidDecimal(price).add(BigDecimalUtils.getBidDecimal(salesPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
			//销售不含税价 = 销售合同价 / 1.17
			BigDecimal noTaxPrice = invoicePrice.divide(bd, 2, BigDecimal.ROUND_HALF_UP);
			//货款 = 销售合同价 / 1.17 * 重量
			BigDecimal goodsAmount = invoicePrice.divide(bd, 7, BigDecimal.ROUND_HALF_UP).multiply(bdw);
			//税款 = 货款 * 1.17 
			BigDecimal taxPrice = goodsAmount.multiply(bdz).setScale(2, BigDecimal.ROUND_HALF_UP);
			//总金额 = 货款 + 税款
			BigDecimal totalAmount = goodsAmount.add(taxPrice);
			
			SalesSettlement ss = new SalesSettlement();
			//ss.setFlag("JS" + timeStamp);
			ss.setContractMonth(contractMonth);
			ss.setOrderItemNo(orderItemNo);
			ss.setWeight(weight);
			ss.setInvoiceNo(invoiceNo);
			ss.setInvoicePrice(invoicePrice.toString());  //销售合同价
			ss.setNoTaxPrice(noTaxPrice.toString());//销售不含税价
			ss.setGoodsAmount(goodsAmount.toString());//货款金额
			ss.setTaxPrice(taxPrice.toString());  //税款金额
			ss.setTotalAmount(totalAmount.toString());//总金额
			ss.setOrderUnitId(orderUnitId);
			ss.setManufacturerId(manufacturerId);
			ss.setSpecification(thickness + "*" + width + "*" + length);
			ss.setSalesOrderNo(salesOrderNo);
			ss.setSalesOrderIds(solesOrderIds);
			ss.setPName(pName);
			ss.setGrade(grade);
			ss.setSaveDate(currentDate);
			ss.save();
			
			String updateSettStateSql = getSqlByBeetl("trading.deliveryDetailed.updateSettState", param);
			Db.update(updateSettStateSql, "1");
		}
	}

}
