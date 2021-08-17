package com.australisairline.demo.service;
import com.australisairline.demo.entity.Flight;
import com.australisairline.demo.model.FlightCreateDTO;
import com.australisairline.demo.model.FlightUpdateDTO;

import java.util.List;
import java.util.UUID;
public interface FlightService {
    List<Flight> getAllFlights();

    Flight createFlight(FlightCreateDTO flightCreateDTO);

    Flight updateFlight(FlightUpdateDTO flightUpdateDTO, UUID id);

    List<Flight> getDoneFlights();

    List<Flight> getNotDoneFlights();

    void deleteFlight(UUID uuid);

    Flight getFlightbyID(UUID id);
}
