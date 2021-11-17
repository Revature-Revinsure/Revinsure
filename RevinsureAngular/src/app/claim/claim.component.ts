import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { ClaimService } from '../service/claim.service';
import { DataService } from '../service/data.service';
import { NotificationService } from '../service/notification.service';

@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {
  message!: string;
  currentDate: number = Date.now();
  currentUser: UserInfo = this.dataService.userInfo;
  currentType: User = this.dataService.currentUser;
  constructor(private claimService: ClaimService,
    private notificationService: NotificationService,
    private formBuilder: FormBuilder,
    private dataService: DataService,
    private router:Router) { }

  ngOnInit(): void {
    if (this.currentType.type=="EMPLOYEE"){
      this.router.navigate(["/home"]);
    }
  }

  claimForm = this.formBuilder.group({
    dateOfClaim: [this.currentDate],
    dateOfService: [null, Validators.required],
    amount: [null, Validators.required],
    description: [null, Validators.required],
  })

  submitClaim() {
    this.claimService.makeClaim(this.claimForm).subscribe(
      (response) => {
        this.message = response.body!.message!;
        this.notificationService.sendMessage(this.message);
        this.router.navigate(["/home"]);
      }
    );
  }
}
