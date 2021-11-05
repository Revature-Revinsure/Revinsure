import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  

  httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      
      withCredentials: true, //only one needed in a GET; others are for POST, PUT, ETC
      observe: 'response' as 'response'
      }; 

  constructor(private httpClient: HttpClient, private dataService: DataService ) { }
  
  userExists(email: String): Observable<HttpResponse<User>> {
      let addOnURL: String = '/register';
      let fullURL: String = this.dataService.BaseURL + addOnURL;
      return this.httpClient.post<User>(fullURL, {"email": email}, this.httpOptions);
      }
  

  registerNewUser(unregisteredUser: User) {
    this.httpClient.post<>(this.dataService.urlForRegister, { })
  }

  registerNewUserInfo(infoForm: {firstname: String; lastname: String; address: String; city: String; state: String; zip: String})
  


}
