package com.webservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
//@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Event> pubEvents;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Subscriptions", 
	joinColumns = @JoinColumn(name = "uid", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "eid", referencedColumnName = "id"))
	private Set<Event> subEvents;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Event> getPubEvents() {
		return pubEvents;
	}

	public void setPubEvents(Set<Event> pubEvents) {
		this.pubEvents = pubEvents;
	}

	public Set<Event> getSubEvents() {
		return subEvents;
	}

	public void setSubEvents(Set<Event> subEvents) {
		this.subEvents = subEvents;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
