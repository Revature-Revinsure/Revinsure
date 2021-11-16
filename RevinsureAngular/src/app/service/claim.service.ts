import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Status } from '../models/status';
import { Message } from '../models/message';
import { DataService } from './data.service';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ClaimService {

  constructor(private http: HttpClient, private dataService: DataService) { }

  baseUrl: string = this.dataService.baseURL;

  makeClaim(claim:FormGroup): Observable<HttpResponse<Message>> {
    let addonUrl: string = "/api/claim";
    let fullUrl: string = this.baseUrl + addonUrl;

    return this.http.post<Message>(fullUrl, {
      dateOfService: claim.value.dateOfService,
      dateOfClaim: claim.value.dateOfClaim,
      amount: claim.value.amount,
      status: Status.PENDING,
      description: claim.value.description
    }, {
      withCredentials: true,
      observe: 'response' as 'response'
    });
  }

}

