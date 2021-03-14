import { Component, OnInit } from '@angular/core';
import {ParkingLot} from '../ParkingModel/parkingLot.model';
@Component({
  selector: 'app-new-parking-lot',
  templateUrl: './new-parking-lot.component.html',
  styleUrls: ['./new-parking-lot.component.css']
})
export class NewParkingLotComponent implements OnInit {

  address="";
  noOfFloors=1;
  noOfRows=1;
  noOfSpotsPerRow=1;
  newParkingLot: ParkingLot 
  constructor() { }

  ngOnInit(): void {
  }

  addParkingLot() {
    
  }
}
