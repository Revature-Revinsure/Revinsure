import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './models/user';
import { DataService } from './service/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'RevinsureAngular';

  constructor(private router: Router, private dataService: DataService) { let currentUser: User = this.dataService.currentUser;}
  
  logout(): void {
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }
}
