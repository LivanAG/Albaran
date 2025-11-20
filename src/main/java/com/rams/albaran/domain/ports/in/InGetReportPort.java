package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Report;

import java.sql.Date;

public interface InGetReportPort {

    Report generateReport(Date start, Date end);
}
