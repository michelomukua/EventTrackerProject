import { Activity } from './activity';
import { Member } from './member';
export class Workout {
  id: number;
  member!: Member;
  activity!: Activity;
  workoutDate: string;
  comment: string;
  duration: number;

  constructor(
    id: number = 0,
    member: Member = new Member(),
    activity: Activity = new Activity(),
    workoutDate: string = '',
    comment: string = '',
    duratiion: number = 0
  ) {
    this.id = id;
    this.member = member;
    this.activity = activity;
    this.workoutDate = workoutDate;
    this.comment = comment;
    this.duration = duratiion;
  }
}
