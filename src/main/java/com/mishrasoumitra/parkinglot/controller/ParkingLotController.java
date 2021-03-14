package com.mishrasoumitra.parkinglot.controller;

import com.mishrasoumitra.parkinglot.exceptions.InvalidParkingSpotSizeException;
import com.mishrasoumitra.parkinglot.exceptions.ParkingLotNotFoundException;
import com.mishrasoumitra.parkinglot.exceptions.ParkingNotAvailableException;
import com.mishrasoumitra.parkinglot.model.ParkingDetails;
import com.mishrasoumitra.parkinglot.model.ParkingLot;

import com.mishrasoumitra.parkinglot.model.ParkingSpot;
import com.mishrasoumitra.parkinglot.model.Response;
import com.mishrasoumitra.parkinglot.service.ParkingLotService;
import com.mishrasoumitra.parkinglot.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglot")
@CrossOrigin
public class ParkingLotController {
    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    ParkingSpotService parkingSpotService;

    @GetMapping("")
    public List<ParkingLot> list() {
        return parkingLotService.listAllParkingLots();
    }


    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ParkingLot parkingLot) {
        parkingLot = parkingLotService.addParking(parkingLot);
        parkingSpotService.addParkingSpotsForNewParkingLot(parkingLot);
        return new ResponseEntity<>(new Response("Created new parkinglot with ID "+parkingLot.getParkingLotId()),HttpStatus.OK);
    }

    @GetMapping("/{parkingLotId}")
    public ResponseEntity<?> getParkingDetails(@PathVariable(name = "parkingLotId") int parkingLotId) {
        try {
            ParkingLot parkingLot = parkingLotService.getParkingById(parkingLotId);
            List<ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots(parkingLotId);
            ParkingDetails parkingDetails = new ParkingDetails();
            parkingDetails.setParkingLot(parkingLot);
            parkingDetails.setParkingSpots(parkingSpots);
            return new ResponseEntity<>(parkingDetails, HttpStatus.OK);
        } catch (ParkingLotNotFoundException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false), HttpStatus.OK);
        }
    }
    @GetMapping("/getemptyspot/{parkingLotId}/{size}")
    public ResponseEntity<?> getOneEmptySpot(@PathVariable(name = "parkingLotId") int parkingLotId,
                                             @PathVariable(name = "size") String size) {
        try {
            ParkingSpot parkingSpot = parkingSpotService.getOneEmptySpot(parkingLotId, size);
            return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
        }
        catch (InvalidParkingSpotSizeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ParkingNotAvailableException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/updateParkingSpot")
    public ResponseEntity<?> addVehicle(@RequestBody ParkingSpot parkingSpot) {
            parkingSpotService.updateParkingSpot(parkingSpot);
            return new ResponseEntity<>(String.format(
                    "Vehicle assigned to Parking Lot %d, Floor %d, Row %d, " +
                            "Spot %d", parkingSpot.getParkingSpotId().getParkingLotId(),
                    parkingSpot.getParkingSpotId().getFloorNo(),
                    parkingSpot.getParkingSpotId().getRowNo(),
                    parkingSpot.getParkingSpotId().getSpotNo()),
                    HttpStatus.OK);
    }
}
