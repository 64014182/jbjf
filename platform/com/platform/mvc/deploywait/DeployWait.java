package com.platform.mvc.deploywait;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import com.jfinal.log.Log;

/**
 *  model
 * @author 董华健  dongcb678@163.com
 */
@SuppressWarnings("unused")
@Table(tableName = DeployWait.table_name)
public class DeployWait extends BaseModel<DeployWait> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static final Log log = Log.getLog(DeployWait.class);
	
	public static final DeployWait dao = new DeployWait().dao();

	/**
	 * 表名称
	 */
	public static final String table_name = "pt_deploywait";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar(32)  长度：32
	 */
	public static final String column_ids = "ids";
	
	/**
	 * 字段描述：文件位置 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_filepath = "filepath";
	
	/**
	 * 字段描述：提交时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_savedate = "savedate";
	
	/**
	 * 字段描述：文件名 
	 * 字段类型：varchar(256)  长度：256
	 */
	public static final String column_fileName = "fileName";
	
	/**
	 * 字段描述：包名类名 
	 * 字段类型：varchar(1024)  长度：1024
	 */
	public static final String column_pkf = "pkf";
	
	
	/**
	 * sqlId : platform.deployWait.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPageFrom = "platform.deployWait.splitPageFrom";

	private String ids;
	private String filepath;
	private Timestamp savedate;
	private String fileName;
	private String pkf;
	
	public void setIds(String ids){
		set(column_ids, ids);
	}
	public String getIds() {
		return get(column_ids);
	}
	public void setFilepath(String filepath){
		set(column_filepath, filepath);
	}
	public String getFilepath() {
		return get(column_filepath);
	}
	public void setSavedate(Timestamp savedate){
		set(column_savedate, savedate);
	}
	public Timestamp getSavedate() {
		return get(column_savedate);
	}
	public void setFileName(String fileName){
		set(column_fileName, fileName);
	}
	public String getFileName() {
		return get(column_fileName);
	}
	public void setPkf(String pkf){
		set(column_pkf, pkf);
	}
	public String getPkf() {
		return get(column_pkf);
	}
	
}
