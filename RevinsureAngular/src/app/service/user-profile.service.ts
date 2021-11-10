import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';
import { UserInfo } from '../models/user-info';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  baseUrl: string = "http://localhost:8000";

  constructor(private httpClient: HttpClient) { }

  updateEmail(email: string): Observable<HttpResponse<any>> {
    return this.httpClient.post<String>(this.baseUrl + "/user/updateEmail", email, { withCredentials: true, observe: 'response' as 'response' })
  }

  updatePassword(password: string): Observable<HttpResponse<any>> {
    return this.httpClient.post<String>(this.baseUrl + "/user/updatePassword", password, { withCredentials: true, observe: 'response' as 'response' })
  }

  updateUserInfo(userInfo: UserInfo): Observable<HttpResponse<any>> {
    return this.httpClient.post<UserInfo>(this.baseUrl + "/user/updateUserInfo", userInfo, { withCredentials: true, observe: 'response' as 'response' })
  }
}
