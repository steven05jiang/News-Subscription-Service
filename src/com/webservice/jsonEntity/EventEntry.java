package com.webservice.jsonEntity;

import java.util.Date;

import com.webservice.entity.Event;

public class EventEntry {
	
	private int id;
	private String eventName;
	private String description;
	private Date createDate;
	private Date expireDate;
	private int publisherId;
	private String publisherName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public EventEntry(){
		
	}
	public EventEntry(Event event) {
		super();
		this.id = event.getId();
		this.eventName = event.getEventName();
		this.description = event.getDescription();
		this.createDate = event.getCreateDate();
		this.expireDate = event.getExpireDate();
		this.publisherId = event.getPublisher().getId();
		this.publisherName = event.getPublisher().getUsername();
	}
}
