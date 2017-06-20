package com.trading.mvc.excelinhistory;

import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Service;
import com.platform.mvc.base.BaseService;
import com.platform.tools.ToolDateTime;

@Service(name = ExcelInHistoryService.serviceName)
public class ExcelInHistoryService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ExcelInHistoryService.class);

	public static final String serviceName = "excelInHistoryService";

	public void save(UploadFile uploadFile, String count, String module) {
		ExcelInHistory eih = new ExcelInHistory();
		eih.setFileName(uploadFile.getOriginalFileName());
		eih.setUploadpath(uploadFile.getUploadPath());
		eih.setUploadname(uploadFile.getFileName());
		eih.setRecordCount(count);
		eih.setModule(module);
		eih.setSaveDate(ToolDateTime.getSqlTimestamp());
		eih.save();
	}
}
