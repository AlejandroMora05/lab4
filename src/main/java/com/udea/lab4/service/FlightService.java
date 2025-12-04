package com.udea.lab4.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udea.lab4.entity.Flight;
import com.udea.lab4.repository.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    public Flight addFlight(String flightNumber, int seatsAvailable, String origin, String destination,
            LocalDateTime departureTime, LocalDateTime arrivalTime) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightNumber);
        flight.setSeatsAvailable(seatsAvailable);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        return flightRepository.save(flight);
    }

}
