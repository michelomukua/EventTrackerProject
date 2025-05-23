package com.skilldistillery.eventtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Workout;
import com.skilldistillery.eventtracker.repositories.WorkoutRepository;

@Service
public class WorkoutServiceImpl implements WorkoutService {
	
	@Autowired
	private WorkoutRepository workoutRepo;
	

	@Override
	public Workout findById(int id) {
		return workoutRepo.findById(id).orElse(null);
	}

}
