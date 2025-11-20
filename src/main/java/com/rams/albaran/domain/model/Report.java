package com.rams.albaran.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Report {

    private List<DeliveryNote> notes;

    // resumen por servicio
    private Map<String, ReportSummary> summaryByService;

    // total general
    private ReportSummary totalSummary;
}
