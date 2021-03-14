package com.mishrasoumitra.parkinglot.service;

import com.mishrasoumitra.parkinglot.exceptions.ParkingLotNotFoundException;
import com.mishrasoumitra.parkinglot.model.ParkingLot;
import com.mishrasoumitra.parkinglot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository;
    public List<ParkingLot> listAllParkingLots() {
        return parkingLotRepository.findAll();
    }
    public ParkingLot addParking(ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingById(int id) throws ParkingLotNotFoundException {
        if(!parkingLotRepository.existsById(id)) {
            throw new ParkingLotNotFoundException(id);
        }
        return parkingLotRepository.findById(id).get();
    }
}
