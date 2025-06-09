import { Workout } from './../../models/workout';
import { Component, OnInit } from '@angular/core';
import { WorkoutService } from '../../services/workout-service';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-workout-list',
  imports: [FormsModule, FontAwesomeModule],
  templateUrl: './workout-list.html',
  styleUrl: './workout-list.css',
})
export class WorkoutList implements OnInit {
  workouts: Workout[] = [];

  newWorkout: Workout = new Workout();
  editWorkout: Workout | null = null;
  selected: Workout | null = null;
  constructor(private workoutService: WorkoutService) {}

  ngOnInit(): void {
    this.loadWorkouts();
  }
  displayTable(): void {
    this.selected = null;
    this.loadWorkouts();
  }
  getTotalWorkout() {
    let total = 0;
    for (let workout of this.workouts) {
      total = total + workout.duration;
    }
    return total;
  }
  displayEdit(workout: Workout): void {
    let wdate = workout.workoutDate.substring(0, 10);
    this.selected = workout;
    this.selected.workoutDate = wdate;
    this.editWorkout = Object.assign({}, workout);
  }
  loadWorkouts(): void {
    this.workoutService.index().subscribe({
      next: (workoutList) => {
        this.workouts = workoutList;
        console.log(this.workouts);
      },
      error: (badnews) => {
        console.error('Home.loadWorkout: error getting workouts');
        console.error(badnews);
      },
    });
  }

  addWorkout(newWorkout: Workout) {
    let wdate = new Date(newWorkout.workoutDate);
    newWorkout.workoutDate = wdate.toISOString().substring(0, 19);
    console.log(newWorkout);
    this.workoutService.create(newWorkout).subscribe({
      next: (createdWorkout) => {
        this.loadWorkouts();
      },
    });
  }
  updateWorkout(updatedWorkout: Workout): void {
    let wdate = new Date(updatedWorkout.workoutDate);
    updatedWorkout.workoutDate = wdate.toISOString().substring(0, 19);
    this.workoutService.update(updatedWorkout, updatedWorkout.id).subscribe({
      next: (success) => {
        //What to do when request is successful
        this.loadWorkouts();
        this.editWorkout = null;
        this.selected = null;
      },
      error: (err: any) => {
        console.log(err);
        console.log('Workout-List.ts Component: Error deleting workouts');
      },
    });
  }
  deleteWorkout(workoutId: number): void {
    this.workoutService.delete(workoutId).subscribe({
      next: (success) => {
        //What to do when request is successful
        this.loadWorkouts();
      },
      error: (err: any) => {
        console.log(err);
        console.log('Workout-List.ts Component: Error deleting workout');
      },
    });
  }
}
