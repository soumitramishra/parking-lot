import { Component, Input, Output, OnInit, EventEmitter } from '@angular/core';
import {User} from '../usermodel/user.model'
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
  
  constructor() { }

  ngOnInit(): void {
  }

  onLogin() {
    var user= new User(this.userName, this.password,null,null,null);
    this.userLoginEvent.emit(user);
  }

  onSignUp() {
    var user= new User(this.userName, this.password,this.firstName, this.lastName, this.userType);
    this.userSignUpEvent.emit(user);
  }

}
