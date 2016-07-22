package com.webservice.jsonEntity;

import java.util.Set;

public class UserEntry {
	private int id;
	private String username;
	private Set<EventBrief> events;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<EventBrief> getEvents() {
		return events;
	}
	public void setEvents(Set<EventBrief> events) {
		this.events = events;
	}
	public UserEntry() {
		super();
		// TODO Auto-generated constructor stub
	}
}
