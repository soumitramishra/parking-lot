import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  parkingLots = [];
  constructor(private cookieService: CookieService, private router:Router, private http:HttpClient) { }

  ngOnInit(): void {
    if(!this.cookieService.check("parkingLotSessionId")) {
      this.router.navigate(['/login']);
    }
    this.http.get('http://localhost:8081/parkinglot').pipe(
      map(
        responseData=> {
          var lots = [];
          for (const key in responseData) {
            lots.push(responseData[key]);
          }
          return lots;
        }
         
      )
    ).subscribe(lots=>this.parkingLots=lots)
    
    ;
  }
  
}
