package com.trading.mvc.poci;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.tools.ToolDateTime;
import com.trading.mvc.planordercomplete.PlanOrderComplete;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/poci/modalPlan
 * /trading/poci/save
 * /trading/poci/edit
 * /trading/poci/update
 * /trading/poci/view
 * /trading/poci/delete
 * /common/poci/add.html
 * 
 */
@Controller("/trading/poci")
public class PociController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PociController.class);
	
	private PociService pociService;
	
	/**
	 * 列表
	 */
	public void index() {
		setCurDateToQueryParam("cDate", ToolDateTime.pattern_yymm);
		paging(ConstantInit.db_dataSource_main, splitPage, Poci.sqlId_splitPageSelect, Poci.sqlId_splitPageFrom);
		render("/trading/poci/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(PociValidator.class)
	public void save() {
		getModel(Poci.class).save();
		forwardAction("/trading/poci/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Poci poci = Poci.dao.findById(getPara());
		setAttr("poci", poci);
		render("/trading/poci/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(PociValidator.class)
	public void update() {
		getModel(Poci.class).update();
		forwardAction("/trading/poci/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Poci poci = Poci.dao.findById(getPara());
		setAttr("poci", poci);
		render("/trading/poci/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		pociService.baseDelete(Poci.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/poci/backOff");
	}
	
	/**
	 * 计划完成弹出框
	 */
	public void modalPlan() {
		String cNo = getPara("cNo");
		List<PlanOrderComplete> list = PlanOrderComplete.dao.findByColumnValue("cNo", cNo);
		setAttr("entitys", list);
		render("/trading/poci/modalPlan.html");
	}
}
