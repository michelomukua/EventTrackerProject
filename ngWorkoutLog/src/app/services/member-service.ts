import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { Member } from '../models/member';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  private url = environment.baseUrl + 'api/members';

  constructor(private http: HttpClient) {}

  index(): Observable<Member[]> {
    return this.http.get<Member[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        throw new Error(
          'MemberService.index(): error retrieving members: ' + err
        );
      })
    );
  }
}
