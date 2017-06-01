package com.platform.mvc.deploywait;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.dto.ZtreeNode;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolDateTime;

/**
 * XXX 管理	
 * 描述：
 * 
 * /platform/deployWait
 * /platform/deployWait/save
 * /platform/deployWait/edit
 * /platform/deployWait/update
 * /platform/deployWait/view
 * /platform/deployWait/delete
 * /common/deployWait/add.html
 * 
 */
@Controller("/platform/deployWait")
public class DeployWaitController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeployWaitController.class);
	
	private DeployWaitService deployWaitService;
	
	public void saveToWait() {
		try {
			Map<String, String[]> map = getParaMap();
			String[] files = map.get("saveFiles[]");
			Timestamp ts = ToolDateTime.getSqlTimestamp();
			for (String file : files) {
				DeployWait dw = getModel(DeployWait.class);
				dw.setFilepath(file);
				dw.setFileName(getFileName(file));
				dw.setPkf(file.substring(file.lastIndexOf("WEB-INF"),file.length()));
				dw.setSavedate(ts);
				dw.save();
			}
		} catch (Exception e) {
			renderError(null,null,e.getMessage());
			return;
		}
		renderSuccess(null, null, null);
	}
	
	private String getFileName(String path){
		if(StringUtils.isEmpty(path))
			return path;
		int subBeginIndex = path.lastIndexOf("/") + 1 ;
		int subendIndex = path.length();
		
		return path.substring(subBeginIndex,subendIndex);
	}
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, DeployWait.sqlId_splitPageFrom);
		render("/platform/deployWait/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(DeployWaitValidator.class)
	public void save() {
		getModel(DeployWait.class).save();
		forwardAction("/platform/deployWait/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		DeployWait deployWait = DeployWait.dao.findById(getPara());
		setAttr("deployWait", deployWait);
		render("/platform/deployWait/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(DeployWaitValidator.class)
	public void update() {
		getModel(DeployWait.class).update();
		forwardAction("/platform/deployWait/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		DeployWait deployWait = DeployWait.dao.findById(getPara());
		setAttr("deployWait", deployWait);
		render("/platform/deployWait/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		deployWaitService.baseDelete(DeployWait.table_name, getPara() == null ? ids : getPara());
		forwardAction("/platform/deployWait/backOff");
	}
	
	public void treeData() {
		if (StringUtils.isEmpty(ids)) {
			String filepath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			ids = filepath.replace("/classes/", "");
		}
		File file = new File(ids);
		File[] files = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});

		List<ZtreeNode> nodeList = new ArrayList<ZtreeNode>();
		ZtreeNode node = null;

		for (File f : files) {
			node = new ZtreeNode();
			node.setId(ids + "/" +f.getName());
			node.setName(f.getName());
			if(f.isFile()){
				node.setIsParent(false);
			}else{
				node.setIsParent(true);
			}
			nodeList.add(node);
		}
		renderJson(nodeList);
	}
	
	public void publish() throws Exception{
		deployWaitService.publish(ids,getCxt());
	}
	
	@SuppressWarnings("deprecation")
	public void getUploadFile() throws IOException {
		String floderPath = getRequest().getRealPath("");
		List<UploadFile> files = getFiles();
		deployWaitService.replace(floderPath,files);
	}
}
