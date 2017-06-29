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
import com.trading.mvc.BigDecimalUtils;
import com.trading.mvc.TableUtils;
import com.trading.mvc.TradingConst;
import com.trading.mvc.deliverydetailed.DeliveryDetailed;
import com.trading.mvc.planordercomplete.PlanOrderComplete;
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

	public int saveExcelDatas(UploadFile uploadFile, String indexKey, String dtype,String currentTime) throws Exception {
		String sql = getSqlMy("platform.iedtd.getIedtdByIndexKey");
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();
		String[][] excelData = ToolExcel.readExcelToArray(uploadFile.getFile(), 2, ToolExcel.getColNo(columnsNo));
		excelData = ToolExcel.addIds(excelData);
		excelData = ToolExcel.addOther(excelData, dtype, currentTime);
		Db.batch(insertSql, excelData, 100);
		//setHasSet(excelData);
		return excelData.length;
	}
	
	@SuppressWarnings("unused")
	private void setHasSet(String[][] excelData) {
		String hasSetIds = "";
		String ym = null;

		for (String[] ed : excelData) {
			String priceStr = ed[8];
			String weightStr = ed[9];
			
			BigDecimal price = BigDecimalUtils.getBidDecimal(priceStr);
			BigDecimal weight = BigDecimalUtils.getBidDecimal(weightStr);
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
				updateSalesSettlement(ws.getIds(), invoiceNo,currentDate.toString());
			}else{
				saveSalesSettlement(ws, ssl, invoiceNo,currentDate.toString());
			}
			
			ws.setInvoice(invoiceNo);
			ws.setHasConfirm(WiscoSettlement.YES);
			ws.setSaveInvoceDate(currentDate.toString());
			ws.update();
		}
	}

	private void saveSalesSettlement(WiscoSettlement ws, SalesSettlement ssl, String invoiceNo,String saveDate) throws Exception{
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
		BigDecimal salesPrice = calcuSalesPrice(BigDecimalUtils.getBidDecimal(ws.getPrice()), BigDecimalUtils.getBidDecimal(addPirce));

		// 计算销售不含税价=销售合同价/1.17(4舍5入，2位数）
		BigDecimal noTaxPrice = noTaxPrice(salesPrice);

		// 总金额=销售合同价*出货重量
		BigDecimal totalAmount = totalAmountPrice(salesPrice, BigDecimalUtils.getBidDecimal(ws.getWeight()));

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
		
		ssl.setSaveDate(saveDate);
		ssl.save();

	}

	private void updateSalesSettlement(String ids, String invoce,String currentDate) {
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
		BigDecimal bd = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117);
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
		BigDecimal bdBit = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117);
		BigDecimal itemSalesprice = itemPrice.multiply(bdBit).setScale(2, BigDecimal.ROUND_HALF_UP).add(salesAddPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
		return itemSalesprice;
	}

	/**
	 *  计算销售不含税价=销售合同价/1.17(4舍5入，2位数）
	 * @return
	 */
	private BigDecimal noTaxPrice(BigDecimal salesprice){
		BigDecimal bd = BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117);
		BigDecimal noTaxPrice = salesprice.divide(bd,2, BigDecimal.ROUND_HALF_UP);
		return noTaxPrice;
	}
	
