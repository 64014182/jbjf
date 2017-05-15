package freemark;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 博客单元测试类
 * 
 * @author 董华健 dongcb678@163.com
 */
public class TestBlog {
	private Configuration cfg; // 模版配置对象

	public void init() throws Exception {
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件夹位置
		cfg.setDirectoryForTemplateLoading(new File("F:\\workspace\\java\\JfinalUIBv4\\junit\\freemark\\"));
	}

	public void process() throws Exception {
		Template t = new Template(null, new StringReader("用户名：${user};URL：    ${url};姓名： 　${name}"), null);
		// 创建插值的Map
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", "lavasoft");
		map.put("url", "http://www.baidu.com/");
		map.put("name", "百度");
		// 执行插值，并输出到指定的输出流中
		t.process(map, new OutputStreamWriter(System.out));
	}

	@Test
	public void test() throws Exception {
		TestBlog hf = new TestBlog(); 
         hf.init(); 
         hf.process();
	}

}
