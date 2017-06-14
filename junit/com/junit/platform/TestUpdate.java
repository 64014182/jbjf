package com.junit.platform;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junit.TestBase;

public class TestUpdate extends TestBase {

	@Test
	public void updateSalessettlement() {
		int updateSuccCount = 0;
		
		String sql = "SELECT ids,wiscoSettlementIds FROM b_trading_salessettlement";
		String update = "UPDATE b_trading_salessettlement SET pName = ?, grade = ?, specification = ?, weight = ? WHERE ids = ?";
		List<Record> tbss = Db.find(sql);
		for (Record r : tbss) {
			String ids = r.getStr("ids");
			String wiscoSettlementId = r.get("wiscoSettlementIds");
			if (StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(wiscoSettlementId)) {
				Record r1 = Db.findById("b_trading_wiscosettlement", "ids", wiscoSettlementId);
				String pName = r1.getStr("pName");
				String grade = r1.getStr("grade");
				String specification = r1.getStr("specification");
				String weight = r1.getStr("weight");
				int i = Db.update(update, pName, grade, specification, weight, ids);
				updateSuccCount = updateSuccCount + i;
			}
		}
		System.out.println("查询：" + tbss.size() + " 条");
		System.out.println("更新成功：" +updateSuccCount+ " 条");
	}
	
	@Test
	public void updateSaleOrder() {
		int updateSuccCount = 0;
		
		String sql = "SELECT ids,salesOrderIds FROM b_trading_salessettlement";
		String update = "UPDATE b_trading_salessettlement SET addPrice = ?, manufacturerId = ?, orderUnitId = ?, salesOrderNo = ? WHERE ids = ?";
		List<Record> tbss = Db.find(sql);
		for (Record r : tbss) {
			String ids = r.getStr("ids");
			String salesOrderId = r.get("salesOrderIds");
			if (StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(salesOrderId)) {
				Record r1 = Db.findById("b_trading_salesorder", "ids", salesOrderId);
				String salesOrderNo = r1.getStr("salesOrderNo");
				String salesPrice = r1.getStr("salesPrice");
				String manufacturerId = r1.getStr("manufacturer");
				String orderUnitId = r1.getStr("orderunit");
				
				int i = Db.update(update, salesPrice, manufacturerId, orderUnitId,salesOrderNo, ids);
				updateSuccCount = updateSuccCount + i;
			}
		}
		System.out.println("查询：" + tbss.size() + " 条");
		System.out.println("更新成功：" +updateSuccCount+ " 条");
	}

}
