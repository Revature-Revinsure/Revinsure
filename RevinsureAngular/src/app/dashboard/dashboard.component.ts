import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Claim } from '../models/claim';
import { DiscussionPost } from '../models/discussion-post';
import { User } from '../models/user';
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
  currentUser!: User;
  userInfo!: UserInfo;
  userClaims!:Claim[];
  userPosts: DiscussionPost[] | undefined = undefined;
  allClaims!:Claim[];

  viewClaims: boolean = false;

  ngOnInit(): void {
    this.getInfo();
    this.getMyPosts();
    this.currentUser=this.dataService.currentUser;
    console.log(this.currentUser) 
    if(this.currentUser.type=="PATIENT"){
      this.getClaims();
    } else if(this.currentUser.type=="EMPLOYEE"){
      this.getAllClaims();
    }  
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
  getAllClaims() {
    this.dashboardService.getAllUserClaims().subscribe(
      (data) => {
        if (data.body != null) {
          console.log(data);
          this.allClaims = data.body;
          this.dataService.allClaims = data.body;
        }
      }
    );
  }

  getMyPosts() {
    this.dashboardService.getCurrentUserPosts().subscribe(
      (data) => {
        // if (data.body != null) {
          console.log(data);
          this.userPosts = data.body!;
          this.dataService.userPosts = data.body!;
        // }
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
