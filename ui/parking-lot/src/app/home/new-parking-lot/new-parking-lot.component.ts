import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {ParkingLot} from '../ParkingModel/parkingLot.model';
import {HttpClient} from '@angular/common/http'
import {CookieService} from 'ngx-cookie-service';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-new-parking-lot',
  templateUrl: './new-parking-lot.component.html',
  styleUrls: ['./new-parking-lot.component.css']
})
export class NewParkingLotComponent implements OnInit {

  
  newParkingLot: ParkingLot;
  responseMessage: String;
  constructor(private httpClient:HttpClient) { }

  ngOnInit(): void {
    this.newParkingLot = new ParkingLot();
    this.newParkingLot.address="";
    this.newParkingLot.noOfFloors=1;
    this.newParkingLot.noOfRows=1;
    this.newParkingLot.noOfSpots=1;
  }

  addParkingLot() {
    console.log("trying to add ");
    this.httpClient.post('http://localhost:8081/parkinglot/',this.newParkingLot).pipe(
      map(
        responseData=>{
          var message =  responseData["message"];
          return message;
          
        }
      )
    ).subscribe(message=>this.responseMessage=message);
  }
}
