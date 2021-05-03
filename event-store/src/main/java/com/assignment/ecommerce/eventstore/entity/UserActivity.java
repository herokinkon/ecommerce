package com.assignment.ecommerce.eventstore.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user-activity")
public class UserActivity {

	@Id
	private String id;
	@Field(type = FieldType.Text)
	private String url;
	@Field(type = FieldType.Text)
	private String method;
	@Field(type = FieldType.Date, format = DateFormat.basic_date_time)
	private Date date;

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

	public void setDate(Date date) {
		this.date = date;
	}

}
