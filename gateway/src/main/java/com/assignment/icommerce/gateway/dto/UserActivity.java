package com.assignment.icommerce.gateway.dto;

import java.util.Date;

public class UserActivity {

	private String url;
	private String method;
	private Date date;

	public UserActivity() {
		this.date = new Date();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getDate() {
		return date;
	}

}
