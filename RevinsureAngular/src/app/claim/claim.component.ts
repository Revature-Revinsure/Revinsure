import { Component, OnInit } from '@angular/core';
import { Claim } from '../models/claim';
import { Status } from '../models/status';

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {

  currentDate: number = Date.now();

  constructor() { }

  ngOnInit(): void {
  }

  submitClaim(amount: string, description: string){
    let claim: Claim = {id: -1, dateOfService: null, dateOfClaim: this.currentDate, amount: <number>(<unknown>amount), status: Status.PENDING, description: description};
    console.log(claim);
  }
}
