package com.skilldistillery.eventtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Workout;
import com.skilldistillery.eventtracker.services.WorkoutService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class WorkoutController {
	
	
	@Autowired
	private WorkoutService workoutService;
	
	@GetMapping("workouts/{workoutId}")
	public Workout findById(@PathVariable("workoutId")int workoutId, HttpServletResponse resp) {
		
		Workout workout = workoutService.findById(workoutId);
		if(workout == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return workout;
		
	}

}
