package com.trading.mvc.manufacturer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.dto.ZtreeNode;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/manufacturer
 * /trading/manufacturer/save
 * /trading/manufacturer/edit
 * /trading/manufacturer/update
 * /trading/manufacturer/view
 * /trading/manufacturer/delete
 * /common/manufacturer/add.html
 * 
 */
@Controller("/trading/manufacturer")
public class ManufacturerController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(ManufacturerController.class);
	
	private ManufacturerService manufacturerService;
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Manufacturer.sqlId_splitPageFrom);
		render("/trading/manufacturer/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(ManufacturerValidator.class)
	public void save() {
		getModel(Manufacturer.class).save();
		forwardAction("/trading/manufacturer/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Manufacturer manufacturer = Manufacturer.dao.findById(getPara());
		setAttr("manufacturer", manufacturer);
		render("/trading/manufacturer/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(ManufacturerValidator.class)
	public void update() {
		getModel(Manufacturer.class).update();
		forwardAction("/trading/manufacturer/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Manufacturer manufacturer = Manufacturer.dao.findById(getPara());
		setAttr("manufacturer", manufacturer);
		render("/trading/manufacturer/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		manufacturerService.baseDelete(Manufacturer.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/manufacturer/backOff");
	}
	
	public void test(){
		System.out.println(getRequest().getContextPath());
		render("/trading/manufacturer/test.html");
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
}
