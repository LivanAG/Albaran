package com.rams.albaran.infraestructure.ports.in.rest.controller;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.in.InCreateAndUpdateZonePort;
import com.rams.albaran.domain.ports.in.InGetZonePort;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteRequestDto;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteResponseDto;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.DeliveryNoteMapper;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.ZoneMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final InGetZonePort inGetZonePort;
    private final InCreateAndUpdateZonePort inCreateAndUpdateZonePort;
    public ZoneController(InGetZonePort inGetZonePort, InCreateAndUpdateZonePort inCreateAndUpdateZonePort) {
        this.inGetZonePort = inGetZonePort;
        this.inCreateAndUpdateZonePort = inCreateAndUpdateZonePort;
    }

    @GetMapping("/search")
    public List<CatalogDto> search(@RequestParam String query) {
        return inGetZonePort.findAllByFilters(query)
                .stream()
                .map(ZoneMapper::toCatalogDto)
                .toList();
    }

    @GetMapping("/paged")
    public Page<Zone> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String query
    ) {

        return inGetZonePort.getPaged(query, page, size);

    }



    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Zone zoneRequest) {


        if ((zoneRequest.getCp() == null || zoneRequest.getCp().isEmpty()) && (zoneRequest.getName() == null|| zoneRequest.getName().isEmpty())) {
            return ResponseEntity.badRequest().body("Debe introducir un CP o un Nombre al menos");
        }


        if (zoneRequest.getKms() == null) {
            zoneRequest.setKms(0);
        }

        if (zoneRequest.getSuburb() == null) {
            zoneRequest.setSuburb(0);
        }

        zoneRequest.setId(null);

        inCreateAndUpdateZonePort.create(zoneRequest);
        return ResponseEntity.ok("Creación de la zona correcta.");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Zone zoneRequest) {


        if ((zoneRequest.getCp() == null || zoneRequest.getCp().isEmpty()) && (zoneRequest.getName() == null|| zoneRequest.getName().isEmpty())) {
            return ResponseEntity.badRequest().body("Debe introducir un CP o un Nombre al menos");
        }


        if (zoneRequest.getKms() == null) {
            zoneRequest.setKms(0);
        }

        if (zoneRequest.getSuburb() == null) {
            zoneRequest.setSuburb(0);
        }

        zoneRequest.setId(null);

        inCreateAndUpdateZonePort.update(id,zoneRequest);
        return ResponseEntity.ok("Edicion de la zona correcta.");
    }
}
