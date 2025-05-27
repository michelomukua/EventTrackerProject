package com.skilldistillery.eventtracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_id")
	private int eventId;
	
	@Column(name="member_id")
	private int memberId;

	@Column(name="workout_id")
	private int workoutId;
	@Column(name="event_date")
	private LocalDateTime eventDate;
	private String comment;
	public Event() {
		super();
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}
	public LocalDateTime getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public int hashCode() {
		return Objects.hash(eventId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return eventId == other.eventId;
	}
	public Event(int eventId, int memberId, int workoutId, LocalDateTime eventDate, String comment) {
		super();
		this.eventId = eventId;
		this.memberId = memberId;
		this.workoutId = workoutId;
		this.eventDate = eventDate;
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", memberId=" + memberId + ", workoutId=" + workoutId + ", eventDate="
				+ eventDate + ", comment=" + comment + "]";
	}
	
}