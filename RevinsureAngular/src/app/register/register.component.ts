import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

import { DataService } from '../service/data.service';
import { RegisterService } from '../service/register.service';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';
import { NotificationService } from '../service/notification.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  returnUrl: string | any;
  currentUser!: User;
  currentUserInfo!: UserInfo;

  constructor(
    private registerService: RegisterService,
    private formBuilder: FormBuilder,
    private dataService: DataService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.returnUrl = '/login';
    this.currentUser = this.dataService.currentUser;
    this.currentUserInfo = this.dataService.userInfo;
  }

  registerform = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password: ["", Validators.required],
    firstname: ["", Validators.required],
    lastname: ["", Validators.required],
    address: ["", Validators.required],
    city: ["", Validators.required],
    state: ["", Validators.required],
    zip: ["", Validators.required],
    type: ["", Validators.required]
  });





  onSubmit() {

    let unregisteredUser: User = {
      id: -1,
      email: this.registerform.value.email,
      password: this.registerform.value.password,
      type: this.registerform.value.type,
      posts:[]
    }

    let newUserInfo: UserInfo = {
      id: -1,
      //user: unregisteredUser,
      firstName: this.registerform.value.firstname,
      lastName: this.registerform.value.lastname,
      address: this.registerform.value.address,
      city: this.registerform.value.city,
      state: this.registerform.value.state,
      zip: this.registerform.value.zip
    }

    if (this.registerform.valid) {
      this.registerService.userExists(this.registerform.value.email).subscribe(
        (data) => {

          if (data.body == null) {
            this.registerService.registerNewUser(unregisteredUser).subscribe(
              (data) => {
                if(data.body)
                this.registerService.registerNewUserInfo(newUserInfo).subscribe(
                  (data) => {
                    if (data.body) {
                      this.notificationService.sendMessage("Your registration was successful! Login to continue.")
                    }
                  });
              });
          }
        });
    }
  }

}