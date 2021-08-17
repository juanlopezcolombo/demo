package com.australisairline.demo.service;
import com.australisairline.demo.entity.Plane;
import com.australisairline.demo.model.PlaneCreateDTO;
import com.australisairline.demo.model.PlaneUpdateDTO;

import java.util.List;
import java.util.UUID;
public interface PlaneService {
    List<Plane> getAllPlanes();

    Plane createPlane(PlaneCreateDTO planeCreateDTO);

    Plane updatePlane(PlaneUpdateDTO planeUpdateDTO, UUID id);

    void deletePlane(UUID uuid);

    Plane getPlanebyID(UUID id);
}
