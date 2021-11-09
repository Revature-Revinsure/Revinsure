import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { EmployeePortalComponent } from './employee-portal/employee-portal.component';
import { LoginComponent } from './login/login.component';
import { PatientPortalComponent } from './patient-portal/patient-portal.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ClaimComponent } from './claim/claim.component';
import { RoutGuard } from './route.guard';
import { UserInfoComponent } from './user-info/user-info.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'file-claim', component: ClaimComponent, canActivate: [RoutGuard] },
  { path: 'user-info', component: UserInfoComponent/*, canActivate: [RoutGuard] */},
  { path: 'home', component: DashboardComponent/*, canActivate: [RoutGuard] */},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
