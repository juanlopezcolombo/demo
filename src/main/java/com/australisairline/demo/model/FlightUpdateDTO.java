package com.australisairline.demo.model;

import com.australisairline.demo.entity.Plane;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FlightUpdateDTO {
    private String of;
    private String to;
    private java.sql.Date date;
    private Plane plane;
}
