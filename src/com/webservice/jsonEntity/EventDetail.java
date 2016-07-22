package com.webservice.jsonEntity;

import java.util.Set;

public class EventDetail {
	private EventEntry event;
	private Set<NewsEntry> news;
	public EventEntry getEvent() {
		return event;
	}
	public void setEvent(EventEntry event) {
		this.event = event;
	}
	public Set<NewsEntry> getNews() {
		return news;
	}
	public void setNews(Set<NewsEntry> news) {
		this.news = news;
	}
	public EventDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
}
