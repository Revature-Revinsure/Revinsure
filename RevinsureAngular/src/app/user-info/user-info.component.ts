import { Component, OnInit, NgModule } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { DataService } from '../service/data.service';
import { LoginService } from '../service/login.service';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  constructor(private userProfileService: UserProfileService, private dataService: DataService, private formBuilder: FormBuilder, private loginService: LoginService) { }

  currentUser!: User;

  userInfo!: UserInfo;

  editEmail: boolean = false;
  editPassword: boolean = false;
  editUserInfo: boolean = false;

  updateEmailForm = this.formBuilder.group({
    newEmail: ["", [Validators.required, Validators.email]],
  });
  updatePasswordForm = this.formBuilder.group({
    newPassword: ["", Validators.required],
  });
  updateInfoForm = this.formBuilder.group({
    newFirstName: [this.dataService.userInfo.firstName, Validators.required],
    newLastName: [this.dataService.userInfo.lastName, Validators.required],
    newAddress: [this.dataService.userInfo.address, Validators.required],
    newState: [this.dataService.userInfo.state, Validators.required],
    newCity: [this.dataService.userInfo.city, Validators.required],
    newZip: [this.dataService.userInfo.zip, Validators.required],
  });


  //needs to be a user info object


  ngOnInit(): void {
    this.currentUser = this.dataService.currentUser;
    this.userInfo = this.dataService.userInfo;
  }

  //getUserInfo(){}

  //getUser{}

  toggleEmail() {
    if (this.editEmail == false) {
      this.editEmail = true;
    } else {
      this.editEmail = false;
    }
  }

  togglePassword() {
    if (this.editPassword == false) {
      this.editPassword = true;
    } else {
      this.editPassword = false;
    }
  }

  toggleUserInfo() {
    if (this.editUserInfo == false) {
      this.editUserInfo = true;
    } else {
      this.editUserInfo = false;
    }
  }


  updateEmail() {
    let userForm = this.formBuilder.group({
      email: this.updateEmailForm.value.newEmail,
      password: this.currentUser.password
    });
    this.userProfileService.updateEmail(this.updateEmailForm.value.newEmail).subscribe(
      (data) => this.loginService.loginRequestWithPost(userForm).subscribe(
        (data) => {
          this.dataService.currentUser = data.body!;
          this.currentUser = data.body!;
        }
      )
    );
  }

  updatePassword() {
    let userForm = this.formBuilder.group({
      email: this.updateEmailForm.value.newEmail,
      password: this.currentUser.password
    });
    this.userProfileService.updatePassword(this.updatePasswordForm.value.newPassword).subscribe(
      () => this.loginService.loginRequestWithPost(userForm).subscribe(
        (data) => this.dataService.currentUser = data.body!
      )
    );
  }

  updateUserInfo() {
    let userForm = this.formBuilder.group({
      email: this.currentUser.email,
      password: this.currentUser.password
    });
    this.userProfileService.updateUserInfo(this.updateInfoForm).subscribe(
      () => this.loginService.loginRequestWithPost(userForm).subscribe(
        (data) => {
          this.dataService.currentUser = data.body!;
        }
      )
    );
  }
}
