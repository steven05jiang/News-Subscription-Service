package com.webservice.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Events")
public class Event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "eventName")
	private String eventName;
	@Column(name = "description")
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name = "createDate")
	private Date createDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "expireDate")
	private Date expireDate;
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User publisher;
	@ManyToMany(mappedBy = "subEvents")
	private Set<User> subscribers;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "event", referencedColumnName = "id")
	private Set<News> news;
	
	
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


	public User getPublisher() {
		return publisher;
	}


	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}


	public Set<User> getSubscribers() {
		return subscribers;
	}


	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}


	public Set<News> getNews() {
		return news;
	}


	public void setNews(Set<News> news) {
		this.news = news;
	}


	public Event() {
		super();
		// TODO Auto-generated constructor stub
		Date date = new Date();
		this.createDate = date;
	}
	
}
