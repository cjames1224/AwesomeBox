package com.netbuilder.awesomebox.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Playlist")
public class Playlist {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Name", nullable = false, length = 45)
	@NotNull
	@Size(min = 1, max = 45)
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "User_ID",referencedColumnName = "ID")
	private User owner;
	
	public Playlist() {
		
	}
	public Playlist(String name, User owner) {
		this.name = name;
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
		.append("Playlist { name=\"" + name)
		.append("\", id=\"" + id)
		.append("\", user=\"" + owner + "\" }")
		.toString();
	}
}
