import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import {User} from '../usermodel/user.model'
import {Router} from '@angular/router';
import {CookieService} from 'ngx-cookie-service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  myList = ["hello", "Hi"];
  userName="";
  password ="";
  firstName="";
  lastName="";
  userTypes =  ["admin", "operator"];
  userType="admin";
  @Input() loginStatus="";
  signUp=false;
  @Output() userLoginEvent = new EventEmitter<User>();
  @Output() userSignUpEvent = new EventEmitter<User>();
  constructor(private cookieService: CookieService, private router: Router) {

  }

  ngOnInit(): void {
    if(this.cookieService.check("parkingLotSessionId")){
      console.log("true1111");
      this.router.navigate(['/home']);
    }
  }
  
  onLogin() {
    console.log("onlogin called from login compo")
    var user= new User(this.userName, this.password,null,null,null);
    this.userLoginEvent.emit(user);
  }

  onSignUp() {
    console.log("onSignUp called from login compo");
    var user= new User(this.userName, this.password,this.firstName, this.lastName, this.userType);
    this.userSignUpEvent.emit(user);
  }

}
