import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { User } from './usermodel/user.model';
import { map } from 'rxjs/operators';
import { Router } from "@angular/router";
import { CookieService } from "ngx-cookie-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title = 'parking-lot';
  loginPagestatus = "";
  userSessionId  = null;
  constructor(private http: HttpClient, private router: Router, private cookieService:CookieService) {
    
  }
  ngOnInit(): void {
    if(this.cookieService.check("parkingLotSessionId")) {
      this.userSessionId=this.cookieService.get("parkingLotSessionId");
      this.router.navigate(['/home']);
    }
    else {
      this.userSessionId=null;
    }
  }

  checkAlreadyLoggedIn() {
    return this.cookieService.check("parkingLotSessionId");
  }
  attemptLogin(user: User) {
    console.log("hereherherherhehrrhehehe");
    console.log("user "+user.userName+" trying to login");
    this.http.post("http://localhost:8081/users/login",user).pipe(
   map(
     responseData=>{
       console.log(responseData["successStatus"]);
       var sessionId = responseData["message"];
       var successStatus = responseData["successStatus"];
       if(successStatus==true) {
         this.cookieService.set("parkingLotUsernName", user.userName);
         this.cookieService.set("parkingLotSessionId", sessionId);
         this.userSessionId = sessionId;
         this.router.navigate(['/home']);
       }
       return sessionId;
     }
   )
  ).
  subscribe(
    message=>this.loginPagestatus=message);
  }

  attemptSignUp(newUser: User) {
    console.log("user "+newUser.userName+" trying to signUp");
  
  this.http.post("http://localhost:8081/users/",newUser).pipe(
   map(
     responseData=>{
       console.log(responseData);
       var message = responseData["message"];
      //  for(const key in responseData) {
      //   message=responseData[key];
      //  }
       return message;
     }

   )
  ).
  
  
  subscribe(
    
    message=>this.loginPagestatus=message);
  
  }
}
