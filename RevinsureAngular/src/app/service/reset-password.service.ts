import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class ResetPasswordService {

  constructor(private httpClient: HttpClient, private dataService:DataService) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true,
    observe: 'response' as 'response'
  };
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
    return this.httpClient.post<boolean>(fullURL, {"email":email}, this.httpOptions);
  }

  updateUserPassword(user: User):Observable<HttpResponse<boolean>>{

    
    let addOnURL: string = '/user/updatePasswordByEmail';
    let fullURL: string = this.dataService.baseURL + addOnURL;

    return this.httpClient.put<boolean>(fullURL,{
      "email" : user.email,
      "password" : user.password

    },this.httpOptions);
  }
}
