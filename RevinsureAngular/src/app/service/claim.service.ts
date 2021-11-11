import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Claim } from '../models/claim';
import { Status } from '../models/status';
import { Message } from '../models/message';
import { DataService } from './data.service';
//import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  constructor(private http: HttpClient, private dataService: DataService) { }

  baseUrl: string = this.dataService.baseURL;
  claim!: Claim; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

    withCredentials: true,
    observe: 'response' as 'response'
  };


  makeClaim(claim: Claim): Observable<HttpResponse<Message>> {
    let addonUrl: string = "/api/claim";
    let fullUrl: string = this.baseUrl + addonUrl;

    this.claim = claim;


    return this.http.post<Message>(fullUrl, this.claim, this.httpOptions);
  }

}

