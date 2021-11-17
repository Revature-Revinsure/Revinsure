import { Injectable } from '@angular/core';
import { DiscussionPost } from '../models/discussion-post';
import { Claim } from '../models/claim';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
@Injectable({
  providedIn: 'root'
})
export class DataService {
  baseURL: string = 'http://54.173.12.218:8000';
  userInfo!: UserInfo;
  currentUser!: User;
  currentPost!: DiscussionPost;
  userPosts!: DiscussionPost[];
  userClaims!: Claim[];
  allClaims!:Claim[];
  constructor() { }

}
