package com.skilldistillery.eventtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Activity;
import com.skilldistillery.eventtracker.repositories.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public Activity findById(int activityId) {
		return activityRepo.findById(activityId).orElse(null);
	}

	@Override
	public List<Activity> getAllActivity() {
		return activityRepo.findAll();
	}

	@Override
	public Activity create(Activity activity) {
		return activityRepo.saveAndFlush(activity);
	}

	@Override
	public Activity update(Activity activity) {
		return activityRepo.save(activity);
	}

	@Override
	public boolean delete(int activityId) {
		if(activityRepo.findById(activityId) != null) { 
			activityRepo.deleteById(activityId);
			return true;
		}
		return false;
	}

}
