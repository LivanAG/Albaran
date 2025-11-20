package com.rams.albaran.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Price {
    private Integer id;
    private BigDecimal km;
    private BigDecimal servicePrice;
    private BigDecimal loadUnloadTime;
    private BigDecimal suburb;
    private BigDecimal national;
    private Boolean isActive;
}