//	private BigDecimal getBigDecimal(String numStr) {
//		return new BigDecimal(numStr.replaceAll(",", ""));
//	}
//	
//	private BigDecimal getBit() {
//		return new BigDecimal("1.17");
//	}

	public void saveSalesSettleOther(SalesSettlement ss){
		String soNo = ss.getSalesOrderIds();
		SalesOrder so = SalesOrder.dao.findFirstByColumnValue("salesOrderNo", soNo);
		//总价
		String ta = ss.getTotalAmount();
		
		BigDecimal totalAmount = BigDecimalUtils.getBidDecimal(ta);
		//货款金额
		BigDecimal goodsAmount = goodsAmount(BigDecimalUtils.getBidDecimal(ta));
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
		BigDecimal sumWeight = BigDecimalUtils.getBidDecimal("0");      //数量
		BigDecimal sumLoad = BigDecimalUtils.getBidDecimal("0");			//货款金额
		BigDecimal sumTax = BigDecimalUtils.getBidDecimal("0");			//税款金额
		BigDecimal sumTaxLoad = BigDecimalUtils.getBidDecimal("0");    //价税合计
		BigDecimal sumFreight = BigDecimalUtils.getBidDecimal("0");    //运费合计
		BigDecimal sumTaxLoadFreight = BigDecimalUtils.getBidDecimal("0");    //结算表清单
		
		for (Record r : list) {
			String weight = r.getStr("weight");  //数量
			String load = r.getStr("loan");   //货款金额
			String tax = r.getStr("tax");     //税款金额
			String freight = r.getStr("freight");
			
			BigDecimal bdLoad = BigDecimalUtils.getBidDecimal(load);
			BigDecimal bdTax = BigDecimalUtils.getBidDecimal(tax);
			BigDecimal bdFreight = BigDecimalUtils.getBidDecimal(freight);
			BigDecimal bdWeight = BigDecimalUtils.getBidDecimal(weight);
			
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

	public void save(WiscoSettlement ws, String salesAddPrice, String salesWeight, String quantity) {
		Poci poci = getPociByInvoceNo(ws.getOrderItemNo());
		SalesOrder so = getSoByInvoceNo(ws.getOrderItemNo());
		if (null == poci || null == so) {
			throw new RuntimeException("订单项次号： " + ws.getOrderItemNo() + "对应的合同不存在或没设置！");
		}
		String saveDate = ToolDateTime.getCurrent(ToolDateTime.pattern_yymmdd);
		Timestamp ts = ToolDateTime.getSqlTimestamp(ToolDateTime.getDate());
		// 采购结算价
		String priceStr = ws.getStr("price");
		String weightStr = ws.getStr("weight");
		
		BigDecimal bdPirce = BigDecimalUtils.getBidDecimal(priceStr);
		BigDecimal bdWeight = BigDecimalUtils.getBidDecimal(weightStr);
		
		//采购货款金额 = 采购结算价 * 采购实结重量
		BigDecimal bdLoan = bdPirce.multiply(bdWeight);
		//采购税额 = 采购货款金额 * 0.17
		BigDecimal bdTax = bdLoan.multiply(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_017));

		ws.setPrice(priceStr);
		ws.setLoan(bdLoan.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		ws.setTax(bdTax.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		ws.setSettlementNo(poci.getInvoceNo());
		ws.setFreight("0");
		ws.setWaterTIP("0");
		ws.setWaterTIP("0");
		ws.setExtensionFreight("0");
		ws.setHasConfirm("1");
		ws.setDtype("3");
		ws.setSaveInvoceDate(ts.toString());
		ws.setSaveDate(saveDate);
		ws.setContractMonth(poci.getCDate());
		if (StringUtils.isEmpty(ws.getIds())) {
			ws.save();
		} else {
			ws.update();
		}
		
		setPlanOrderComplete(ws);
		setSalesSettlement(ws, saveDate, so, salesAddPrice, salesWeight);
		setDeliveryDetailed(ws, quantity);
	}
	
	private void setPlanOrderComplete(WiscoSettlement ws) {
		String orderItemNo = ws.getOrderItemNo();
		orderItemNo = orderItemNo.substring(0, orderItemNo.length() - 3) + "A01";
		PlanOrderComplete fpoc = PlanOrderComplete.dao.findFirstByColumnValue("orderItemNo", orderItemNo);
		if (fpoc == null) {
			throw new RuntimeException("订单项次号： 【" + orderItemNo + "】的采购计划不存在！");
		}

		PlanOrderComplete tpoc = PlanOrderComplete.dao.findFirstByColumnValue("orderItemNo", orderItemNo);
		if (tpoc == null) {
			PlanOrderComplete poc = new PlanOrderComplete();
			poc.setOrderItemNo(ws.getOrderItemNo());
			poc.setPName(ws.getPName());
			poc.setGrade(ws.getGrade());
			poc.setPrice(fpoc.getPrice());
			poc.save();
		}
	}

	private Poci getPociByInvoceNo(String orderItemNo) {
		int length = orderItemNo.length();
		if (length >= 10) {
			String pociInvoiceNo = orderItemNo.substring(0, length - 3);
			return Poci.dao.findFirstByColumnValue("invoceNo", pociInvoiceNo);
		} else {
			throw new RuntimeException("订单项次号： " + orderItemNo + "位数小于10！");
		}
	}
	
	private void setDeliveryDetailed(WiscoSettlement ws, String quantity) {
		DeliveryDetailed dd = new DeliveryDetailed();
		dd.setOrderItemNo(ws.getOrderItemNo());
		dd.setContractMonth(ws.getContractMonth());
		dd.setWeight(ws.getWeight());
		dd.setWriteOffDate(ws.getSaveInvoceDate());
		dd.setDtype("3");
		dd.setQuantity(quantity);
		String specification = ws.getSpecification();
		
		if (StringUtils.isNotEmpty(specification) && specification.indexOf("*") > 0) {
			String[] spec = specification.split("\\*");

			for (int i = 0; i <= spec.length; i++) {
				if (i == 0) {
					dd.setThickness(spec[0]);
				} else if (i == 2) {
					dd.setWidth(spec[1]);
				} else if (i == 3) {
					dd.setLength(spec[2]);
				}
			}
		}
		dd.save();
	}
	
	private SalesOrder getSoByInvoceNo(String orderItemNo) {
		int length = orderItemNo.length();
		if (length >= 10) {
			String pociInvoiceNo = orderItemNo.substring(0, length - 3);
			return SalesOrder.dao.findFirstByColumnValue("orderItemNo", pociInvoiceNo);
		} else {
			throw new RuntimeException("订单项次号： " + orderItemNo + "位数小于10！");
		}
	}
	
	/**
	 * 保存销售结算
	 * @param ws 采购结算
	 * @param salesAddPrice 销售加价
	 * @param salesWeight 销售实结重量
	 */
	private void setSalesSettlement(WiscoSettlement ws,String saveDate,SalesOrder so,String salesAddPrice,String salesWeight) {
		SalesSettlement ss = new SalesSettlement();
		//采购合同价 = 采购结算价*1.17; 
		BigDecimal bdPpirce = BigDecimalUtils.getBidDecimal(ws.getPrice()).multiply(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117));
		
		//销售合同价格 = 采购合同价 + 销售加价
		BigDecimal bdSalesInvocePirce = bdPpirce.add(BigDecimalUtils.getBidDecimal(salesAddPrice));
				
		//销售不含税价  =销售合同价格/1.17(保留7位小数)
		BigDecimal bdNotax = bdSalesInvocePirce.divide(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117), 7, BigDecimal.ROUND_HALF_UP);
		
		//销售货款金额=销售不含税价*销售实结重量
		BigDecimal bdGoodsAmount = bdNotax.multiply(BigDecimalUtils.getBidDecimal(salesWeight));
		
		//销售税额=销售货款金额*0.17
		BigDecimal bdTaxPrice = bdGoodsAmount.multiply(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_017));
		
		//价税合计=销售货款金额+销售税额
		BigDecimal totalAmount = bdGoodsAmount.add(bdTaxPrice);
		
		ss.setWiscoSettlementIds(ws.getIds());
		ss.setOrderItemNo(ws.getOrderItemNo());
		ss.setGoodsAmount(bdGoodsAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		ss.setTaxPrice(bdTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		ss.setInvoicePrice(bdSalesInvocePirce.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		ss.setNoTaxPrice(bdNotax.toString());
		ss.setTotalAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
		ss.setInvoiceNo(ws.getInvoice());
		ss.setSaveDate(saveDate);
		ss.setOrderUnitId(so.getOrderUnit());
		ss.setManufacturerId(so.getManufacturer());
		ss.setPName(ws.getPName());
		ss.setGrade(ws.getGrade());
		ss.setSpecification(ws.getSpecification());
		ss.setWeight(salesWeight);
		ss.setContractMonth(ws.getContractMonth());
		ss.setAddPrice(salesAddPrice);
		ss.setSalesOrderNo(so.getSalesOrderNo());
		ss.setSalesOrderIds(so.getIds());
		ss.save();
		//ss.setSalesOrderIds(salesOrderIds);
		//ss.setInvoiceNo(invoice);
		//ss.save();
	}

//	private Poci setPoci(String orderItemNo, String contractMonth) {
//		String invoiceNo = orderItemNo.substring(0, orderItemNo.length() - 3);
//		Poci p = Poci.dao.findFirstByColumnValue("invoceNo", invoiceNo);
//		if (p == null) {
//			p = new Poci();
//			p.setInvoceNo(invoiceNo);
//			p.setCDate(contractMonth);
//			p.save();
//		}
//		return p;
//	}
//	private String getOrderItemNo(String orderItemNo) {
//		orderItemNo = "W" + orderItemNo;
//		String sql = "SELECT COUNT(*) as count FROM b_trading_wiscosettlement where orderItemNo = ?";
//		Record r = Db.findFirst(sql, orderItemNo);
//		String countNo = "0";
//		if (null != r) {
//			countNo = String.valueOf(r.get("count"));
//		}
//		if (countNo.length() <= 1) {
//			countNo += "0" + countNo;
//		}
//		orderItemNo += countNo;
//		return orderItemNo;
//	}
//	private Manufacturer setManufacturer(String unitName) {
//		Manufacturer m = Manufacturer.dao.findFirstByColumnValue("name", unitName);
//		if (null == m) {
//			m = new Manufacturer();
//			m.setName(unitName);
//			m.save();
//		}
//		return m;
//	}
	
//	private SalesOrder setSalesOrder(Poci p, Manufacturer m) {
//		String invoceNo = p.getStr("invoceNo");
//		SalesOrder so = SalesOrder.dao.findFirstByColumnValue("orderItemNo", invoceNo);
//		if (so != null) {
//			return so;
//		}
//		so = new SalesOrder();
//		so.setSalesPrice("0");
//		so.setFreightage("0");
//		so.setStorag("0");
//		so.setPocIds(p.getIds());
//		so.setSalesPrice("0");
//		so.setOrderItemNo(invoceNo);
//		so.setManufacturer(m.getIds());
//		so.save();
//		return so;
//	}

	/**
	 * 追溯
	 * @param selIds  选择的发货明细编号
	 * @param invoiceNo  发票号
	 * @param traceRange 追溯幅度
	 * @param docNo  追溯文号
	 */
	public void saveTrace(String selIds, String invoiceNo, String traceRange,String docNo) {
		String sqlIn = sqlIn(selIds);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn);
		String sql = getSqlByBeetl("trading.wiscoSettlement.selectDeliverydetailedIn", param);
		
		List<DeliveryDetailed> list = DeliveryDetailed.dao.find(sql);
		BigDecimal bTraceRange = BigDecimalUtils.getBidDecimal(traceRange);
		
		//总重量
		BigDecimal totalWeight = BigDecimalUtils.getBidDecimal("0"); 
		//总金额
		BigDecimal totalGapPrice = BigDecimalUtils.getBidDecimal("0"); 
		//总税额
		BigDecimal totalPrice = BigDecimalUtils.getBidDecimal("0"); 
		//总价税合计
		BigDecimal totalPriceTax = BigDecimalUtils.getBidDecimal("0"); 
		
		for (DeliveryDetailed dd : list) {
			String weight = dd.getWeight();
			BigDecimal bWeight = BigDecimalUtils.getBidDecimal(weight);
			totalWeight = totalWeight.add(bWeight);
			
			//追溯金额 = 追溯幅度 * 重量 / 1.17
			BigDecimal gapPrice = bTraceRange.multiply(bWeight).divide(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_117), 2, BigDecimal.ROUND_HALF_UP);  
			dd.setGapPrice(gapPrice.toString());
			totalGapPrice = totalGapPrice.add(gapPrice);
			
			//追溯税额 = 追溯金额 * 0.17
			BigDecimal dvPrice = gapPrice.multiply(BigDecimalUtils.getBidDecimal(BigDecimalUtils.WIS_BIG_017)).setScale(2, BigDecimal.ROUND_HALF_UP);
			dd.setDvPrice(dvPrice.toString());
			totalPrice = totalPrice.add(dvPrice);
			
			//价税合计 = 追溯金额 + 税额
			BigDecimal bPriceTax = gapPrice.add(dvPrice);					
			dd.setPricetax(bPriceTax.toString());
			totalPriceTax = totalPriceTax.add(bPriceTax);
			
			dd.setHasSet("1");
			dd.setTraceRange(traceRange);
			dd.setDocNo(docNo);
			dd.update();
		}
		//结算清单号
		String no = ToolDateTime.getCurrent("yyMMdd");
		
		//追溯合计
		if (!list.isEmpty()) {
			WiscoSettlement ws = new WiscoSettlement();
			String sNo = TradingConst.WiscoSettlement_cz + no;
			String settNo = TableUtils.getNo(6, "b_trading_wiscosettlement", "settlementNo", sNo);
			ws.setSettlementNo(settNo);
			ws.setWeight(totalWeight.toString());
			ws.setLoan(totalGapPrice.toString());
			ws.setTax(totalPrice.toString());
			ws.setInvoice(invoiceNo);
			ws.save();
			
			SalesSettlement ss = new SalesSettlement();
			String saNo = TradingConst.WiscoSettlement_xz + no;
			String flagNo = TableUtils.getNo(6, "b_trading_salessettlement", "flag", saNo);
			ss.setFlag(flagNo);
			ss.setWeight(totalWeight.toString());
			ss.setGoodsAmount(totalGapPrice.toString());
			ss.setTaxPrice(totalPrice.toString());
			ss.setInvoiceNo(invoiceNo);
			ss.setTotalAmount(totalPriceTax.toString());
			ss.save();
		}
	}
	
	public static void main(String[] args) {
		String s = "5.00*1,500.00*6,000.00";
		String[] spec = s.split("\\*");
		System.out.println(spec);
	}

	public void update(WiscoSettlement ws, String salesAddPrice, String salesWeight, String quantity) {
		SalesSettlement ss = SalesSettlement.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != ss) {
			ss.delete();
		}
		DeliveryDetailed dd = DeliveryDetailed.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != dd) {
			dd.delete();
		}
		PlanOrderComplete poc = PlanOrderComplete.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != poc) {
			poc.delete();
		}
		save(ws, salesAddPrice, salesWeight, quantity);
	}

	public void del(String ids) {
		WiscoSettlement ws = WiscoSettlement.dao.findById(ids);
		SalesSettlement ss = SalesSettlement.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != ss) {
			ss.delete();
		}
		DeliveryDetailed dd = DeliveryDetailed.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != dd) {
			dd.delete();
		}
		if (ws != null) {
			ws.delete();
		}
		PlanOrderComplete poc = PlanOrderComplete.dao.findFirstByColumnValue("orderItemNo", ws.getOrderItemNo());
		if (null != poc) {
			poc.delete();
		}
	}
}
