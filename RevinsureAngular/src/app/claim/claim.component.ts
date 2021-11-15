import { Component, OnInit } from '@angular/core';
import { Claim } from '../models/claim';
import { Status } from '../models/status';
import { ClaimService } from '../service/claim.service';
import { NotificationService } from '../service/notification.service';

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {

  currentDate: number = Date.now();
  message: string | undefined = "";

  constructor(private claimService: ClaimService,
    private notificationService: NotificationService) { }

  ngOnInit(): void {
  }

  submitClaim(amount: string, description: string){
    let claim: Claim = {id: -1,
      dateOfService: null,
      dateOfClaim: this.currentDate,
      amount: <number>(<unknown>amount),
      status: Status.PENDING,
      description: description};
    

    this.claimService.makeClaim(claim).subscribe(
      response => {
        this.message= response.body?.message;
        this.notificationService.sendMessage(this.message);
      }
    );

  }
}
