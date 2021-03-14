import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ParkingLot } from '../ParkingModel/parkingLot.model';
import {ParkingSpot} from '../ParkingModel/parkingSpotModel'
import { map } from 'rxjs/operators';
@Component({
  selector: 'app-park-vehicle',
  templateUrl: './park-vehicle.component.html',
  styleUrls: ['./park-vehicle.component.css']
})
export class ParkVehicleComponent implements OnInit {
  parkingLot:ParkingLot;
  parkingSpotObtained=null;
  parkingLotId = null;
  responseMessage = "Loading...";
  sizes = ["small", "medium", "large"]
  size = this.sizes[0];
  vehicleNo=null;
  showConfirmButton = false;
  
  constructor(private route: ActivatedRoute, private http:HttpClient) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.parkingLotId=params["parkingLotId"];
      this.responseMessage=null;
    }
    );
  }
  getEmptySpot() {
    var requestUri = "http://localhost:8081/parkinglot/getemptyspot/"+this.parkingLotId+"/"+this.size;
   this.http.get(requestUri).
     
   
   
   subscribe(
     successResponse=>{
       this.parkingSpotObtained = successResponse;
       var id = successResponse["parkingSpotId"];
       this.responseMessage = "Found Empty spot with desired Size on Floor "+id.floorNo
       +" Row "+id.rowNo+" Spot "+ id.spotNo;
       this.showConfirmButton=true;
     },
     errorResponse=>{
       console.log(errorResponse);
       this.responseMessage=errorResponse["error"];
      }
   );
  }

  confirmBooking() {
    this.parkingSpotObtained["vehicleNo"]=this.vehicleNo;
    this.parkingSpotObtained["occupied"]=true;
    var uri = "http://localhost:8081/parkinglot/updateParkingSpot";
    this.http.put(uri, this.parkingSpotObtained).subscribe(
      responseData=>{
        console.log(responseData);
      }
    );
  }

}
