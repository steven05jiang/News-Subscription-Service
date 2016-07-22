package com.webservice.jsonEntity;

import java.util.Date;

import com.webservice.entity.News;

public class NewsEntry {
	private String title;
	private String description;
	private Date createDate;
	private Date expireDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public NewsEntry(News news) {
		super();
		this.title = news.getTitle();
		this.description = news.getDescription();
		this.createDate = news.getCreateDate();
		this.expireDate = news.getExpireDate();
	}
	
	
}
