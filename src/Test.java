import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Test {
	private static Configuration cfg;

	private static final String TEMPLATEFILENAME = "F://workspace/java//JfinalUIBv4//src//templates//";

	private static Template dateTmp;

	private static Map<String, Object> root = new HashMap<>();;

	static {
		try {
			// 初始化参数
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(TEMPLATEFILENAME));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			dateTmp = cfg.getTemplate("test.ftl");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[]args) throws Exception {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("张三", 20, "女"));
		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));
//		empList.add(new Employee("张三", 20, "女"));

		root.put("empList", empList);

		File file = new File("f://test.xlsx");
		FileWriter fw = new FileWriter(file);
		dateTmp.process(root, fw);
	}
}
