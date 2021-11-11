import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Message } from '../models/message';
import { CovidQuestion } from '../models/covid-question';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class CovidQuestionsService {

  baseUrl: String = this.dataService.baseURL;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
   
  withCredentials: true, 
  observe: 'response' as 'response'
  };  

  constructor(private http: HttpClient, private dataService: DataService) { }
  
  //call this method when a user logs in
  getCurrentForm(): Observable<boolean>{
    let addonUrl: string = "/user/covid";
    let fullUrl: string = this.baseUrl + addonUrl;

    //I just want the date of the last form
    return this.http.get<boolean>(fullUrl, {withCredentials: true});
  }

  submitCovidForm(form: CovidQuestion): Observable<HttpResponse<Message>>{
    let addonUrl: string = "/user/covid";
    let fullUrl: string = this.baseUrl + addonUrl;

    return this.http.post<Message>(fullUrl, form, this.httpOptions);
  }

  updateCurrentForm(form: CovidQuestion){
    let addonUrl: string = "";
    let fullUrl: string = this.baseUrl + addonUrl;

    return this.http.put<Message>(fullUrl, form, this.httpOptions);
  }
}
