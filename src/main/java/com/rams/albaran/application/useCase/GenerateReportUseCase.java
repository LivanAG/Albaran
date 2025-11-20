package com.rams.albaran.application.useCase;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Report;
import com.rams.albaran.domain.model.ReportSummary;
import com.rams.albaran.domain.ports.in.InGetReportPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenerateReportUseCase implements InGetReportPort {

    private final GetDeliveryNoteUseCase getDeliveryNoteUseCase;

    public GenerateReportUseCase(GetDeliveryNoteUseCase getDeliveryNoteUseCase) {
        this.getDeliveryNoteUseCase = getDeliveryNoteUseCase;
    }

    @Override
    public Report generateReport(Date start, Date end) {

        List<DeliveryNote> list = getDeliveryNoteUseCase.getNotesByDates(start, end);

        Map<String, ReportSummary> summaryMap = new HashMap<>();
        ReportSummary globalTotal = new ReportSummary();

        for (DeliveryNote dn : list) {

            String serviceName;

            // Determinar tipo de fila
            if (Boolean.TRUE.equals(dn.getDirectPayment())) {
                serviceName = "PAGO DIRECTO";
            } else {
                serviceName = (dn.getService() != null && dn.getService().getType() != null)
                        ? dn.getService().getType()
                        : "SIN SERVICIO";
            }

            // Crear entry si no existe
            summaryMap.putIfAbsent(serviceName, new ReportSummary());
            ReportSummary sum = summaryMap.get(serviceName);

            // Valores seguros
            int serviceCount = dn.getServiceCount() != null ? dn.getServiceCount() : 0;
            int suburbCount = dn.getSuburbCount() != null ? dn.getSuburbCount() : 0;
            int kmsCount = dn.getKmsCount() != null ? dn.getKmsCount() : 0;
            int loadCount = dn.getLoadUnloadTimeCount() != null ? dn.getLoadUnloadTimeCount() : 0;
            BigDecimal amount = dn.getTotalDeliveryNoteAmount() != null ? dn.getTotalDeliveryNoteAmount() : BigDecimal.ZERO;

            // Contabilizar servicios directos
            if (Boolean.TRUE.equals(dn.getDirectPayment())) {
                // contar como directo
                sum.setTotalDirectCount(sum.getTotalDirectCount() + 1);
                globalTotal.setTotalDirectCount(globalTotal.getTotalDirectCount() + 1);

                // también es un servicio
                sum.setTotalServiceCount(sum.getTotalServiceCount() + 1);
                globalTotal.setTotalServiceCount(globalTotal.getTotalServiceCount() + 1);
            }

            // Acumular valores normales
            sum.setTotalServiceCount(sum.getTotalServiceCount() + serviceCount);
            sum.setTotalSuburbs(sum.getTotalSuburbs() + suburbCount);
            sum.setTotalKms(sum.getTotalKms() + kmsCount);
            sum.setTotalLoadUnloadTime(sum.getTotalLoadUnloadTime() + loadCount);
            sum.setTotalAmount(sum.getTotalAmount().add(amount));

            // Conteo nacional + importes nacionales
            if (Boolean.TRUE.equals(dn.getIsNational())) {
                sum.setTotalNationalCount(sum.getTotalNationalCount() + 1);
                globalTotal.setTotalNationalCount(globalTotal.getTotalNationalCount() + 1);

                sum.setTotalNationalAmount(
                        sum.getTotalNationalAmount().add(dn.getTotalNational() != null ? dn.getTotalNational() : BigDecimal.ZERO));

                globalTotal.setTotalNationalAmount(
                        globalTotal.getTotalNationalAmount().add(dn.getTotalNational() != null ? dn.getTotalNational() : BigDecimal.ZERO));
            }

            // Acumular importes desglosados por tipo
            sum.setTotalServiceAmount(sum.getTotalServiceAmount()
                    .add(dn.getTotalAmountService() != null ? dn.getTotalAmountService() : BigDecimal.ZERO));

            sum.setTotalSuburbAmount(sum.getTotalSuburbAmount()
                    .add(dn.getTotalAmountSuburb() != null ? dn.getTotalAmountSuburb() : BigDecimal.ZERO));

            sum.setTotalKmsAmount(sum.getTotalKmsAmount()
                    .add(dn.getTotalAmountKms() != null ? dn.getTotalAmountKms() : BigDecimal.ZERO));

            sum.setTotalLoadAmount(sum.getTotalLoadAmount()
                    .add(dn.getTotalAmountloadUnloadTime() != null ? dn.getTotalAmountloadUnloadTime() : BigDecimal.ZERO));

            // Global desglosado
            globalTotal.setTotalServiceCount(globalTotal.getTotalServiceCount() + serviceCount);
            globalTotal.setTotalSuburbs(globalTotal.getTotalSuburbs() + suburbCount);
            globalTotal.setTotalKms(globalTotal.getTotalKms() + kmsCount);
            globalTotal.setTotalLoadUnloadTime(globalTotal.getTotalLoadUnloadTime() + loadCount);
            globalTotal.setTotalAmount(globalTotal.getTotalAmount().add(amount));

            // *** Faltaba esto para que el importe global de C/D no sea 0 ***
            globalTotal.setTotalLoadAmount(globalTotal.getTotalLoadAmount()
                    .add(dn.getTotalAmountloadUnloadTime() != null ? dn.getTotalAmountloadUnloadTime() : BigDecimal.ZERO));
        }

        // Construir respuesta final
        Report report = new Report();
        report.setNotes(list);
        report.setSummaryByService(summaryMap);
        report.setTotalSummary(globalTotal);

        return report;
    }
}
