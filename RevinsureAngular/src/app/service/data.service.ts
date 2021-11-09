import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
@Injectable({
  providedIn: 'root'
})
export class DataService {
  public baseURL: string = "http://localhost:8000";
  userInfo!: UserInfo;
  currentUser!: User;
  constructor() { }

  BaseURL(){
    return this.baseURL;
  }

}
