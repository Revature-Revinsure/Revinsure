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

  constructor(private httpClient: HttpClient, private dataService: DataService ) { }
  
  userExists(email: string): Observable<HttpResponse<boolean>> {
    let options = {
      withCredentials: true,
      observe: 'response' as 'response',
      body: {
        "email": email
      }
    };
      let addOnURL: string = '/register';
      let fullURL: string = this.dataService.BaseURL + addOnURL;
      return this.httpClient.get<boolean>(fullURL, options);
  }
  

  registerNewUser(unregisteredUser: User): Observable<HttpResponse<User>> {
    let addOnURL: string = '/register';
    let fullURL: string = this.dataService.BaseURL + addOnURL;
    return this.httpClient.post<User>(fullURL, {unregisteredUser}, this.httpOptions)
  }

  registerNewUserInfo(infoForm: UserInfo): Observable<HttpResponse<UserInfo>> {
    let addOnURL: string = '/registerInfo';
    let fullURL: string = this.dataService.BaseURL + addOnURL;
    return this.httpClient.post<UserInfo>(fullURL, {infoForm}, this.httpOptions)
  }
}
