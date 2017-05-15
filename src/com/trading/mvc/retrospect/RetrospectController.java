package com.trading.mvc.retrospect;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolDateTime;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/retrospect
 * /trading/retrospect/save
 * /trading/retrospect/edit
 * /trading/retrospect/update
 * /trading/retrospect/view
 * /trading/retrospect/delete
 * /common/retrospect/add.html
 * 
 */
@Controller("/trading/retrospect")
public class RetrospectController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(RetrospectController.class);
	
	private RetrospectService retrospectService;
	
	public static void main(String []args){
	}
	/**
	 * 列表
	 */
	public void index() {
		setCurDateToQueryParam("mon", ToolDateTime.pattern_yymm);
		paging(ConstantInit.db_dataSource_main, splitPage, BaseModel.sqlId_splitPageSelect, Retrospect.sqlId_splitPageFrom);
		render("/trading/retrospect/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(RetrospectValidator.class)
	public void save() {
		retrospectService.saveRetr(getModel(Retrospect.class));
		forwardAction("/trading/retrospect/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Retrospect retrospect = Retrospect.dao.findById(getPara());
		setAttr("retrospect", retrospect);
		render("/trading/retrospect/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(RetrospectValidator.class)
	public void update() {
		//getModel(Retrospect.class).update();
		retrospectService.saveRetr(getModel(Retrospect.class));
		forwardAction("/trading/retrospect/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Retrospect retrospect = Retrospect.dao.findById(getPara());
		setAttr("retrospect", retrospect);
		render("/trading/retrospect/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		retrospectService.baseDelete(Retrospect.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/retrospect/backOff");
	}
	
}
