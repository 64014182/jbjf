package com.platform.mvc.gc.gctableconf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.annotation.Service;
import com.platform.mvc.base.BaseService;
import com.platform.tools.DataSet;
import com.platform.tools.ToolString;
import com.platform.tools.code.handler.BaseHandler;
import com.platform.tools.code.handler.ColumnDto;

@Service(name = GcTableConfService.serviceName)
public class GcTableConfService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(GcTableConfService.class);

	public static final String serviceName = "gcTableConfService";

	public Record findByTablename(String tablename) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tablename", tablename);
		List<Record> list = find("platform.gcTableConf.queryByTablename", params);
		Record r = null;
		if (list != null && list.size() > 0) {
			r = list.get(0);
		}
		return r;
	}

	public List<Record> findColumnsByTablename(String tablename) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tablename", tablename);
		List<Record> list = find("platform.gcTableConf.showColumns", params);
		return list;
	}

	public List<Record> findGcColumnsByTablename(String tablename) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tablename", tablename);
		List<Record> list = find("platform.gcTableConf.queryGcColumnsByTablename", params);
		return list;
	}

	public String generatePage(Record cInfo, List<Record> pcs) {
		String smallClass = cInfo.getStr("classname");
		smallClass = ToolString.toLowerCaseFirstOne(smallClass);
		String srcFolder = cInfo.get("srcFolder");
		String packages = "generatePage"; 
		String pagePath = System.getProperty("user.dir") + "/" + srcFolder + "/" + packages.replace(".", "/") + "/"+ smallClass;
		Map<String, Object> pagePara = getPagePara(cInfo, pcs);
		
		String basePath = cInfo.get("packagename");
		basePath = basePath.split("\\.")[1];
		pagePara.put("basePath", basePath);
		String[] pages = new String[] { "list", "form", "view", "add", "update" };
		for (String page : pages) {
			String listPage = pagePath + "/" + page + ".html";
			BaseHandler.createFileByTemplete("html/" + page + "_.html", pagePara, listPage);
		}
		return pagePath;
	}
	
	private Map<String, Object> getPagePara(Record cInfo, List<Record> pcs) {
		Map<String, Object> toPagePara = new HashMap<String, Object>();
		toPagePara.put("demitStart", "<%");
		toPagePara.put("demitEnd", "%>");
		toPagePara.put("demitpStart", "${");
		toPagePara.put("demitpEnd", "}");
		toPagePara.put("classNameSmall", ToolString.toLowerCaseFirstOne(cInfo.getStr("classname")));
		toPagePara.put("cviewname", cInfo.getStr("viewname"));
		toPagePara.put("columnList", getTableColumnDtos(pcs));
		return toPagePara;
	}

	private List<ColumnDto> getTableColumnDtos(List<Record> pcs) {
		List<ColumnDto> columnList = new ArrayList<ColumnDto>();
		for (Record r : pcs) {
			columnList.add(setToTableColumn(r));
		}
		return columnList;
	}

	private ColumnDto setToTableColumn(Record r) {
		ColumnDto td = new ColumnDto();
		td.setFormtype(r.getStr("formtype"));
		td.setViewname(r.getStr("viewname"));
		td.setColumnname(r.getStr("columnname"));
		td.setQuery(r.getInt("hasQuery"));
		td.setList(r.getInt("hasListView"));
		td.setIds(r.getStr("ids"));

		if ("select".equals(td.getViewname()) && "static".equals(td.getViewname())) {
			String dataStr = r.getStr("viewdata");
			String[] datas = dataStr.split(":");
			String type = datas[0];
			String statm = datas[1];

			String dataOptions = datas[2];
			String[] options = dataOptions.split(",");
			String dataValue = options[0];
			String dataView = options[1];

			if ("sql".equals(type)) {
				List<Record> records = Db.find(statm);
				for (Record option : records) {
					String oValue = option.getStr(dataValue);
					String oView = option.getStr(dataView);
					td.getDatas().add(new DataSet(oView, oValue));
				}
			}
		}
		return td;
	}
	
	public static void main(String []args){
		String srcFolder = "WebContent/WEB-INF/view"; 
		String packages = "test";
		String filePath = System.getProperty("user.dir") + "/" + srcFolder + "/" + packages.replace(".", "/") + "/"+ "cusinfo";
		System.out.println(filePath);
	}
}
