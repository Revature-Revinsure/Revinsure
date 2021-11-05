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
    password: ["", ]
    firstname: ["", Validators.required],
    lastname: ["", Validators.required],
    address: ["", Validators.required],
    city: ["", Validators.required],
    state: ["", Validators.required],
    zip: ["", Validators.required]
  });





  onSubmit() {
    if (this.registerform.valid) {
      this.registerService.userExists(this.registerform.email).subscribe(
        (data) => {
          if(data == null) {
            return; //TODO: create Message as model class, and return that instead of window.alert
          } else { window.alert("That email address is already registered. Try again, please.")}
        }

      let unregisteredUser: UserInfo = {
        id: -1, 
        email: this.registerform.email, 
        password: this.registerform.password
      }

      this.registerService.registerNewUser(unregisteredUser);
      )
      
      
      
      
      
      this.currentUserInfo = new UserInfo(
        -1,
        this.registerform.value.firstname,
        this.registerform.value.lastname,
        this.registerform.value.address,
        this.registerform.value.city,
        this.registerform.value.state,
        this.registerform.value.zip
      );

      await this.registerService.registerNewUser(this.registerform.value.email).subscribe(
        (data) => {
          if (data.body != null) {
            this.dataService.currentUser = data.body;
            this.currentUser = data.body;
            this.regService.registerInfoNewUser(this.currentUserInfo).subscribe(
              (data) => {
                if (data.status == 200) {
                  window.alert('Your registration was successful! Login and get started!');
                  this.router.navigate([this.returnUrl]);
                }
              }
            );
          }
        }
      );
    }
  }



  checkUniqueUser(email: String) {
    this.registerService.userExists(this.registerform.email).subscribe(
      (data) => {
        console.log(data);
        let unique = (data == null);
        return unique;
      }
      
      
        {
          this.registerNewUserForm(this.registerform)
            // email, 
            // password, 
            // firstname, 
            // lastname, 
            // address, 
            // city, 
            // state, 
            // zip);
          //if fail/success, toast alert
        }
        else {
          //toast, that email is already registered. did you want to login?
        }
      }
    )
  
  }
  
    registerNewUserForm(
      email: String, 
      password: string, 
      firstname: String, 
      lastname: String, 
      address: String, 
      city: String, 
      state: String, 
      zip: String) {
        
        let userForm = {
          "email": email,
          "password": password,
          "firstname": firstname,
          "lastname": lastname,
          "address": address,
          "city": city,
          "state": state,
          "zip": zip
        }
  
        this.registerService.register(userForm);
  
  
    }


//send email and password first, check that email is unique/newuser,
//if true, then register whole form,
//email and pass as User type
//
//getByUserEmail returns user obj. a null User obj will be returned if email is unique






