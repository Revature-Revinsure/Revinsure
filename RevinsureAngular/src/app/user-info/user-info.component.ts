import { Component, OnInit, NgModule } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginComponent } from '../login/login.component';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { DashboardService } from '../service/dashboard.service';
import { DataService } from '../service/data.service';
import { LoginService } from '../service/login.service';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  constructor(
    private userProfileService: UserProfileService,
    private dataService: DataService,
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private dashboardService: DashboardService
  ) { }
  currentUser!: User;
  userInfo!: UserInfo;

  updateEmailForm = this.formBuilder.group({
    newEmail: [null, [Validators.required, Validators.email]],
  });
  updatePasswordForm = this.formBuilder.group({
    newPassword: [null, Validators.required],
  });
  updateInfoForm = this.formBuilder.group({
    newFirstName: [null, Validators.required],
    newLastName: [null, Validators.required],
    newAddress: [null, Validators.required],
    newState: [this.dataService.userInfo.state, Validators.required],
    newCity: [null, Validators.required],
    newZip: [null, Validators.required],
  });

  ngOnInit(): void {
    this.currentUser = this.dataService.currentUser;
    this.userInfo = this.dataService.userInfo;
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
    this.userProfileService.updateUserInfo(this.updateInfoForm).subscribe(
      () => this.dashboardService.getCurrentUserInfo().subscribe(
        (data) => {
          this.dataService.userInfo = data.body!;
          this.userInfo = data.body!;
        }
      )
    );
  }
}
