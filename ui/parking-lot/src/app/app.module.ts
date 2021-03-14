import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {Routes, RouterModule} from '@angular/router';



import { AppComponent } from './app.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { NewParkingLotComponent } from './home/new-parking-lot/new-parking-lot.component';

const appRoutes: Routes = [
  {path: 'login', redirectTo: '/'},
  {path: 'home', component:HomeComponent},
  {path: 'newParkingLot', component:NewParkingLotComponent},
  {path: '**', redirectTo:'/home'}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    NewParkingLotComponent
        ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
