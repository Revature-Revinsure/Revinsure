import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PatientPortalComponent } from './patient-portal/patient-portal.component';
import { EmployeePortalComponent } from './employee-portal/employee-portal.component';
import { ClaimComponent } from './claim/claim.component';
import { CovidQuestionsComponent } from './covid-questions/covid-questions.component';
import { DisscussionBoardComponent } from './disscussion-board/disscussion-board.component';
import { DisscussionPostComponent } from './disscussion-post/disscussion-post.component';
import { HttpClientModule } from '@angular/common/http';
import { ClaimService } from './service/claim.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PatientPortalComponent,
    EmployeePortalComponent,
    ClaimComponent,
    CovidQuestionsComponent,
    DisscussionBoardComponent,
    DisscussionPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
<<<<<<< HEAD
    HttpClientModule
=======
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
>>>>>>> 41612e69dbcfc4f9b9466aa5be3811ecbd9a0162
  ],
  providers: [ClaimService],
  bootstrap: [AppComponent]
})
export class AppModule { }
