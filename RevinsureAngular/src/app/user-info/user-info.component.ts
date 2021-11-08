import { Component, OnInit, NgModule} from '@angular/core';
import { User } from '../models/user';
import { UserInfo } from '../models/user.info';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  constructor(private userProfileService: UserProfileService) { }

  currentUser: User = {id: 100, email: "test@test.com", password: "test", type: "PATIENT"};

  userInfo: UserInfo = {id: 100, user: this.currentUser, firstname: "Firstname", lastname: "Lastname",
                        address: "123 Something Road", city: "New York", state: "New York", zip: "12345"};

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
    //this.getUserInfo();
  }

  //getUserInfo(){}

  toggleEmail(){
    console.log("running toggle email");
    console.log(this.editEmail);
    if (this.editEmail == false){
      this.editEmail = true;
    }else {
      this.editEmail = false;
    }
  }

  togglePassword(){
    console.log("running toggle password");
    console.log(this.editPassword);
    if (this.editPassword == false){
      this.editPassword = true;
    }else {
      this.editPassword= false;
    }
  }

  toggleUserInfo(){
    console.log("running toggle user info");
    console.log(this.editPassword);
    if (this.editUserInfo == false){
      this.editUserInfo = true;
    }else {
      this.editUserInfo = false;
    }
  }
 

  updateEmail(){
    console.log("running update email");
    console.log(this.newEmail);

    this.userProfileService.updateEmail(this.newEmail).subscribe(
      response => {
        console.log(response.status);
      }
    )

  }  

  updatePassword(){
    console.log("running update password");
  }  

  updateUserInfo(){
    console.log("running update user info");
    console.log(this.newFirstName);
    console.log(this.newLastName);
    console.log(this.newAddress);
    console.log(this.newCity);
    console.log(this.newState);
    console.log(this.newZip);

  }  
}
