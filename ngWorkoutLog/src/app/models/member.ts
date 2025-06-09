export class Member {
  id: number;
  firstName: string;
  lastName: string;
  dateStarted: string;
  dateEnded: string;
  active: boolean;

constructor(
  id: number = 0,
  firstName: string = '',
  lastName: string = '',
  dateStarted: string = '',
  dateEnded: string = '',
  active: boolean = false

 ){
  this.id = id;
  this.firstName = firstName;
  this.lastName = lastName;
  this.dateStarted = dateStarted;
  this.dateEnded = dateEnded;
  this.active = active;
}
}

