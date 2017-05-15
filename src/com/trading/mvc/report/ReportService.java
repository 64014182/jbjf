package com.trading.mvc.report;

import java.util.List;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Service;

import com.platform.mvc.base.BaseService;

@Service(name = ReportService.serviceName)
public class ReportService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ReportService.class);
	
	public static final String serviceName = "reportService";
	
	/**
	 * 发出未结查询
	 * @return
	 */
	public List<Record> iak(){
		
		return null;
	}
}
