import { Injectable } from '@angular/core';
import { DiscussionPost } from '../models/discussion-post';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
@Injectable({
  providedIn: 'root'
})
export class DataService {
  public baseURL: string = "http://localhost:8000";
  userInfo!: UserInfo;
  currentUser!: User;
  currentPost!: DiscussionPost;

  constructor() { }



}
