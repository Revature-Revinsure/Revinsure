import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from '../models/user';
import { ResetPasswordService } from '../service/reset-password.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
 

  constructor(private formBuilder:FormBuilder,private resetPasswordService: ResetPasswordService) { }

  ngOnInit(): void {
  }
  resetPassForm = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password: ["", Validators.required]
  });

  resetPassword(){
    
    let forgetfulUser: User = {
      id: -1, 
      email: this.resetPassForm.value.email, 
      password: this.resetPassForm.value.password,
      type: ""
    }

    if(this.resetPassForm.valid){
      this.resetPasswordService.userExists(this.resetPassForm.value.email).subscribe(
        (data)=> {
          
          if(data.body){
            this.resetPasswordService.updateUserPassword(forgetfulUser).subscribe(

              (data)=>{
                  
              }
            );
          }
        }


      );
    }

  }
}
