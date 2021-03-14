package com.mishrasoumitra.parkinglot.repository;

import com.mishrasoumitra.parkinglot.model.ParkingSpot;
import com.mishrasoumitra.parkinglot.model.ParkingSpotId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, ParkingSpotId> {
    @Query("SELECT p from ParkingSpot p where p.occupied=false and p.spotSize =:spotSize and p.parkingSpotId.parkingLotId=:parkingLotId")
    List<ParkingSpot> findBySize(@Param("parkingLotId") int parkingLotId, @Param("spotSize") String sp);

    @Query("SELECT p from ParkingSpot p where p.parkingSpotId.parkingLotId =:parkingLotId")
    List<ParkingSpot> getAllParkingSpotsByParkingLotId(@Param("parkingLotId") int parkingLotId);
}
