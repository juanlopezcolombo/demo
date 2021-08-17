package com.australisairline.demo.repository;
import lombok.*;
import com.australisairline.demo.DemoApplication;
import com.australisairline.demo.entity.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureWebClient
@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import({DemoApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;

    @Test
    void simpleFlightRepositoryTest() {

        assertTrue(flightRepository.findAll().isEmpty());


        String of= "bsas";
        String to= "mdq";



        val nonPersistedFlight = Flight.builder()
                .of("bsas")
                .to("mdq")
                .build();

        assertNull(nonPersistedFlight.getUuid());
        assertEquals(nonPersistedFlight.getOf(),of );
        assertEquals(nonPersistedFlight.getTo(),to );



        flightRepository.save(nonPersistedFlight);

        List<Flight> flights= flightRepository.findAll();

        assertFalse(flights.isEmpty());
        assertEquals(1, flights.size());

        val persistedFlight = flights.get(0);

        assertNotNull(persistedFlight.getUuid());
        assertEquals(persistedFlight.getOf(), of);

        assertEquals(persistedFlight.getTo(),to);
    }

}
