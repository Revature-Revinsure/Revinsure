import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';
import { UserInfo } from '../models/user-info';
import { FormGroup } from '@angular/forms';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  baseUrl: string = this.dataService.baseURL;

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  updateEmail(email: string): Observable<HttpResponse<any>> {
    return this.httpClient.post<String>(this.baseUrl + "/user/updateEmail", email, { withCredentials: true, observe: 'response' as 'response' })
  }

  updatePassword(password: string): Observable<HttpResponse<any>> {
    return this.httpClient.post<String>(this.baseUrl + "/user/updatePassword", password, { withCredentials: true, observe: 'response' as 'response' })
  }

  updateUserInfo(userInfoForm: FormGroup): Observable<HttpResponse<any>> {

    return this.httpClient.post<UserInfo>(this.baseUrl + "/user/updateUserInfo", {
      "firstName": userInfoForm.value.newFirstName,
      "lastName": userInfoForm.value.newLastName,
      "address": userInfoForm.value.newAddress,
      "city": userInfoForm.value.newCity,
      "state": userInfoForm.value.newState,
      "zip": userInfoForm.value.newZip
    }, { withCredentials: true, observe: 'response' as 'response' })
  }
}
