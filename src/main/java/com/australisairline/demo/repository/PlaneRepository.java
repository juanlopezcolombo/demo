package com.australisairline.demo.repository;
import com.australisairline.demo.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface PlaneRepository extends JpaRepository<Plane, UUID>{
}
