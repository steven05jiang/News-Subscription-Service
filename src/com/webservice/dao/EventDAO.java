package com.webservice.dao;

import com.webservice.entity.Event;

public interface EventDAO {
	public Event createEvent(Event event);
	public Event getEventById(int id);
	public void updateEvent(Event event);
	public void deleteEvent(Event event);
}
