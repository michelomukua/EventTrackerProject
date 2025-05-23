package com.skilldistillery.eventtracker.services;

import com.skilldistillery.eventtracker.entities.Workout;

public interface WorkoutService {

	Workout findById( int id);
}
