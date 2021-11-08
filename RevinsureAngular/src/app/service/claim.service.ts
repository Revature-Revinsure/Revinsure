import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Claim } from '../models/claim';
import { Status } from '../models/status';
import { Message } from '../models/message';

@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  constructor(private http: HttpClient) { }

  baseUrl: string = "http://localhost:8000/api";
  claim!: Claim; //= {id: -1, dateOfService: Date, dateOfClaim: Date, amount: 100, 
  //status: Status.PENDING, description: ""};
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

    withCredentials: true,
    observe: 'response' as 'response'
  };


  makeClaim(claim: Claim): Observable<HttpResponse<Message>> {
    let addonUrl: string = "/claim";
    let fullUrl: string = this.baseUrl + addonUrl;

    this.claim = claim;


    return this.http.post<Message>(fullUrl, this.claim, this.httpOptions);
  }

}

