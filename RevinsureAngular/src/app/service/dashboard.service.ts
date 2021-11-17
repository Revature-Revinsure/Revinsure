import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Claim } from '../models/claim';
import { DiscussionPost } from '../models/discussion-post';
import { UserInfo } from '../models/user-info';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private httpClient:HttpClient, private dataService: DataService) { }

  baseUrl: string = this.dataService.baseURL;

  getCurrentUserClaims(){
    return this.httpClient.get<Claim[]>(this.baseUrl + "/api/userClaims",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }

  getAllUserClaims(){
    return this.httpClient.get<Claim[]>(this.baseUrl + "/api/allClaims",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }

  getCurrentUserPosts(){
    return this.httpClient.get<DiscussionPost[]>(this.baseUrl + "/discussion/myPosts",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }

  getCurrentUserInfo(){
    return this.httpClient.get<UserInfo>(this.baseUrl + "/user/userInfo",
      { withCredentials: true, observe: 'response' as 'response' }
    );
  }
}
