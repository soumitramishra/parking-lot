package com.mishrasoumitra.parkinglot.repository;

import com.mishrasoumitra.parkinglot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
}
