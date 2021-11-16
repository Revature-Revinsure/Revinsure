import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Claim } from '../models/claim';
import { DiscussionPost } from '../models/discussion-post';
import { UserInfo } from '../models/user-info';
import { DashboardService } from '../service/dashboard.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private dashboardService: DashboardService, private dataService: DataService, private router: Router) { }

  userInfo!: UserInfo;
  userClaims!:Claim[];
  userPosts!: DiscussionPost[];

  viewClaims: boolean = false;

  ngOnInit(): void {
    this.getInfo();
    this.getClaims();
    this.userPosts = this.dataService.userPosts;
    
    console.log(this.userPosts);
    
  }
  
  toggleViewClaims(){
    if (this.viewClaims == true){
      this.viewClaims = false;
    }else{
      this.viewClaims = true;
    }
  }

  getClaims() {
    this.dashboardService.getCurrentUserClaims().subscribe(
      (data) => {
        if (data.body != null) {
          
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

  selectPost(post: DiscussionPost) {
    this.dataService.currentPost = post;
    this.router.navigate(['/discussion-post']);
  }
}
