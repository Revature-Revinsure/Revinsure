import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    private loginService: LoginService) { }

  ngOnInit(): void {
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
            if(data.body.type == "EMPLOYEE"){
            this.router.navigate(['/employeeHome']);
            } else {
              this.router.navigate(['/patientHome']);
            }
          }
        }
      );
    }
  }
}
