import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeePortalComponent } from './employee-portal/employee-portal.component';
import { LoginComponent } from './login/login.component';
import { PatientPortalComponent } from './patient-portal/patient-portal.component';
import { RegisterComponent } from './register/register.component';
import { RoutGuard } from './route.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent},
  { path: 'employeeHome', component: EmployeePortalComponent, canActivate: [RoutGuard]},
  { path: 'patientHome', component: PatientPortalComponent, canActivate: [RoutGuard]},
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
  { path: '**',   redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
