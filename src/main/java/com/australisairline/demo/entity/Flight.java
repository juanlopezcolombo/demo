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
public class Flight implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;


    private String of;
    private String to;
    private java.sql.Date date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plane_id", referencedColumnName = "uuid")
    private Plane plane;


}
