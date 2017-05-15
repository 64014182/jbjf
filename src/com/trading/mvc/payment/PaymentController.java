package com.trading.mvc.payment;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import com.platform.annotation.Controller;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolDateTime;
import com.trading.mvc.JSonList;

/**
 * XXX 管理	
 * 描述：
 * 
 * /trading/payment
 * /trading/payment/save
 * /trading/payment/edit
 * /trading/payment/update
 * /trading/payment/view
 * /trading/payment/delete
 * /common/payment/add.html
 * 
 */
@Controller("/trading/payment")
public class PaymentController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(PaymentController.class);
	
	private PaymentService paymentService;
	
	/**
	 * 列表
	 */
	public void indexPage() {
		render("/trading/payment/list.html");
	}
	
	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInit.db_dataSource_main, splitPage, Payment.sqlId_splitPageSelect, Payment.sqlId_splitPageFrom);
		render("/trading/payment/list.html");
	}
	
	public void getJosnDatas() {
		//http://localhost:8080/JFinalUIBV4/trading/payment/getJosnDatas?order=asc&offset=0&limit=10
		//http://localhost:8080/JFinalUIBV4/trading/payment?order=asc&offset=0&limit=10
		
		Map<String, String> paramMap = getParamMap();
		String offset = paramMap.get("offset");
		String limit = paramMap.get("limit");
		System.out.println(paramMap.get("filter"));
		
		int pageNumber = Integer.parseInt(offset)/ 10 + 1;
		Page<Payment> list = Payment.dao.paginate(pageNumber, Integer.parseInt(limit), getSqlMy(BaseModel.sqlId_splitPageSelect), getSqlMy(Payment.sqlId_splitPageFrom));
		JSonList js = new JSonList();
		js.setRows(list.getList());
		js.setTotal(list.getTotalRow());
		
		String json = JSON.toJSONString(js);
		
		//String jsonstr = array.toString();
		System.out.println(json);
		renderJson(json);
	}
	/**
	 * 保存
	 */
	@Before(PaymentValidator.class)
	public void save() {
		Payment p = getModel(Payment.class);
		p.setSTime(ToolDateTime.getSqlTimestamp());
		p.save();
		forwardAction("/trading/payment/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Payment payment = Payment.dao.findById(getPara());
		setAttr("payment", payment);
		render("/trading/payment/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(PaymentValidator.class)
	public void update() {
		getModel(Payment.class).update();
		forwardAction("/trading/payment/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Payment payment = Payment.dao.findById(getPara());
		setAttr("payment", payment);
		render("/trading/payment/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		paymentService.baseDelete(Payment.table_name, getPara() == null ? ids : getPara());
		forwardAction("/trading/payment/backOff");
	}
	
}
