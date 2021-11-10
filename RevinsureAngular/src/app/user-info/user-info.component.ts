import { Component, OnInit, NgModule } from '@angular/core';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { DataService } from '../service/data.service';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  constructor(private userProfileService: UserProfileService, private dataService:DataService) { }

  currentUser!: User;

  userInfo!: UserInfo;

  editEmail: boolean = false;
  editPassword: boolean = false;
  editUserInfo: boolean = false;

  newEmail: string = "";
  newPassword: string = "";

  newFirstName: string = "";
  newLastName: string = "";
  newAddress: string = "";
  newState: string = "";
  newCity: string = "";
  newZip: string = "";
  //needs to be a user info object


  ngOnInit(): void {
   this.currentUser = this.dataService.currentUser;
   this.userInfo = this.dataService.userInfo;
  }

  //getUserInfo(){}

  //getUser{}

  toggleEmail() {
    console.log("running toggle email");
    console.log(this.editEmail);
    if (this.editEmail == false) {
      this.editEmail = true;
    } else {
      this.editEmail = false;
    }
  }

  togglePassword() {
    console.log("running toggle password");
    console.log(this.editPassword);
    if (this.editPassword == false) {
      this.editPassword = true;
    } else {
      this.editPassword = false;
    }
  }

  toggleUserInfo() {
    console.log("running toggle user info");
    console.log(this.editPassword);
    if (this.editUserInfo == false) {
      this.editUserInfo = true;
    } else {
      this.editUserInfo = false;
    }
  }


  updateEmail() {
    console.log("running update email");
    console.log(this.newEmail);

    this.userProfileService.updateEmail(this.newEmail).subscribe(
      response => {
        console.log(response.status);
      }
    )

  }

  updatePassword() {
    console.log("running update password");
    console.log(this.newPassword);

    this.userProfileService.updatePassword(this.newPassword).subscribe(
      response => {
        console.log(response.status);

        //if status = 200 then show updated green check mark window
      }
    )
  }

  updateUserInfo() {
    console.log("running update user info");
    console.log(this.newFirstName);
    console.log(this.newLastName);
    console.log(this.newAddress);
    console.log(this.newCity);
    console.log(this.newState);
    console.log(this.newZip);

    if (this.newFirstName != "") {
      this.userInfo.firstname = this.newFirstName;
    }

    if (this.newLastName != "") {
      this.userInfo.lastname = this.newLastName;
    }

    if (this.newAddress != "") {
      this.userInfo.address = this.newAddress;
    }

    if (this.newCity != "") {
      this.userInfo.city = this.newCity;
    }

    if (this.newState != "") {
      this.userInfo.state = this.newState;
    }

    if (this.newZip != "") {
      this.userInfo.zip = this.newZip;
    }

    this.userProfileService.updateUserInfo(this.userInfo).subscribe(
      response => {
        console.log(response.status);
      }
    )

  }
}
