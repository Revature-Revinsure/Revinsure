import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
import { UserInfoComponent } from './user-info/user-info.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
    DisscussionPostComponent,
    UserInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
