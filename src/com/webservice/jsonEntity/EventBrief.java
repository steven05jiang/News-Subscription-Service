package com.webservice.jsonEntity;

import com.webservice.entity.Event;

public class EventBrief {
	
	private int id;
	private String eventName;
	private String description;

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
	
	public EventBrief(Event event) {
		super();
		this.id = event.getId();
		this.eventName = event.getEventName();
		this.description = event.getDescription();
	}
	
	

}
