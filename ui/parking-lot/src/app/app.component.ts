import { HttpClient } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { User } from './usermodel/user.model';
import { map } from 'rxjs/operators';
import { stringify } from '@angular/compiler/src/util';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  title = 'parking-lot';
  loginPagestatus = ""
  constructor(private http: HttpClient) {
    
  }
  attemptLogin(newUser: User) {
    console.log("user "+newUser.userName+" trying to login");
    //this.http.get("http://localhost:8081/users").subscribe(responseData=>console.log(responseData));
    this.http.get("http://localhost:8081/users/1").subscribe(responseData=>console.log(responseData.toString()));
    this.http.get("http://localhost:8081/users/2").subscribe(responseData=>console.log(responseData.toString()));
    
    
  }

  attemptSignUp(newUser: User) {
    console.log("user "+newUser.userName+" trying to signUp");
    // this.http.get("http://localhost:8081/users/").subscribe(responseData=>console.log(responseData.toString()));
  //   this.http.post("http://localhost:8080/users", JSON.stringify(newUser))
  //   .subscribe(responseData=>console.log(responseData.toString()));
  // JSON.stringify(newUser);
  // console.log(newUser);
  
  // this.http.post("http://localhost:8081/users/",tmp).subscribe(
    
  // responseData=>console.log(JSON.stringify(responseData)));
  
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
