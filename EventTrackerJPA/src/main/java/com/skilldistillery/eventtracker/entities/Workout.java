package com.skilldistillery.eventtracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Workout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer duration;
	
	@Column(name="workout_date")
	private LocalDateTime workoutDate;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	public Workout() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}



	public LocalDateTime getWorkoutDate() {
		return workoutDate;
	}

	public void setWorkoutDate(LocalDateTime workoutDate) {
		this.workoutDate = workoutDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workout other = (Workout) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Workout [id=" + id + ", duration=" + duration + ", workoutDate=" + workoutDate + ", comment=" + comment
				+ ", member=" + member + ", activity=" + activity + "]";
	}
}