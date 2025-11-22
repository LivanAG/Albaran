package com.rams.albaran.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ReportSummary {

    private Integer totalServiceCount = 0;
    private Integer totalSuburbs = 0;
    private Integer totalKms = 0;
    private Integer totalLoadUnloadTime = 0;

    private int totalNationalCount = 0;
    private int totalDirectCount = 0;

    // NUEVOS CAMPOS DE IMPORTE DESGLOSADO
    private BigDecimal totalServiceAmount = BigDecimal.ZERO;
    private BigDecimal totalSuburbAmount = BigDecimal.ZERO;
    private BigDecimal totalKmsAmount = BigDecimal.ZERO;
    private BigDecimal totalLoadAmount = BigDecimal.ZERO;
    private BigDecimal totalNationalAmount = BigDecimal.ZERO;
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // 🆕 NUEVOS CAMPOS
    private int deliveryNoteCount = 0;
    private BigDecimal totalWithIVA = BigDecimal.ZERO;
}

