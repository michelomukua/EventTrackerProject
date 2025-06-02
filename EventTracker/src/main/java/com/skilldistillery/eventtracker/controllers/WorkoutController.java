package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Workout;
import com.skilldistillery.eventtracker.services.WorkoutService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class WorkoutController {

	@Autowired
	private WorkoutService workoutService;

	@GetMapping({"workouts","workouts/"})
	public List<Workout> index(){
		return workoutService.getAllWorkouts();
	}
	@GetMapping("workouts/{workoutId}")
	public Workout findById(@PathVariable("workoutId") int workoutId, HttpServletResponse resp) {

		Workout workout = workoutService.findById(workoutId);
		if (workout == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return workout;

	}
	@PostMapping({"workouts","workouts/"})
	public Workout addWorkout(@RequestBody Workout workout,
			HttpServletRequest req,HttpServletResponse res) 
	{
		Workout savedWorkout = new Workout();
		try {
			savedWorkout = workoutService.create(workout);
			if (savedWorkout == null)
			{
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append("/").append(workout.getId()).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			savedWorkout = null;
		}
		return savedWorkout;
	}
	
	@PutMapping("workouts/{workoutId}")
	public Workout updateWorkout(@PathVariable("workoutId")int workoutId, @RequestBody Workout workout,
			HttpServletRequest req,HttpServletResponse res) {
		System.out.println(workoutId);
		Workout workoutToUpdate = workoutService.findById(workoutId);
		if(workoutToUpdate == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
		System.out.println(workoutToUpdate.toString());
		workout.setId(workoutId);
		return workoutService.update(workoutId, workout);
		
	}
	
	@DeleteMapping("workouts/{workoutId}")
	public boolean deleteWorkout(@PathVariable("workoutId") int workoutId, HttpServletRequest req,
			HttpServletResponse res) {
		Workout workoutToDelete = workoutService.findById(workoutId);
		if (workoutToDelete == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return workoutService.delete(workoutId);

	}
	@GetMapping("workouts/total")
	public Integer findById( HttpServletResponse resp) {

		
		return workoutService.getWorkoutTotal();

	}
}
