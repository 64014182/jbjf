package com.test.mvc.blog;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.jfinal.aop.Before;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.platform.annotation.Controller;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;
import com.platform.tools.ToolDateTime;

/**
 * XXX 管理	
 * 描述：
 * 
 * /test/blog
 * /test/blog/save
 * /test/blog/edit
 * /test/blog/update
 * /test/blog/view
 * /test/blog/delete
 * /common/blog/add.html
 * 
 */
@Controller("/test/blog")
public class BlogController extends BaseController {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(BlogController.class);
	
	private BlogService blogService;
	
	/**
	 * 列表
	 */
	public void index() {
		//paging(splitPage, BaseModel.sqlId_splitPageSelect, Blog.sqlId_splitPageFrom);
		
		///List datas = splitPage.getList();
		//renderJson(datas);
		render("/test/blog/list.html");
	}
	
	public void list() {
		paging(splitPage, BaseModel.sqlId_splitPageSelect, Blog.sqlId_splitPageFrom);
		List datas = splitPage.getList();
		renderJson(datas);
		//render("/test/blog/list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(BlogValidator.class)
	public void save() {
		Blog blog = getModel(Blog.class);
		blog.setCreatetime(ToolDateTime.getSqlTimestamp());
		blog.save();
		forwardAction("/test/blog/backOff");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		Blog blog = Blog.dao.findById(getPara());
		setAttr("blog", blog);
		render("/test/blog/update.html");
	}
	
	/**
	 * 更新
	 */
	@Before(BlogValidator.class)
	public void update() {
		getModel(Blog.class).update();
		forwardAction("/test/blog/backOff");
	}

	/**
	 * 查看
	 */
	public void view() {
		Blog blog = Blog.dao.findById(getPara());
		setAttr("blog", blog);
		render("/test/blog/view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		blogService.baseDelete("test_blog", getPara() == null ? ids : getPara());
		forwardAction("/test/blog/backOff");
	}
	
	public void getBlogs() {
		List users = Db.find("select * from test_blog");
		renderJson(users);
	}
	
	public void editBlog() {
		Blog blog = getModel(Blog.class);
		blog.setIds(getRequest().getParameter("ids"));
		blog.setTitle(getRequest().getParameter("title"));
		blog.update();
		
		JSONObject json = new JSONObject();
		json.put("success", "success");
		renderJson(json.toString());
	}
}
