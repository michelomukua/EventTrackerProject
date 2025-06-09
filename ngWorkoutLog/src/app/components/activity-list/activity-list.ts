import { Component, OnInit } from '@angular/core';
import { Activity } from '../../models/activity';
import { ActivityService } from '../../services/activity-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-activity-list',
  imports: [FormsModule],
  templateUrl: './activity-list.html',
  styleUrl: './activity-list.css',
})
export class ActivityList implements OnInit {
  activities: Activity[] = [];

  newActivity: Activity = new Activity();
  editActivity: Activity | null = null;
  selected: Activity | null = null;
  constructor(private activityService: ActivityService) {}

  ngOnInit(): void {
    this.loadActivities();
  }

  displayEdit(activity: Activity): void {
    this.selected = activity;
    this.editActivity = Object.assign({}, activity);
  }
  loadActivities(): void {
    this.activityService.index().subscribe({
      next: (activityList) => {
        this.activities = activityList;
      },
      error: (badnews) => {
        console.error('Home.loadActivities: error getting activities');
        console.error(badnews);
      },
    });
  }

  addActivity(newActivity: Activity) {
    this.activityService.create(newActivity).subscribe({
      next: (createdActivity) => {
        this.loadActivities();
      },
    });
  }
  updateActivity(updatedActivity: Activity): void {
    this.activityService.update(updatedActivity, updatedActivity.id).subscribe({
      next: (success) => {
        //What to do when request is successful
        this.loadActivities();
        this.editActivity = null;
        this.selected = null;
      },
      error: (err: any) => {
        console.log(err);
        console.log('Activity-List.ts Component: Error deleting activities');
      },
    });
  }
  deleteActivity(activityId: number): void {
    this.activityService.delete(activityId).subscribe({
      next: (success) => {
        //What to do when request is successful
        this.loadActivities();
      },
      error: (err: any) => {
        console.log(err);
        console.log('Activity-List.ts Component: Error deleting activities');
      },
    });
  }
}
