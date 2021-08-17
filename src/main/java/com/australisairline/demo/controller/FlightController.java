package com.australisairline.demo.controller;

import com.australisairline.demo.entity.Flight;
import com.australisairline.demo.model.FlightCreateDTO;
import com.australisairline.demo.model.FlightUpdateDTO;
import com.australisairline.demo.service.FlightService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllToDos() {
        val flights = flightService.getAllFlights();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @GetMapping("/done/")
    public ResponseEntity<List<Flight>> getDoneFlights() {
        val flights = flightService.getDoneFlights();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @GetMapping("/not-done/")
    public ResponseEntity<List<Flight>> getNotDoneFlights() {
        val flights = flightService.getNotDoneFlights();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlight(@PathVariable UUID id, @RequestParam(name = "fmt", defaultValue = "json") String format) {
        val flight = flightService.getFlightbyID(id);

                return ResponseEntity.ok(flight);


    }

    @PostMapping
    public ResponseEntity<Flight> postNewFlight(@RequestBody FlightCreateDTO flightCreateDTO) {
        val createdFlight = flightService.createFlight(flightCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody FlightUpdateDTO flightUpdateDTO, @PathVariable UUID id) {
        val updatedFlight = flightService.updateFlight(flightUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
