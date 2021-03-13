import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private cookieService: CookieService, private router:Router) { }

  ngOnInit(): void {
    if(!this.cookieService.check("parkingLotSessionId")) {
      this.router.navigate(['/login']);
    }
  
  }
  signout() {
    this.cookieService.delete("parkingLotSessionId");
    this.cookieService.delete("parkingLotUsernName");
    this.router.navigate(['/']);
  }
}
