package com.australisairline.demo.service;
import com.australisairline.demo.entity.Flight;
import com.australisairline.demo.entity.Plane;
import com.australisairline.demo.exception.EntityNotFoundException;
import com.australisairline.demo.model.FlightCreateDTO;
import com.australisairline.demo.model.FlightUpdateDTO;
import com.australisairline.demo.model.PlaneCreateDTO;
import com.australisairline.demo.service.FlightService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    void happyPathTest() {
        String str="2015-03-31";

        assertTrue(flightService.getAllFlights().isEmpty());
        Date date=Date.valueOf(str);
        FlightCreateDTO flightCreateDTO = FlightCreateDTO.builder()
                .to("mdq")
                .of("bsas")
                .date(date)
                .build();
        Flight savedFlight = flightService.createFlight(flightCreateDTO);

        val myFlights = flightService.getAllFlights();
        assertFalse(myFlights.isEmpty());
        assertEquals(1, myFlights.size());
        val myFlight = myFlights.get(0);
        assertEquals(myFlight, savedFlight);
        assertEquals(1,flightService.getDoneFlights().size());
        assertEquals(0, flightService.getNotDoneFlights().size());


        FlightUpdateDTO my_updated_to = FlightUpdateDTO.builder().to("mdp").build();
        val savedUpdatedFlight = flightService.updateFlight(my_updated_to, myFlight.getUuid());

        val myUpdatedFlights = flightService.getAllFlights();
        assertFalse(myUpdatedFlights.isEmpty());
        assertEquals(1, myUpdatedFlights.size());

        val myUpdatedFlight = myUpdatedFlights.get(0);
        assertEquals(myUpdatedFlight, savedUpdatedFlight);

        flightService.deleteFlight(myUpdatedFlight.getUuid());
        assertTrue(flightService.getAllFlights().isEmpty());
    }

    @Test
    void exceptionTest() {
        assertThrows(EntityNotFoundException.class, () -> flightService.deleteFlight(UUID.randomUUID()));
    }}





