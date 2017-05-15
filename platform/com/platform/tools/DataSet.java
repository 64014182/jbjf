package com.platform.tools;

public class DataSet {
	private String view;
	private String value;

	public DataSet(String view, String value) {
		super();
		this.view = view;
		this.value = value;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
