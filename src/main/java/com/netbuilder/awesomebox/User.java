package com.netbuilder.awesomebox;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="Username", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String username;
	
	@Column(name="Password", nullable = false, length = 20)
	@NotNull
	@Size(min = 1, max = 20)
	private String password;
	
	@Column(name="Credits", nullable = false, columnDefinition="int default 0")
	@NotNull
	@Range(min=0, max=100)
	private int credits;
	
	@Column(name="isAdmin", nullable = false, columnDefinition="boolean default 0")
	@NotNull
	private boolean isAdmin;

	// Constructors
	public User() {

	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, int credits, boolean isAdmin ) {
		this.username = username;
		this.password = password;
		this.credits = credits;
		this.isAdmin = isAdmin;
	}
	
	//public User(String username, String password, int credits, )

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	@Override
	public String toString(){
		return new StringBuilder()
		.append("User { Username=\"" + username)
		.append("\", id=\"" + id)
		.append("\", password=\"" + password)
		.append("\", credits=\"" + credits)
		.append("\", isAdmin=\"" + isAdmin + "\" }")
		.toString();
		
	}
}
