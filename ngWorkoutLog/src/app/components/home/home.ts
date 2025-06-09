import { MemberService } from './../../services/member-service';
import { Member } from './../../models/member';
import { CommonModule } from '@angular/common';
import { Activity } from './../../models/activity';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivityService } from '../../services/activity-service';
import { WorkoutList } from "../workout-list/workout-list";

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule, WorkoutList],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {





}
