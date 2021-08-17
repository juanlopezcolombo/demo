package com.australisairline.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;



@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Plane implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String manufacturer;
    private String model;
    private int capacity;
    @OneToOne(mappedBy = "plane")
    private Flight flight;
}
