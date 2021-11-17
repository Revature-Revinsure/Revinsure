import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { DataService } from './data.service';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private myHttpClient: HttpClient, private dataService: DataService) { }

  baseUrl: string = this.dataService.baseURL;

  loginRequestWithPost(loginForm:FormGroup): Observable<HttpResponse<User>>{ //instead of the json, we get the entire response.
    //post request with an empty body
    return this.myHttpClient.post<User>(this.baseUrl +"/user/login", //uri
    {
    "email": loginForm.value.email,
    "password": loginForm.value.password
    }, //body 
    {withCredentials: true,observe: 'response' as 'response'} //http options, key value pairs.
    );
  }
}
