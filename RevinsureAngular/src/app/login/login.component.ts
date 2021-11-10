import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CovidQuestionsService } from '../service/covid-questions.service';
import { DataService } from '../service/data.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,
    private dataService: DataService,
    private router: Router,
    private loginService: LoginService,
    private covidService: CovidQuestionsService) { }

  ngOnInit(): void {
    sessionStorage.setItem('isLoggedIn',"false");
  }

  loginform = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password: ["", Validators.required]
  });

  login() {
    if (this.loginform.valid) {
      this.loginService.loginRequestWithPost(this.loginform).subscribe(
        (data) => {
          if (data.body != null) {
            sessionStorage.setItem('isLoggedIn',"true");
            this.dataService.currentUser = data.body;

            this.getCovidForm();
          }
        }
      );
    }
  }

  getCovidForm(){
    this.covidService.getCurrentForm().subscribe(
      response => {
        let responseDate: number | null = response.body?.valueOf;
        let currentDate: number = <number>(<unknown>Date());

        //if the user is logging in for the first time
        if(responseDate == null){
          this.router.navigate(['/covid-question'])
        }
        //if the user has previously filled out the covid verification
        else if(responseDate != null){
          let difference: number = currentDate - responseDate;
          difference = Math.ceil(difference / 86400000);
          
          //if it has been at least 2 weeks since the user filled out the verification
          if(difference >= 14){
            this.router.navigate(['/covid-question']);
          }
          //if it has not been 2 weeks since the user filled out the verification
          else{
            if(this.dataService.currentUser.type == "EMPLOYEE"){
              this.router.navigate(['/employeeHome']);
              } else {
                this.router.navigate(['/patientHome']);
              }
          }

        }
      }
    )
  }
}
