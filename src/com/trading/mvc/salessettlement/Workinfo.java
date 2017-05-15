package com.trading.mvc.salessettlement;

import java.util.Date;

public class Workinfo {
	private Date wnumber;
	private String witem;
	private String wmeting;
	private String wbweek;
	private String wtweek;
	private String wproblem;
	private String wremark;

	public Date getWnumber() {
		return wnumber;
	}

	public void setWnumber(Date wnumber) {
		this.wnumber = wnumber;
	}

	public String getWitem() {
		return witem;
	}

	public void setWitem(String witem) {
		this.witem = witem;
	}

	public String getWmeting() {
		return wmeting;
	}

	public void setWmeting(String wmeting) {
		this.wmeting = wmeting;
	}

	public String getWbweek() {
		return wbweek;
	}

	public void setWbweek(String wbweek) {
		this.wbweek = wbweek;
	}

	public String getWtweek() {
		return wtweek;
	}

	public void setWtweek(String wtweek) {
		this.wtweek = wtweek;
	}

	public String getWproblem() {
		return wproblem;
	}

	public void setWproblem(String wproblem) {
		this.wproblem = wproblem;
	}

	public String getWremark() {
		return wremark;
	}

	public void setWremark(String wremark) {
		this.wremark = wremark;
	}

}
