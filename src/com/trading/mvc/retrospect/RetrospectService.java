package com.trading.mvc.retrospect;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.platform.annotation.Service;
import com.platform.mvc.base.BaseService;
import com.trading.mvc.deliverydetailed.DeliveryDetailed;
import com.trading.mvc.planordercomplete.PlanOrderComplete;

@Service(name = RetrospectService.serviceName)
public class RetrospectService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(RetrospectService.class);
	
	public static final String serviceName = "retrospectService";

	public void saveRetr(Retrospect model) {
		String specification = model.getSpecification();
		String[] speciArr = specMatch(specification);
		
		Map<String,Object> queryParams = new HashMap<String,Object>();
		model.getMon();
		//queryParams.put("thickness", speciArr[0]);
		queryParams.put("thickness", speciArr[0]);
		queryParams.put("width", speciArr[1]);
		queryParams.put("length", speciArr[2]);
		List<PlanOrderComplete> pocs = PlanOrderComplete.dao.findByColumnValue(queryParams, "orderItemNo");
		String orderItemNos = "";
		for(PlanOrderComplete p :pocs){
			orderItemNos += p.getOrderItemNo()+",";
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlIn(orderItemNos));
		String sql = getSqlByBeetl("trading.deliveryDetailed.selectInOrderItemNo",param);
		
		List<DeliveryDetailed> dds = DeliveryDetailed.dao.find(sql);
		for(DeliveryDetailed dd : dds){
			dd.setDvPrice(model.getPrice());
			dd.setGapPrice(model.getGapPrice());
			dd.setDocNo(model.getDocNo());
		}
		Db.batchUpdate(dds, 100);

		if (StringUtils.isEmpty(model.getIds())) {
			model.save();
		} else {
			model.update();
		}
	}
	
	/***
	 * 规则为厚 宽 长，这里将规则 7.000*1405 切为数组放置，如果没有长设置为.00
	 * @param specification
	 * @return
	 */
	private String[] specMatch(String specification) {
		String[] sArr = { ".00", ".00", ".00" };
		if (StringUtils.isEmpty(specification)) {
			return sArr;
		}
		String[] ms = specification.split("\\*");
		// 当规则没有长度时设置.00
		for (int i = 0; i < ms.length; i++) {
			sArr[i] = fmtMicrometer(String.format("%.2f", Float.parseFloat(ms[i])));
		}

		return sArr;
	}
	
	/**
	 * 千分位标识
	 * @param text
	 * @return
	 */
	public String fmtMicrometer(String text) {
		DecimalFormat df = null;
		if (text.indexOf(".") > 0) {
			if (text.length() - text.indexOf(".") - 1 == 0) {
				df = new DecimalFormat("###,##0.");
			} else if (text.length() - text.indexOf(".") - 1 == 1) {
				df = new DecimalFormat("###,##0.0");
			} else {
				df = new DecimalFormat("###,##0.00");
			}
		} else {
			df = new DecimalFormat("###,##0");
		}
		double number = 0.0;
		try {
			number = Double.parseDouble(text);
		} catch (Exception e) {
			number = 0.0;
		}
		return df.format(number);
	}
	
	public static void main(String[] args) {
		String specification = "7.000*1405";
		RetrospectService rs = new RetrospectService();
		String[] sArr = rs.specMatch(specification);
		System.out.println(rs.fmtMicrometer(sArr[0]));
	}
}
