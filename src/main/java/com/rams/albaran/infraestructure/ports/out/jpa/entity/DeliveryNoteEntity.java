package com.rams.albaran.infraestructure.ports.out.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "delivery_note")
@Getter
@Setter
public class DeliveryNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity serviceEntity;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryNoteZoneEntity> deliveryNoteZones;

    private Integer number;

    private Integer serviceCount;
    private Integer suburbCount;
    private Integer loadUnloadTimeCount;
    private Integer kmsCount;


    private Boolean directPayment = false;

    @Column(length = 1000)
    private String comment;

    private Boolean isOutOfTime = false;
    private Boolean isNational = false;
    // ---------------------------------------
    // CAMPOS MONETARIOS (BigDecimal)
    // ---------------------------------------

    @Column(precision = 10, scale = 2)
    private BigDecimal totalNational;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmountService;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmountSuburb;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmountloadUnloadTime;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmountKms;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalDeliveryNoteAmount;

}
