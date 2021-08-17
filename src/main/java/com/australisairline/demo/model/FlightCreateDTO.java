package com.australisairline.demo.model;
import com.australisairline.demo.entity.Plane;
import lombok.*;
import java.sql.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCreateDTO {
    private String of;
    private String to;
    private Date date;
    private Plane plane;
}
