package com.skilldistillery.eventtracker.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private String name;
	
	private String description;
	
	public Activity() {
		super();
		
	}

	@Override
	public String toString() {
		return "Activity [Id=" + Id + ", name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return Id == other.Id;
	}

	public Activity(int id, String name, String description) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

	