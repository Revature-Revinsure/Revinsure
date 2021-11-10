import { Injectable } from '@angular/core';
import { Claim } from '../models/claim';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
@Injectable({
  providedIn: 'root'
})
export class DataService {
  baseURL: string = 'http://localhost:8000';
  userInfo!: UserInfo;
  currentUser!: User;
  userClaims!: Claim[];
  constructor() { }

}
