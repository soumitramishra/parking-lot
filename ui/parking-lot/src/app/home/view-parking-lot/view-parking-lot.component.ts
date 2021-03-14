import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ParkingLot } from '../ParkingModel/parkingLot.model';

@Component({
  selector: 'app-view-parking-lot',
  templateUrl: './view-parking-lot.component.html',
  styleUrls: ['./view-parking-lot.component.css']
})
export class ViewParkingLotComponent implements OnInit {

  parkingLotId = null;
  parkingLot = null;
  parkingSpots = [];
  contentReady = false;
  totalNoOfSmallSpots = 0;
  totalSmallUnoccupied = 0;
  totalNoOfMediumSpots = 0;
  totalMediumUnoccupied = 0;
  totalNoOfLargeSpots = 0;
  totalLargeUnoccupied = 0;
  constructor(private activatedRoute: ActivatedRoute, private http:HttpClient) { 
    

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=> {
      this.parkingLotId=params["parkingLotId"];
     
    });
    var request = "http://localhost:8081/parkinglot/"+this.parkingLotId;
    this.http.get(request).pipe(map(
      responseData=> {
        var listofParkingSpots = [];
        this.parkingLot= responseData["parkingLot"];
        var parkingSpots1 = responseData["parkingSpots"];
        for(const key in parkingSpots1) {
          listofParkingSpots.push({...parkingSpots1[key]});
        }
        return listofParkingSpots;
      }
    ))
    .subscribe(
      spots=> {
        this.parkingSpots=spots;
        for(var i=0;i<this.parkingSpots.length;i++) {
          //console.log();
          var size = this.parkingSpots[i].spotSize;
          var occupied = this.parkingSpots[i].occupied;
          if(size=="small") {
            this.totalNoOfSmallSpots++;
            if (occupied==false) {
              this.totalSmallUnoccupied++;
            }
          }
          if(size=="medium") {
            this.totalNoOfMediumSpots++;
            if (occupied==false) {
              this.totalMediumUnoccupied++;
            }
          }
          if(size=="small") {
            this.totalNoOfLargeSpots++;
            if (occupied==false) {
              this.totalLargeUnoccupied++;
            }
          }
        }
        // for(const key in this.parkingSpots) {
        //   console.log(this.parkingSpots[key]);
        // }
        this.contentReady = true;
      }
      
    );
    // this.http.get(request).subscribe(
    //   responseData=> {
    //     this.parkingLot = responseData["parkingLot"];
    //     this.parkingSpots = responseData["parkingSpots"];
    //     console.log(this.parkingSpots);
    //   }
      
    // );
    
    
  }

}
