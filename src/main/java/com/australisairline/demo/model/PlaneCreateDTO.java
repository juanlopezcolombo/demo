package com.australisairline.demo.model;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaneCreateDTO {
    private String manufacturer;
    private String model;
    private int capacity;

}
