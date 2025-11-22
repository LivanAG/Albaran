package com.rams.albaran.infraestructure.ports.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class DeliveryNoteResponseDto {
    private Integer id;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String serviceName;
    private Integer serviceId;
    private List<CatalogDto> zones;

    private Integer serviceCount;
    private Integer suburbCount;
    private Integer loadUnloadTimeCount;
    private Integer kmsCount;
    private BigDecimal totalAmountService;
    private BigDecimal totalAmountSuburb;
    private BigDecimal totalAmountloadUnloadTime;
    private BigDecimal totalAmountKms;
    private Boolean directPayment;
    private BigDecimal totalAmount;
    private BigDecimal totalNational;

    private boolean isNational;
    private String comment;
    private Boolean isOutOfTime;

}
