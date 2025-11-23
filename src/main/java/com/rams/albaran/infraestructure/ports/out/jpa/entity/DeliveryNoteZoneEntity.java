package com.rams.albaran.infraestructure.ports.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_note_zone")
@Getter
@Setter
public class DeliveryNoteZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNoteEntity deliveryNote;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private ZoneEntity zone;


}