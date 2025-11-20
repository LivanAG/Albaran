package com.rams.albaran.infraestructure.ports.in.rest.controller;


import com.rams.albaran.domain.model.Report;
import com.rams.albaran.domain.ports.in.*;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteRequestDto;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteResponseDto;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.DeliveryNoteMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/delivery-note")
public class DeliveryNoteController {

    private final InCreateDeliveryNotePort inCreateDeliveryNotePort;
    private final InGetDeliveryNotePort inGetDeliveryNotePort;
    private final InUpdateDeliveryNotePort inUpdateDeliveryNotePort;
    private final InDeleteDeliveryNotePort inDeleteDeliveryNotePort;
    private final InGetReportPort inGetReportPort;
    public DeliveryNoteController(InCreateDeliveryNotePort inCreateDeliveryNotePort, InGetDeliveryNotePort inGetDeliveryNotePort, InUpdateDeliveryNotePort inUpdateDeliveryNotePort, InDeleteDeliveryNotePort inDeleteDeliveryNotePort, InGetReportPort inGetReportPort) {
        this.inCreateDeliveryNotePort = inCreateDeliveryNotePort;
        this.inGetDeliveryNotePort = inGetDeliveryNotePort;
        this.inUpdateDeliveryNotePort = inUpdateDeliveryNotePort;
        this.inDeleteDeliveryNotePort = inDeleteDeliveryNotePort;
        this.inGetReportPort = inGetReportPort;
    }


    @GetMapping("/paged")
    public Page<DeliveryNoteResponseDto> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer number,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {

        Date start = (startDate != null) ? Date.valueOf(startDate) : null;
        Date end   = (endDate != null) ? Date.valueOf(endDate) : null;

        return inGetDeliveryNotePort.getPaged(number, start, end, page, size).map(DeliveryNoteMapper::toDto);

    }

    @GetMapping("/report")
    public ResponseEntity<Report> generateReport(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        Date start = (startDate != null) ? Date.valueOf(startDate) : null;
        Date end   = (endDate != null) ? Date.valueOf(endDate) : null;

        return ResponseEntity.ok(inGetReportPort.generateReport(start, end));
    }


    @GetMapping("/{id}")
    public DeliveryNoteResponseDto getDetail(@PathVariable Integer id) {
        return DeliveryNoteMapper.toDto(inGetDeliveryNotePort.getDeliveryNoteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody DeliveryNoteRequestDto dto) {



        // -------------------------
        // VALIDACIÓN: Fecha obligatoria siempre
        // -------------------------
        if (dto.getDate() == null) {
            return ResponseEntity.badRequest().body("La fecha es obligatoria.");
        }

        // ============================
        // MODO PAGO DIRECTO
        // ============================
        if (Boolean.TRUE.equals(dto.getDirectPayment())) {

            // number obligatorio
            if (dto.getNumber() == null) {
                return ResponseEntity.badRequest().body("El número es obligatorio en pago directo.");
            }

            // totalAmount obligatorio
            if (dto.getTotalAmount() == null || dto.getTotalAmount() <= 0) {
                return ResponseEntity.badRequest().body("El totalAmount es obligatorio en pago directo.");
            }



        } else {
            // ============================
            // MODO NORMAL (NO directo)
            // ============================

            // number obligatorio
            if (dto.getNumber() == null) {
                return ResponseEntity.badRequest().body("El número es obligatorio.");
            }

            // serviceId obligatorio
            if (dto.getServiceId() == null) {
                return ResponseEntity.badRequest().body("El servicio es obligatorio.");
            }

            // zonas obligatorias
            if (dto.getZonesId() == null || dto.getZonesId().isEmpty()) {
                return ResponseEntity.badRequest().body("Debe seleccionar al menos una zona.");
            }

            // loadUnloadTimeCount obligatorio
            if (dto.getLoadUnloadTimeCount() == null) {
                return ResponseEntity.badRequest().body("El tiempo de carga/descarga es obligatorio.");
            }

            // totalAmount debe venir null
            if (dto.getTotalAmount() != null) {
                return ResponseEntity.badRequest()
                        .body("En modo normal totalAmount debe ser null.");
            }
        }
        inUpdateDeliveryNotePort.update(id, DeliveryNoteMapper.dtoToDomain(dto));
        return ResponseEntity.ok("Albarán actualizado correctamente.");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDN(@RequestBody DeliveryNoteRequestDto dto) {

        // -------------------------
        // VALIDACIÓN: Fecha obligatoria siempre
        // -------------------------
        if (dto.getDate() == null) {
            return ResponseEntity.badRequest().body("La fecha es obligatoria.");
        }

        // ============================
        // MODO PAGO DIRECTO
        // ============================
        if (Boolean.TRUE.equals(dto.getDirectPayment())) {

            // number obligatorio
            if (dto.getNumber() == null) {
                return ResponseEntity.badRequest().body("El número es obligatorio en pago directo.");
            }

            // totalAmount obligatorio
            if (dto.getTotalAmount() == null || dto.getTotalAmount() <= 0) {
                return ResponseEntity.badRequest().body("El totalAmount es obligatorio en pago directo.");
            }



        } else {
            // ============================
            // MODO NORMAL (NO directo)
            // ============================

            // number obligatorio
            if (dto.getNumber() == null) {
                return ResponseEntity.badRequest().body("El número es obligatorio.");
            }

            // serviceId obligatorio
            if (dto.getServiceId() == null) {
                return ResponseEntity.badRequest().body("El servicio es obligatorio.");
            }

            // zonas obligatorias
            if (dto.getZonesId() == null || dto.getZonesId().isEmpty()) {
                return ResponseEntity.badRequest().body("Debe seleccionar al menos una zona.");
            }

            // loadUnloadTimeCount obligatorio
            if (dto.getLoadUnloadTimeCount() == null) {
                return ResponseEntity.badRequest().body("El tiempo de carga/descarga es obligatorio.");
            }

            // totalAmount debe venir null
            if (dto.getTotalAmount() != null) {
                return ResponseEntity.badRequest()
                        .body("En modo normal totalAmount debe ser null.");
            }
        }

        // ***** Si pasa todas las validaciones *****
        inCreateDeliveryNotePort.create(DeliveryNoteMapper.dtoToDomain(dto));
        return ResponseEntity.ok("Creación del albarán correcta.");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteNotes(@RequestBody List<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return ResponseEntity.badRequest().body("Debe proporcionar al menos un ID.");
        }

        inDeleteDeliveryNotePort.deleteByIds(ids);

        return ResponseEntity.ok("Albaranes eliminados correctamente.");
    }

}
