package com.smartcompound.repository;

import com.smartcompound.entity.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {

    @Query("SELECT t FROM BusTrip t WHERE LOWER(t.destination) LIKE LOWER(CONCAT('%', :dest, '%'))")
    List<BusTrip> searchByDestination(@Param("dest") String dest);
}
