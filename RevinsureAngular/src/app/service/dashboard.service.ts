import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Claim } from '../models/claim';
import { UserInfo } from '../models/user-info';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private httpClient:HttpClient) { }

  getCurrentUserClaims(){
    return this.httpClient.get<Claim[]>("http://localhost:8000/api/userClaims",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }

  getCurrentUserInfo(){
    return this.httpClient.get<UserInfo>("http://localhost:8000/user/userInfo",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }
}
