package com.australisairline.demo.repository;


import com.australisairline.demo.entity.Plane;
import lombok.*;
import com.australisairline.demo.DemoApplication;
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
public class PlaneRepositoryTest {
    @Autowired
    private PlaneRepository planeRepository;

    @Test
    void simplePlaneRepositoryTest() {

        assertTrue(planeRepository.findAll().isEmpty());


        String model= "boeing";
        String manufacturer= "Japan";
        int capacity=50;


        val nonPersistedPlane = Plane.builder()
                .model(model)
                .manufacturer(manufacturer)
                .capacity(50)
                .build();

        assertNull(nonPersistedPlane.getUuid());
        assertEquals(nonPersistedPlane.getCapacity(),capacity );
        assertEquals(nonPersistedPlane.getModel(),model );



        planeRepository.save(nonPersistedPlane);

        List<Plane> flights= planeRepository.findAll();

        assertFalse(flights.isEmpty());
        assertEquals(1, flights.size());

        val persistedFlight = flights.get(0);

        assertNotNull(persistedFlight.getUuid());
        assertEquals(persistedFlight.getModel(), model);

        assertEquals(persistedFlight.getCapacity(),capacity);
    }

}
