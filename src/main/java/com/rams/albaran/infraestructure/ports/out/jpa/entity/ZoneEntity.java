package com.rams.albaran.infraestructure.ports.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "zone")
@Getter
@Setter
public class ZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String cp;
    private Integer suburb;
    private Integer kms;

}