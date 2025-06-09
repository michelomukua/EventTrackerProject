import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { Activity } from '../models/activity';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  private url = environment.baseUrl + 'api/activities';

  constructor(private http: HttpClient) {}

  index(): Observable<Activity[]> {
    return this.http.get<Activity[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        throw new Error(
          'ActivityService.index(): error retrieving activities: ' + err
        );
      })
    );
  }
  create(activity: Activity): Observable<Activity> {
    return this.http.post<Activity>(this.url, activity).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ActivityService.create(): error creating activity: ' + err
            )
        );
      })
    );
  }
  update(updatedActivity: Activity, activityId: number): Observable<Activity> {
    return this.http
      .put<Activity>(this.url + '/' + activityId, updatedActivity)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'ActivityService.update(): error updating activity: ' + err
              )
          );
        })
      );
  }
  delete(activityId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + activityId).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ActivityService.delete(): error deleting activity: ' + err
            )
        );
      })
    );
  }
}
