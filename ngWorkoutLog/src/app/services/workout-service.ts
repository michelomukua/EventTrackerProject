import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Workout } from '../models/workout';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WorkoutService {
  private url = environment.baseUrl + 'api/workouts';

  constructor(private http: HttpClient) {}

  index(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        throw new Error(
          'WorkoutService.index(): error retrieving workouts: ' + err
        );
      })
    );
  }
  create(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(this.url, workout).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('WorkoutService.create(): error creating workout: ' + err)
        );
      })
    );
  }
  update(updatedWorkout: Workout, workoutId: number): Observable<Workout> {
    return this.http
      .put<Workout>(this.url + '/' + workoutId, updatedWorkout)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'WorkoutService.update(): error updating workout: ' + err
              )
          );
        })
      );
  }
  delete(workoutId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + workoutId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('WorkoutService.delete(): error deleting workout: ' + err)
        );
      })
    );
  }
}
