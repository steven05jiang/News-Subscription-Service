package com.webservice.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "News")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name = "createDate")
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "expireDate")
	private Date expireDate;
	@Column(name = "event")
	private int event;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public int getEvent() {
		return event;
	}
	public void setEvent(int event) {
		this.event = event;
	}
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
	public Date getExpiredDate() {
		return expireDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expireDate = expiredDate;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
