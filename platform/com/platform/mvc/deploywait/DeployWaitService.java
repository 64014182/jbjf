package com.platform.mvc.deploywait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.jfinal.log.Log;
import com.jfinal.upload.UploadFile;
import com.platform.annotation.Service;
import com.platform.mvc.base.BaseService;

@Service(name = DeployWaitService.serviceName)
public class DeployWaitService extends BaseService {

	@SuppressWarnings("unused")
	private static final Log log = Log.getLog(DeployWaitService.class);
	
	public static final String serviceName = "deployWaitService";

	public void publish(String ids,String ctx) throws Exception {
		String sqlInIds = sqlIn(ids);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sqlIn", sqlInIds);

		String sql = getSqlByBeetl("platform.deployWait.findSqlIn", param);
		List<DeployWait> dws = DeployWait.dao.find(sql);

		List<File> files = new ArrayList<>();
		for (DeployWait dw : dws) {
			files.add(new File(dw.getFilepath()));
		}

		publishToServer(dws,ctx);
	}

	private void publishToServer(List<DeployWait> DeployWaits,String ctx) throws Exception {
		String login = ctx + "/platform/login/vali";
		String uploadUrl = ctx + "/platform/deployWait/getUploadFile";
		CloseableHttpClient httpclient = HttpClients.custom().setProxy(new HttpHost("127.0.0.1", 8080)).build();
		login(httpclient,login);
		updateload(httpclient, DeployWaits,uploadUrl);
	}

	private String updateload(CloseableHttpClient httpclient, List<DeployWait> DeployWaits,String uploadUrl) throws Exception {
		HttpPost httpPost = new HttpPost(uploadUrl);
		MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(Charset.defaultCharset());
		CloseableHttpResponse response = null;
		String result = null;
		for (DeployWait dw : DeployWaits) {
			mEntityBuilder.addBinaryBody(dw.getPkf(), new File(dw.getFilepath()));
		}
		HttpEntity reqEntity = mEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("localePram", "zh_CN");
		httpPost.setHeader("Accept", "text/html, */*; q=0.01");

		String contentType = reqEntity.getContentType().toString();
		contentType = contentType.substring(contentType.indexOf(":")+2,contentType.length());
		contentType = contentType.substring(0, contentType.lastIndexOf(";"));
		httpPost.setHeader("Content-Type", contentType);
		response = httpclient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity resEntity = response.getEntity();
			result = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);
		}
		httpclient.close();
		response.close();
		return result;
	}

	private String login(CloseableHttpClient httpclient,String loginUrl) throws Exception {
		HttpPost loginPost = new HttpPost(loginUrl);
		List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
		qparams.add(new BasicNameValuePair("username", "admins"));
		qparams.add(new BasicNameValuePair("password", "123456"));
		qparams.add(new BasicNameValuePair("returnText", "null"));
		loginPost.setEntity(new UrlEncodedFormEntity(qparams, "UTF-8"));
		CloseableHttpResponse loginResponse = httpclient.execute(loginPost);
		HttpEntity entity = loginResponse.getEntity();
		System.out.println("Login form get: " + loginResponse.getStatusLine());
		String content = EntityUtils.toString(entity);
		System.out.println("Login form get: " + content);
		loginResponse.close();
		return content;
	}

	public void replace(String floderPath, List<UploadFile> files) throws IOException {
		for (UploadFile uf : files) {
			String sourceFilePath = floderPath + File.separator +uf.getParameterName();
			backSourceFile(sourceFilePath);
			replaceFile(sourceFilePath,uf);
		}
	}

	private void replaceFile(String sourceFilePath, UploadFile uf) throws IOException {
		FileUtils.copyFile(uf.getFile(), new File(sourceFilePath), true);
	}

	private void backSourceFile(String sourceFilePath) throws IOException {
		String soruceFoler = sourceFilePath.substring(0,sourceFilePath.lastIndexOf("/"));
		FileUtils.copyFileToDirectory(new File(sourceFilePath), new File(soruceFoler + File.separator+ "back"), true);
	}
}
