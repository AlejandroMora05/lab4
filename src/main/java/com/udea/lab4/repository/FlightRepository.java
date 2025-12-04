package com.udea.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udea.lab4.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
}
