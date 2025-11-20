package com.rams.albaran.domain.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DeliveryNote {
    private Integer id;
    private Integer number;
    private LocalDate date;
    private Service service;
    private List<Zone> zones;
    private Integer serviceCount;
    private Integer suburbCount;
    private Integer loadUnloadTimeCount;
    private Integer kmsCount;
    private BigDecimal totalAmountService;
    private BigDecimal totalAmountSuburb;
    private BigDecimal totalAmountloadUnloadTime;
    private BigDecimal totalAmountKms;
    private BigDecimal totalDeliveryNoteAmount;
    private BigDecimal totalNational;
    private Boolean directPayment;
    private Boolean isNational;
}