package com.australisairline.demo.repository;
import com.australisairline.demo.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.UUID;
import java.util.List;
public interface FlightRepository extends JpaRepository<Flight, UUID>{
    List<Flight> findByDateBefore(Date date);
    List<Flight> findByDateAfter(Date date);
}
