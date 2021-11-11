import { Component, OnInit } from '@angular/core';
import { Claim } from '../models/claim';
import { UserInfo } from '../models/user-info';
import { DashboardService } from '../service/dashboard.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dashboardService: DashboardService, private dataService: DataService) { }

  userInfo!: UserInfo;
  userClaims!:Claim[];
  ngOnInit(): void {
    this.getInfo();
    this.getClaims();
  }

  getClaims() {
    this.dashboardService.getCurrentUserClaims().subscribe(
      (data) => {
        if (data.body != null) {
          console.log(data.body)
          this.userClaims = data.body;
          this.dataService.userClaims = data.body;
        }
      }
    );
  }

  getInfo() {
    this.dashboardService.getCurrentUserInfo().subscribe(
      (data) => {
        if (data.body != null) {
          this.userInfo = data.body;
          this.dataService.userInfo = data.body;
        }
      }
    );
  }

}
