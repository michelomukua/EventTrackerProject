package com.skilldistillery.eventtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.eventtracker.entities.Workout;

public interface WorkoutRepository extends JpaRepository <Workout, Integer>{

	@Query(value="SELECT sum(duration) FROM workout", nativeQuery = true)
	Integer getWorkoutTotal();
	

}
