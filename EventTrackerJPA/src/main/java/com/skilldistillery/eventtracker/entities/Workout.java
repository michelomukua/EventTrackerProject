package com.skilldistillery.eventtracker.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Workout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="workout_id")
	private int workoutId;
	
	private String name;
	
	private String description;
	
	private int duration;

	public Workout() {
		super();
	}

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		return Objects.hash(workoutId);
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
		return workoutId == other.workoutId;
	}

	@Override
	public String toString() {
		return "Workout [workoutId=" + workoutId + ", name=" + name + ", description=" + description + ", duration="
				+ duration + "]";
	}

	public Workout(int workoutId, String name, String description, int duration) {
		super();
		this.workoutId = workoutId;
		this.name = name;
		this.description = description;
		this.duration = duration;
	}

	
}
