package com.rams.albaran.infraestructure.ports.in.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DeliveryNoteRequestDto {
    private LocalDate date;
    private Integer serviceId;
    private Integer number;
    private List<Integer> zonesId;
    private Integer loadUnloadTimeCount;
    private Double totalAmount;
    private Boolean directPayment;
    private boolean national;
    private String comment;
    private Boolean isOutOfTime;

}
