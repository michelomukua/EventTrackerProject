package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

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

	@Override
	public boolean delete(int workoutId) {

		if (workoutRepo.findById(workoutId) != null) {
			workoutRepo.deleteById(workoutId);
			return true;
		}
		return false;
	}

	@Override
	public List<Workout> getAllWorkouts() {
		return workoutRepo.findAll();
	}

	@Override
	public Workout create(Workout workout) {
		return workoutRepo.saveAndFlush(workout);
	}

	@Override
	public Workout update(int id, Workout workout) {
		Optional<Workout> workoutOpt = workoutRepo.findById(id);
		
		Workout managedWorkout = null;
		
		if(workoutOpt.isPresent()) {
			managedWorkout = workoutOpt.get();
			
			managedWorkout.getMember().setId(workout.getMember().getId());
			managedWorkout.getActivity().setId(workout.getActivity().getId());
			managedWorkout.setDuration(workout.getDuration());
			managedWorkout.setWorkoutDate(workout.getWorkoutDate());
			managedWorkout.setComment(workout.getComment());

			workoutRepo.saveAndFlush(managedWorkout);
		}
		
		return managedWorkout;
	}

	@Override
	public Integer getWorkoutTotal() {
		return workoutRepo.getWorkoutTotal();
	}

}
