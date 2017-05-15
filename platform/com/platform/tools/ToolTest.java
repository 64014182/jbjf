package com.platform.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class ToolTest {
	public static void parse(String templateDir, String templateFileName, String generalFilePath,
			Map<String, Object> data) throws Exception {
		System.out.println("aaa");
		// 初始化工作
		Configuration cfg = new Configuration();
		// 设置默认编码格式为UTF-8
		cfg.setDefaultEncoding("UTF-8");
		// 全局数字格式
		cfg.setNumberFormat("0.00");
		// 设置模板文件位置
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setTemplateLoader(new StringTemplateLoader());
		// 加载模板
		Template template = cfg.getTemplate(templateFileName, "utf-8");
		OutputStreamWriter writer = null;
		try {
			// 填充数据至Excel
			writer = new OutputStreamWriter(new FileOutputStream(generalFilePath), "UTF-8");
			template.process(data, writer);
			writer.flush();
		} finally {
			writer.close();
		}
	}
}
