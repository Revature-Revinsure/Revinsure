import { Injectable } from '@angular/core';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root'
})
export class DataService {
<<<<<<< HEAD
  currentUser!: User;
=======

  public baseURL: string = "http://localhost:8000";
  userInfo!: UserInfo;
  currentUser!: User;

>>>>>>> 920d40b20e02eb9b4785972ee16b573c3439a4e9
  constructor() { }

  get BaseURL(){
    return this.baseURL;
  }

}
