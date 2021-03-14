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
    this.http.put("http://localhost:8081/users/login",user).
     subscribe(
      responseData=>{
        
        var sessionId = responseData["message"];
        this.cookieService.set("parkingLotUsernName", user.userName);
        this.cookieService.set("parkingLotSessionId", sessionId);
        this.userSessionId = sessionId;
        this.router.navigate(['/home']);
        
      },error=>{this.loginPagestatus=error["error"];
      console.log(error)});
     }
map   
  attemptSignUp(newUser: User) {
    console.log("user "+newUser.userName+" trying to signUp");
  
  this.http.post("http://localhost:8081/users/",newUser).
 
  
  
  subscribe(
    
    responseData=>{
      console.log("This is from RESPONSE");
      console.log(responseData);
      console.log('---');
      this.loginPagestatus = responseData["message"];
      
    }, errorReceived=>{
      
      console.log("This is from ERROR"+errorReceived);
      console.log(errorReceived);
      console.log('---');
      if(errorReceived["status"]==409) {
      this.loginPagestatus=errorReceived["error"];
      }
    }
  );
  

  }
}
