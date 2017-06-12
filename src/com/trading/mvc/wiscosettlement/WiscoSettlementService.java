package com.trading.mvc.wiscosettlement;

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
import com.trading.mvc.manufacturer.Manufacturer;
import com.trading.mvc.poci.Poci;
import com.trading.mvc.salesorder.SalesOrder;
import com.trading.mvc.salessettlement.SalesSettlement;

@Service(name = WiscoSettlementService.serviceName)
public class WiscoSettlementService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(WiscoSettlementService.class);
	
	public static final String serviceName = "wiscoSettlementService";
	
	@SuppressWarnings("unchecked")
	public void pagin(String dataSource, SplitPage splitPage, String selectSqlId, String fromSqlId,String[]invoices) {
		String selectSql = getSqlByBeetl(selectSqlId, splitPage.getQueryParam());

		Map<String, Object> map = getFromSql(splitPage, fromSqlId);
		String fromSql = (String) map.get("sql");
		LinkedList<Object> paramValue = (LinkedList<Object>) map.get("paramValue");
		
		if (null != invoices && invoices.length > 0 && StringUtils.isNotEmpty(invoices[0])) {
			fromSql = fromSql + "and t.invoice IN ( " + sqlIn(invoices[0]) + " ) ";
		}
		// 分页封装
		Page<?> page = Db.use(dataSource).paginate(splitPage.getPageNumber(), splitPage.getPageSize(), selectSql, null,fromSql, paramValue.toArray());
		splitPage.setTotalPage(page.getTotalPage());
		splitPage.setTotalRow(page.getTotalRow());
		splitPage.setList(page.getList());
		splitPage.compute();
	}
	
	public List<WiscoSettlement> getWssByIds(String ids){
		String sqlIn = sqlIn(ids);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.wiscoSettlement.selectIn", param);
		List<WiscoSettlement> ws = WiscoSettlement.dao.find(sql);
		return ws;
	}

	public int saveExcelDatas(UploadFile uploadFile, String indexKey) throws Exception {
		String sql = getSqlMy("platform.iedtd.getIedtdByIndexKey");
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();
		//String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), 3, ToolExcel.getColNo(columnsNo));
		String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), "Sheet1", 3, true,ToolExcel.getColNo(columnsNo));
		Db.batch(insertSql, excelData, 100);
		setHasSet(excelData);
		return excelData.length;
	}
	
	private void setHasSet(String[][] excelData) {
		String hasSetIds = "";
		String ym = null;

		for (String[] ed : excelData) {
			String priceStr = ed[8];
			String weightStr = ed[9];

			BigDecimal price = new BigDecimal(priceStr.replaceAll(",", ""));
			BigDecimal weight = new BigDecimal(weightStr.replaceAll(",", ""));
			int r = price.compareTo(BigDecimal.ZERO);
			int r1 = weight.compareTo(BigDecimal.ZERO);
			if (r == 0 && r1 == 0) {
				String orderItemNo = ed[3];
				ym = ed[7];
				hasSetIds += orderItemNo + ",";
			}
		}

		if (StringUtils.isNotEmpty(hasSetIds)) { // 标注追溯结算
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sqlIn", sqlIn(hasSetIds));
			param.put("contractMonth", ym);
			String udpateHasSetSql = getSqlByBeetl("trading.deliveryDetailed.updateHasSet", param);
			Db.update(udpateHasSetSql);
		}
	}
	
	/**
	 * 保存销售结算
	 * @param settlementNo
	 * @param invoiceNo
	 * @param ssl
	 * @throws Exception
	 */
	public void saveSalesSetl(String settlementNo, String invoiceNo, SalesSettlement ssl) throws Exception {
		List<WiscoSettlement> wsList = WiscoSettlement.dao.findByColumnValue("settlementNo", settlementNo);	
		Timestamp currentDate = ToolDateTime.getSqlTimestamp(ToolDateTime.getDate());
		for (WiscoSettlement ws : wsList) {
			if(StringUtils.isNotEmpty(ws.getInvoice())){
				updateSalesSettlement(ws.getIds(), invoiceNo,currentDate);
			}else{
				saveSalesSettlement(ws, ssl, invoiceNo,currentDate);
			}
			
			ws.setInvoice(invoiceNo);
			ws.setHasConfirm(WiscoSettlement.YES);
			ws.setSaveInvoceDate(currentDate);
			ws.update();
		}
	}

	private void saveSalesSettlement(WiscoSettlement ws, SalesSettlement ssl, String invoiceNo,Timestamp currentDate) throws Exception{
		String orderItemNo = ws.getOrderItemNo().substring(0, 8);
		SalesOrder so = SalesOrder.dao.findFirstByColumnValue("orderItemNo", orderItemNo);

		if (so == null) {
			throw new RuntimeException("销售合同： " + orderItemNo.substring(0, 8) + "未设置！");
		}

		String addPirce = so.getSalesPrice(); // 销售加价
		if (StringUtils.isEmpty(addPirce)) {
			throw new RuntimeException("订单项次号为： " + orderItemNo + "的采购完成计划！未设置销售合同加价！");
		}

		// 计算销售合同价
		BigDecimal salesPrice = calcuSalesPrice(getBigDecimal(ws.getPrice()), getBigDecimal(addPirce));

		// 计算销售不含税价=销售合同价/1.17(4舍5入，2位数）
		BigDecimal noTaxPrice = noTaxPrice(salesPrice);

		// 总金额=销售合同价*出货重量
		BigDecimal totalAmount = totalAmountPrice(salesPrice, getBigDecimal(ws.getWeight()));

		// 货款金额=总金额/1.17（四舍五入到二位数）
		BigDecimal goodsAmount = goodsAmount(totalAmount);

		// 税款金额=总金额-货款金额
		BigDecimal taxPrice = totalAmount.subtract(goodsAmount).setScale(2, BigDecimal.ROUND_HALF_UP);

		ssl.setWsAndSo(ws,so);
		ssl.setInvoicePrice(salesPrice.toString());
		ssl.setNoTaxPrice(noTaxPrice.toString());
		ssl.setTotalAmount(totalAmount.toString());
		ssl.setGoodsAmount(goodsAmount.toString());
		ssl.setTaxPrice(taxPrice.toString());
		ssl.setInvoiceNo(invoiceNo);
		
		ssl.setSaveDate(currentDate);
		ssl.save();

	}

	private void updateSalesSettlement(String ids, String invoce,Timestamp currentDate) {
		SalesSettlement ss = SalesSettlement.dao.findFirstByColumnValue("wiscoSettlementIds", ids);
		ss.setInvoiceNo(invoce);
		ss.update();
	}

	/**
	 * 货款金额=总金额/1.17（四舍五入到二位数）
	 * 
	 * @param totalAmount
	 */
	private BigDecimal goodsAmount(BigDecimal totalAmount) {
		BigDecimal bd = getBit();
		BigDecimal goodsAmount = totalAmount.divide(bd, 2, BigDecimal.ROUND_HALF_UP);
		return goodsAmount;
	}

	/**
	 * 总金额=销售合同价*出货重量
	 * @param weight
	 * @return
	 */
	private BigDecimal totalAmountPrice(BigDecimal salesprice,BigDecimal weight) {
		// 总金额=销售合同价*出货重量
		BigDecimal totalAmount = salesprice.multiply(weight).setScale(2, BigDecimal.ROUND_HALF_UP);
		return totalAmount;
	}

	/**
	 * 计算 销售合同价= 项次价格*1.17+销售加价
	 * @param itemPrice
	 * @param salesAddPrice
	 * @return
	 */
	private BigDecimal calcuSalesPrice(BigDecimal itemPrice, BigDecimal salesAddPrice) {
		BigDecimal bdBit = getBit();
		BigDecimal itemSalesprice = itemPrice.multiply(bdBit).setScale(2, BigDecimal.ROUND_HALF_UP).add(salesAddPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
		return itemSalesprice;
	}

	/**
	 *  计算销售不含税价=销售合同价/1.17(4舍5入，2位数）
	 * @return
	 */
	private BigDecimal noTaxPrice(BigDecimal salesprice){
		BigDecimal bd = getBit();
		BigDecimal noTaxPrice = salesprice.divide(bd,2, BigDecimal.ROUND_HALF_UP);
		return noTaxPrice;
	}
	
	private BigDecimal getBigDecimal(String numStr) {
		return new BigDecimal(numStr.replaceAll(",", ""));
	}
	
	private BigDecimal getBit() {
		return new BigDecimal(1.17d);
	}

	public void saveSalesSettleOther(SalesSettlement ss){
		String soNo = ss.getSalesOrderIds();
		SalesOrder so = SalesOrder.dao.findFirstByColumnValue("salesOrderNo", soNo);
		//总价
		String ta = ss.getTotalAmount();
		
		BigDecimal totalAmount = getBigDecimal(ta);
		//货款金额
		BigDecimal goodsAmount = goodsAmount(getBigDecimal(ta));
		// 税款金额=总金额-货款金额
		BigDecimal taxPrice = totalAmount.subtract(goodsAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		ss.setSalesOrderIds(so.getIds());
		ss.setGoodsAmount(goodsAmount.toString());
		ss.setTaxPrice(taxPrice.toString());
		ss.save();
	}
	
	public String exportExcel(String ids,String orderUnit) throws Exception {
		String sqlIn = sqlIn(ids);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.wiscoSettlement.selectPurchase", param);
		List<Record> list = Db.find(sql);
		
		Record sum = new Record();
		BigDecimal sumWeight = new BigDecimal(0);      //数量
		BigDecimal sumLoad = new BigDecimal(0);			//货款金额
		BigDecimal sumTax = new BigDecimal(0);			//税款金额
		BigDecimal sumTaxLoad = new BigDecimal(0);    //价税合计
		BigDecimal sumFreight = new BigDecimal(0);    //运费合计
		BigDecimal sumTaxLoadFreight = new BigDecimal(0);    //结算表清单
		
		for (Record r : list) {
			String weight = r.getStr("weight");  //数量
			String load = r.getStr("loan");   //货款金额
			String tax = r.getStr("tax");     //税款金额
			String freight = r.getStr("freight");
			
			BigDecimal bdLoad = getBigDecimal(load);
			BigDecimal bdTax = getBigDecimal(tax);
			BigDecimal bdFreight = getBigDecimal(freight);
			BigDecimal bdWeight = getBigDecimal(weight);
			
			BigDecimal bdTaxLoad = bdLoad.add(bdTax);
			BigDecimal taxLoadFreight = bdTaxLoad.add(bdFreight);
			r.set("taxLoad", bdTaxLoad.toString());
			r.set("taxLoadFreight", taxLoadFreight.toString());

			sumWeight = sumWeight.add(bdWeight);
			sumLoad = sumLoad.add(bdLoad);
			sumTax = sumTax.add(bdTax);
			sumTaxLoad = sumTaxLoad.add(bdTaxLoad);
			sumFreight = sumFreight.add(bdFreight);
			sumTaxLoadFreight = sumTaxLoadFreight.add(taxLoadFreight);
		}
		sum.set("sumWeight", sumWeight.toString());
		sum.set("sumLoad", sumLoad.toString());
		sum.set("sumTax", sumTax.toString());
		sum.set("sumTaxLoad", sumTaxLoad.toString());
		sum.set("sumFreight", sumFreight.toString());
		sum.set("sumTaxLoadFreight", sumTaxLoadFreight.toString());
		
		String filePath = PathKit.getWebRootPath() + File.separator + "WEB-INF" + File.separator + "files"+ File.separator + "export";
		//String templateDir = filePath + File.separator + "template";
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdir();
		}
		String generalFilePath = filePath + File.separator + orderUnit + "采购结算" + ToolDateTime.format(new Date(), "_yyyy_MM_dd_HH_mm_ss_SSS")+ ".xls";

		// 导出EXCEL
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("entitys", list);
		data.put("sum", sum);
		data.put("orderUnit", orderUnit);
		ToolFreemarkParse.parse(BaseHandler.class.getResource("/com/platform/tools/code/tpl/excel/").getPath(), "purchase.xml", generalFilePath, data);
		return generalFilePath;
	}

	public void save(WiscoSettlement ws,String unitName) {
		String loanStr = ws.getStr("loan");
		String weightStr = ws.getStr("weight");
		
		BigDecimal bdLoan = getBigDecimal(loanStr);// 货款金额
		BigDecimal bdWeight = getBigDecimal(weightStr);// 重量
		
		BigDecimal price = bdLoan.divide(bdWeight,2, BigDecimal.ROUND_HALF_UP);  //单价  货款除重量
		ws.setPrice(price.toString());
		
		BigDecimal bdBit = new BigDecimal(0.17d);                
		BigDecimal bdTax = bdLoan.multiply(bdBit).setScale(2, BigDecimal.ROUND_HALF_UP);
		ws.setTax(bdTax.toString());     //税额  		货款金额 * 0.17
		ws.setFreight("0");
		ws.setWaterTIP("0");
		ws.setWaterTIP("0");
		ws.setExtensionFreight("0");
		ws.setHasConfirm("1");
		ws.setSaveInvoceDate(ToolDateTime.getSqlTimestamp(ToolDateTime.getDate()));
		
		String orderItemNo = ws.get("orderItemNo");
		
		orderItemNo = getOrderItemNo(ws.getStr("contractMonth"));
		
		Poci poci = setPoci(orderItemNo, ws.getStr("contractMonth"));
		
		Manufacturer m = Manufacturer.dao.findById(unitName);
		SalesOrder so = setSalesOrder(poci,m);
		ws.save();
		
		setSalesSettlement(ws.getIds(),so.getIds(),ws.getInvoice());
	}

	private String getOrderItemNo(String orderItemNo) {
		orderItemNo = "W" + orderItemNo;
		String sql = "SELECT COUNT(*) as count FROM b_trading_wiscosettlement where orderItemNo = ?";
		Record r = Db.findFirst(sql, orderItemNo);
		String countNo = "0";
		if (null != r) {
			countNo = String.valueOf(r.get("count"));
		}
		if (countNo.length() <= 1) {
			countNo += "0" + countNo;
		}
		orderItemNo += countNo;
		return orderItemNo;
	}

	private void setSalesSettlement(String wiscoSettlementIds,String salesOrderIds,String invoice) {
		SalesSettlement ss = new SalesSettlement();
		ss.setWiscoSettlementIds(wiscoSettlementIds);
		ss.setSalesOrderIds(salesOrderIds);
		ss.setInvoiceNo(invoice);
		ss.save();
	}

	private Poci setPoci(String orderItemNo, String contractMonth) {
		String invoiceNo = orderItemNo.substring(0, orderItemNo.length() - 3);
		Poci p = Poci.dao.findFirstByColumnValue("invoceNo", invoiceNo);
		if (p == null) {
			p = new Poci();
			p.setInvoceNo(invoiceNo);
			p.setCDate(contractMonth);
			p.save();
		}
		return p;
	}
	
//	private Manufacturer setManufacturer(String unitName) {
//		Manufacturer m = Manufacturer.dao.findFirstByColumnValue("name", unitName);
//		if (null == m) {
//			m = new Manufacturer();
//			m.setName(unitName);
//			m.save();
//		}
//		return m;
//	}
	
	private SalesOrder setSalesOrder(Poci p, Manufacturer m) {
		String invoceNo = p.getStr("invoceNo");
		SalesOrder so = SalesOrder.dao.findFirstByColumnValue("orderItemNo", invoceNo);
		if (so != null) {
			return so;
		}
		so = new SalesOrder();
		so.setSalesPrice("0");
		so.setFreightage("0");
		so.setStorag("0");
		so.setPocIds(p.getIds());
		so.setSalesPrice("0");
		so.setOrderItemNo(invoceNo);
		so.setManufacturer(m.getIds());
		so.save();
		return so;
	} 
}
