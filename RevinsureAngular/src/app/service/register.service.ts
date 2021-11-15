import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { DataService } from './data.service';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {


  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true,
    observe: 'response' as 'response'
  };

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  userExists(email: string): Observable<HttpResponse<boolean>> {
    
    let options = {
      withCredentials: true,
      observe: 'response' as 'response',
      body: {
        "email": email
      }

    };

    let addOnURL: string = '/user/register/check';
    let fullURL: string = this.dataService.baseURL + addOnURL;
    return this.httpClient.post<HttpResponse<boolean>>(fullURL, options);
  }


  registerNewUser(unregisteredUser: User): Observable<HttpResponse<User>> {
    let addOnURL: string = '/user/register';
    let fullURL: string = this.dataService.baseURL + addOnURL;
    return this.httpClient.post<User>(fullURL, {

      "id": -1,
      "email": unregisteredUser.email,
      "password": unregisteredUser.password,
      "type": unregisteredUser.type


    }, this.httpOptions)
  }

  registerNewUserInfo(infoForm: UserInfo): Observable<HttpResponse<UserInfo>> {
    let addOnURL: string = '/user/registerInfo';
    let fullURL: string = this.dataService.baseURL + addOnURL;
    return this.httpClient.post<UserInfo>(fullURL, {
      "id": -1,
      "firstName": infoForm.firstName,
      "lastName": infoForm.lastName,
      "address": infoForm.address,
      "city": infoForm.city,
      "state": infoForm.state,
      "zip": infoForm.zip
    }, this.httpOptions)
  }
}
