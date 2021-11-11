import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private myHttpClient: HttpClient) { }

  loginRequestWithPost(loginForm:FormGroup): Observable<HttpResponse<User>>{ //instead of the json, we get the entire response.
    //post request with an empty body
    return this.myHttpClient.post<User>("http://localhost:8000/user/login", //uri
    {
    "email": loginForm.value.email,
    "password": loginForm.value.password
    }, //body 
    {withCredentials: true,observe: 'response' as 'response'} //http options, key value pairs.
    );
  }
}
