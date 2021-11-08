import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RoutGuard implements CanActivate {
  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let status = false;

    if (sessionStorage.getItem('isLoggedIn') == "true") {
      status = true;
    } else {
      this.router.navigate(['/login']);
    }

    return status;
  }

}
