package com.skilldistillery.eventtracker.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.eventtracker.entities.Workout;

public interface WorkoutService {

	Workout findById( int id);

	boolean delete(int workoutId);

	List<Workout> getAllWorkouts();
	

	Workout create(Workout workout);

	Workout update(int id, Workout workout);
	
	Integer getWorkoutTotal();
}
