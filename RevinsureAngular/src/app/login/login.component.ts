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
    let showForm: boolean;

    this.covidService.getCurrentForm().subscribe(
      response => {
        showForm = response;
        console.log(showForm);

        if(showForm){
          this.router.navigate(['/covid-question']);
        }
        else{
          if(this.dataService.currentUser.type == "EMPLOYEE"){
            this.router.navigate(['/employeeHome']);
            } 
            else{
              this.router.navigate(['/patientHome']);
            }
        }
      }
    )//end of subscription
  }
  
}
