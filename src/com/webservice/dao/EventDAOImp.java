package com.webservice.dao;

import org.hibernate.Session;

import com.webservice.entity.Event;
import com.webservice.exceptions.InvalidIndexException;

public class EventDAOImp implements EventDAO {

	private Session session;
	
	public EventDAOImp(Session session){
		this.session = session;
	}
	
	@Override
	public Event createEvent(Event event) {
		// TODO Auto-generated method stub
		if(event == null){
			throw new NullPointerException();
		}
		session.beginTransaction();
		session.save(event);
		session.getTransaction().commit();
		session.refresh(event);
		return event;
	}

	@Override
	public Event getEventById(int id) {
		// TODO Auto-generated method stub
		Event event = (Event)session.get(Event.class, id);
		if(event == null){
			throw new InvalidIndexException();
		}
		session.refresh(event);
		return event;
	}

	@Override
	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		if(event == null){
			throw new NullPointerException();
		}
		session.beginTransaction();
		session.update(event);
		session.getTransaction().commit();
	}

	@Override
	public void deleteEvent(Event event) {
		// TODO Auto-generated method stub
		if(event == null){
			throw new NullPointerException();
		}
		session.beginTransaction();
		session.delete(event);
		session.getTransaction().commit();
	}

}
