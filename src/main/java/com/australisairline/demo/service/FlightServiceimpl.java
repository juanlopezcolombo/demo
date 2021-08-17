package com.australisairline.demo.service;

import com.australisairline.demo.entity.Flight;
import com.australisairline.demo.model.FlightCreateDTO;
import com.australisairline.demo.model.FlightUpdateDTO;

import com.australisairline.demo.exception.EntityNotFoundException;

import com.australisairline.demo.repository.FlightRepository;
import com.australisairline.demo.service.FlightService;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@Validated


public class FlightServiceimpl implements FlightService{

    private final  FlightRepository flightRepository;

    public FlightServiceimpl(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }
    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight createFlight(FlightCreateDTO flightCreateDTO) {
        Flight flight= Flight.builder().date(flightCreateDTO.getDate()).of(flightCreateDTO.getOf()).to(flightCreateDTO.getTo()).plane(flightCreateDTO.getPlane()).build();
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(FlightUpdateDTO flightUpdateDTO, UUID id) {
        val toModifyFlightOptional = flightRepository.findById(id);
        if (toModifyFlightOptional.isPresent()) {
            val toModifyFlight = toModifyFlightOptional.get();
            toModifyFlight.setDate(flightUpdateDTO.getDate());
            toModifyFlight.setOf(flightUpdateDTO.getOf());
            toModifyFlight.setTo(flightUpdateDTO.getTo());
            return flightRepository.save(toModifyFlight);
        }
        throw new EntityNotFoundException(String.format("No flight found for id: %s", id));
    }

    @Override
    public List<Flight> getDoneFlights() {
        return flightRepository.findByDateBefore(new Date(System.currentTimeMillis()));
    }

    @Override
    public List<Flight> getNotDoneFlights() {
        return flightRepository.findByDateAfter(new Date(System.currentTimeMillis()));
    }

    @Override
    public void deleteFlight(UUID uuid) {
        if (flightRepository.existsById(uuid)) {
            flightRepository.deleteById(uuid);
            return;
        }
        throw new EntityNotFoundException(String.format("No flight found for id: %s", uuid));
    }

    @Override
    public Flight getFlightbyID(UUID uuid) {


            return flightRepository.findById(uuid) .orElseThrow(() -> new EntityNotFoundException(String.format("No flight found for id: %s", uuid)));

    }
}
