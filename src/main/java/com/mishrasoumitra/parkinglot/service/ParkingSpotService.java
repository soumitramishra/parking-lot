package com.mishrasoumitra.parkinglot.service;

import com.mishrasoumitra.parkinglot.exceptions.InvalidParkingSpotSizeException;
import com.mishrasoumitra.parkinglot.exceptions.ParkingNotAvailableException;
import com.mishrasoumitra.parkinglot.model.ParkingLot;
import com.mishrasoumitra.parkinglot.model.ParkingSpot;
import com.mishrasoumitra.parkinglot.model.ParkingSpotId;
import com.mishrasoumitra.parkinglot.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParkingSpotService {
    @Autowired
    public ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot getOneEmptySpot(int parkingLotId, String size) throws InvalidParkingSpotSizeException, ParkingNotAvailableException {

        if(!size.equals("small") && !size.equals("medium") && !size.equals("large")) {
            throw new InvalidParkingSpotSizeException(size);
        }
        List<ParkingSpot> desiredParkingSpots = parkingSpotRepository.findBySize(parkingLotId, size);
        if(desiredParkingSpots.isEmpty()) {
            throw new ParkingNotAvailableException(parkingLotId, size);
        }
        return desiredParkingSpots.get(0);
    }

    public void updateParkingSpot(ParkingSpot parkingSpot) {
        parkingSpotRepository.save(parkingSpot);
    }


    public void addParkingSpotsForNewParkingLot(ParkingLot parkingLot) {
        int F = parkingLot.getNoOfFloors();
        int R=parkingLot.getNoOfRows();
        int S=parkingLot.getNoOfSpots();
        int newParkingId = parkingLot.getParkingLotId();
        for(int f=0;f<F;f++) {
            for(int r=0;r<R;r++) {
                for(int s=0;s<S;s++) {
                    ParkingSpot parkingSpot = new ParkingSpot();
                    ParkingSpotId parkingSpotId = new ParkingSpotId();
                    parkingSpotId.setParkingLotId(newParkingId);
                    parkingSpotId.setFloorNo(f);
                    parkingSpotId.setRowNo(r);
                    parkingSpotId.setSpotNo(s);
                    parkingSpot.setParkingSpotId(parkingSpotId);
                    parkingSpot.setOccupied(false);
                    String spotSize;
                    if(s<S/3){
                        spotSize="small";
                    }
                    else if(s<(2*S)/3) {
                        spotSize="medium";
                    }
                    else {
                        spotSize="large";
                    }
                    parkingSpot.setSpotSize(spotSize);
                    parkingSpotRepository.save(parkingSpot);
                }
            }
        }
    }

    public List<ParkingSpot> getAllParkingSpots(int parkingLotId) {
        return parkingSpotRepository.getAllParkingSpotsByParkingLotId(parkingLotId);
    }

}
