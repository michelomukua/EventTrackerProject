package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Activity;
import com.skilldistillery.eventtracker.entities.Workout;

public interface ActivityService {
	
	Activity findById(int activityId);

	List<Activity> getAllActivity();

	Activity create(Activity activity);

	Activity update(Activity activity);

	boolean delete(int activityId);

}
