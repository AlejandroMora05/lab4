package com.udea.lab4.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.udea.lab4.entity.Flight;
import com.udea.lab4.entity.Reservation;
import com.udea.lab4.repository.FlightRepository;
import com.udea.lab4.repository.ReservationRepository;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private FlightRepository flightRepository;

    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
    }

    public Reservation reserveFlight(Long flightId, String passengerNamer, String seatNumber) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        if (flight.getSeatsAvailable() > 0) {
            flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
            // Crear Reserva
            Reservation reservation = new Reservation();
            reservation.setPassengerName(passengerNamer);
            reservation.setSeatNumber(seatNumber);
            reservation.setFlight(flight);

            // Generar el codigo de la reserva
            String reservationCode = generateReservationCode(flight.getFlightNumber());
            reservation.setReservationCode(reservationCode);
            return reservationRepository.save(reservation);

        } else {
            throw new RuntimeException("No seats available");
        }
    }

    // Metodo para generar el codigo de reserva
    private String generateReservationCode(String flightNumber) {
        return flightNumber + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
