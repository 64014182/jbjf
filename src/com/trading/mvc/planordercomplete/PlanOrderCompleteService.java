package com.trading.mvc.planordercomplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Service;
import com.platform.mvc.base.BaseService;
import com.platform.mvc.iedtd.Iedtd;
import com.platform.tools.ToolExcel;
import com.platform.tools.ToolUuid;
import com.trading.mvc.poci.Poci;

@Service(name = PlanOrderCompleteService.serviceName)
public class PlanOrderCompleteService extends BaseService {
	
	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PlanOrderCompleteService.class);
	
	public static final String serviceName = "planOrderCompleteService";

	public int savePlanOrders(UploadFile uploadFile, String indexKey) throws Exception {
		String sql = getSqlMy("platform.iedtd.getIedtdByIndexKey");
		Iedtd iedtd = Iedtd.dao.findFirst(sql, indexKey);
		String columnsNo = iedtd.getExcelDataColNo();
		String insertSql = iedtd.getIntoDbSQL();

//		String[][] eDatas = ToolExcel.readExcelToArray(uploadFile.getFile(), "Sheet1", 3, true,ToolExcel.getColNo(columnsNo));
		String[][] eDatas = ToolExcel.readExcelToArray(uploadFile.getFile(), 2, ToolExcel.getColNo(columnsNo));

		List<Poci> pociList = new ArrayList<>();
		Map<String,Poci> pociMap= new HashMap<>(); 
		for (String[] ed : eDatas) {
			String itemOrderNo = ed[0];
			String month = ed[1];
			String invoiceNo = itemOrderNo.substring(0, 8);

			if (pociMap.get(invoiceNo) == null) {
				pociMap.put(invoiceNo, new Poci(ToolUuid.get32UUID(), invoiceNo, month));
			}
		}
		pociList.addAll(pociMap.values());
		Db.batchSave(pociList, 100);
		String[][] saveDatas = addIds(eDatas);
		Db.batch(insertSql, saveDatas, 100);
		return eDatas.length;
	}
	
	private String[][] addIds(String[][] eDatas) {
		String[][] newDatas = new String[eDatas.length][eDatas[0].length + 2];

		int i = 0;
		for (String[] ed : eDatas) {
			String cNo = ed[0].substring(0, 8);
			String ids = ToolUuid.get32UUID();
			String[] gg = (String[]) ArrayUtils.add(ed, 0, ids);
			gg = (String[]) ArrayUtils.add(gg, cNo);
			newDatas[i] = gg;
			i++;
		}
		return newDatas;
	}
	
	
	public static void main(String []args){
//		String[] array1 = {"1","2"};
//		String[] array2 = new String[3];
//		ArrayUtils.addAll(array2, array1);
//		System.out.println(array2);
		
		String[] t={"22","yy"};
	   String[] gg=(String[]) ArrayUtils.add(t,0 ,"jj");//{"22","yy","jj"}
	   System.out.println(gg);
	}
}
