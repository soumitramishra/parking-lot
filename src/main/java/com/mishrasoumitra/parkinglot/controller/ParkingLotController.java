package com.mishrasoumitra.parkinglot.controller;

import com.mishrasoumitra.parkinglot.exceptions.InvalidParkingSpotSizeException;
import com.mishrasoumitra.parkinglot.exceptions.ParkingNotAvailableException;
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

    @GetMapping("/{parkingLotId}/{size}")
    public ResponseEntity<?> getOneEmptySpotBySize(@PathVariable(name = "parkingLotId") int parkingLotId,
                                             @PathVariable(name = "size") String size) {
        System.out.println("here");
        ParkingSpot parkingSpot= null;
        try {
            parkingSpot = parkingSpotService.getOneEmptySpot(parkingLotId, size);
        } catch (InvalidParkingSpotSizeException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false),HttpStatus.OK);
        } catch (ParkingNotAvailableException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false),HttpStatus.OK);
        }
        return new ResponseEntity<>(parkingSpot, HttpStatus.OK);
    }
}
