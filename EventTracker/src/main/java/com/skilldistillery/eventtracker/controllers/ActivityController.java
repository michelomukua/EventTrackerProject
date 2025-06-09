package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Workout;
import com.skilldistillery.eventtracker.entities.Activity;
import com.skilldistillery.eventtracker.services.ActivityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@GetMapping({"activities","activities/"})
	public List<Activity> index(){
		return activityService.getAllActivity();
	}
	
	@GetMapping("activities/{activityId}")
	public Activity findById(@PathVariable("activityId") int activityId, HttpServletResponse resp) {

		Activity activity = activityService.findById(activityId);
		if (activity == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return activity;

	}
	@PostMapping({"activities","activities/"})
	public Activity addAvtivity(@RequestBody Activity activity,
			HttpServletRequest req,HttpServletResponse res) 
	{

		try {
			activity = activityService.create(activity);
			if (activity == null)
			{
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append("/").append(activity.getId()).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			activity = null;
		}
		return activity;
	}
	
	@PutMapping("activities/{activityId}")
	public Activity updateActivity(@PathVariable("activityId")int activityId, @RequestBody Activity activity,
			HttpServletRequest req,HttpServletResponse res) {
		Activity activityToUpdate = activityService.findById(activityId);
		if(activityToUpdate == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return activityService.update(activity);
		
	}

	@DeleteMapping("activities/{activityId}")
	public boolean deleteActivity(@PathVariable("activityId") int activityId, HttpServletRequest req,
			HttpServletResponse res) {
		Activity activityToDelete = activityService.findById(activityId);
		if (activityToDelete == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return activityService.delete(activityId);

	}

}
