import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';

import { DataService } from '../service/data.service';
import { RegisterService } from '../service/register.service';
import { User } from '../models/user';
import { UserInfo } from '../models/user-info';


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
    private router: Router,
    private formBuilder: FormBuilder,
    private dataService: DataService) { }

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
    zip: ["", Validators.required]
  });





  onSubmit() {

    let unregisteredUser: User = {
      id: -1, 
      email: this.registerform.value.email, 
      password: this.registerform.value.password
    }

    let newUserInfo: UserInfo = {
      id: -1,
      user: unregisteredUser,
      firstname: this.registerform.value.firstname,
      lastname: this.registerform.value.lastname, 
      address: this.registerform.value.address, 
      city: this.registerform.value.city,
      state: this.registerform.value.state,
      zip: this.registerform.value.zip
    }

    if (this.registerform.valid) {
      this.registerService.userExists(this.registerform.value.email).subscribe(
        (data) => {
          if(!data) {
            this.registerService.registerNewUser(unregisteredUser).subscribe(
              (data) => {
                this.registerService.registerNewUserInfo(newUserInfo).subscribe(
                    (data) => {
                      console.log(data);
                      if(data) {
                        window.alert("Your registration was successful! Login to continue.");
                        //TODO: create Message as model class, and return that instead of window.alert
                      }
                    });                  
                  });
          }
        });
    }
  }               

}