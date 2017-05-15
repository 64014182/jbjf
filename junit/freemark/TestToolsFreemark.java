package freemark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.junit.TestBase;
import com.platform.tools.ToolFreemarkParse;

/**
 * 博客单元测试类
 * 
 * @author 董华健 dongcb678@163.com
 */
public class TestToolsFreemark extends TestBase{

	@Test
	public void test()  {
		String sql = "SELECT sum(wl.weight) AS sumWeight, SUM(sales.goodsAmount) AS sumGoodsAmount, SUM(sales.taxPrice) AS sumTaxPrice, SUM(sales.totalAmount) AS sumTotalAmount FROM b_trading_salessettlement sales LEFT JOIN b_trading_salesorder so ON sales.salesOrderIds = so.ids LEFT JOIN b_trading_wiscosettlement wl ON sales.wiscoSettlementIds = wl.ids";	
		Record r = Db.findFirst(sql);
		
		String sql2 = "SELECT * FROM b_trading_salessettlement sales LEFT JOIN b_trading_salesorder so ON sales.salesOrderIds = so.ids LEFT JOIN b_trading_wiscosettlement wl ON sales.wiscoSettlementIds = wl.ids";
		List<Record> rs = Db.find(sql2);
		
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.put("entitys", rs);
			data.put("sum", r);
			ToolFreemarkParse.parse("F:\\workspace\\java\\JfinalUIBv4\\junit\\freemark\\", "salesSettlement.xml", "c:\\test.xls", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
