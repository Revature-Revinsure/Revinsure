import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Message } from '../models/message';
import { CovidQuestion } from '../models/covid-question';

@Injectable({
  providedIn: 'root'
})
export class CovidQuestionsService {

  baseUrl: String = "http://localhost:8000";

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
   
  withCredentials: true, 
  observe: 'response' as 'response'
  };  

  constructor(private http: HttpClient) { }
  
  //call this method when a user logs in
  getCurrentForm(): Observable<HttpResponse<Date>>{
    let addonUrl: string = "";
    let fullUrl: string = this.baseUrl + addonUrl;

    //I just want the date of the last form
    return this.http.get<Date>(fullUrl, this.httpOptions);
  }

  submitNewForm(form: CovidQuestion): Observable<HttpResponse<Message>>{
    let addonUrl: string = "";
    let fullUrl: string = this.baseUrl + addonUrl;

    return this.http.post<Message>(fullUrl, form, this.httpOptions);
  }

  updateCurrentForm(form: CovidQuestion){
    let addonUrl: string = "";
    let fullUrl: string = this.baseUrl + addonUrl;

    return this.http.put<Message>(fullUrl, form, this.httpOptions);
  }
}
