package com.skilldistillery.eventtracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_started")
	private LocalDateTime dateStarted;
	
	@Column(name="date_ended")
	private LocalDateTime dateEnded;
	
	private boolean active;
	
	public Member() {
		super();
	}

	public Member(int id, String firstName, String lastName, LocalDateTime dateStarted, LocalDateTime dateEnded,
			boolean active) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateStarted = dateStarted;
		this.dateEnded = dateEnded;
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public LocalDateTime getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(LocalDateTime dateStarted) {
		this.dateStarted = dateStarted;
	}

	public LocalDateTime getDateEnded() {
		return dateEnded;
	}

	public void setDateEnded(LocalDateTime dateEnded) {
		this.dateEnded = dateEnded;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Id == other.Id;
	}

	@Override
	public String toString() {
		return "Member [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateStarted="
				+ dateStarted + ", dateEnded=" + dateEnded + ", active=" + active + "]";
	}
	
}
