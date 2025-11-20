package com.rams.albaran.infraestructure.ports.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Getter
@Setter
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity serviceEntity;

    // -------------------------------
    // CAMPOS MONETARIOS → BIGDECIMAL
    // -------------------------------

    @Column(precision = 10, scale = 2)
    private BigDecimal servicePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal km;

    @Column(precision = 10, scale = 2)
    private BigDecimal loadUnloadTime;

    @Column(precision = 10, scale = 2)
    private BigDecimal suburb;

    @Column(precision = 10, scale = 2)
    private BigDecimal national;

    private Boolean isActive;
}
