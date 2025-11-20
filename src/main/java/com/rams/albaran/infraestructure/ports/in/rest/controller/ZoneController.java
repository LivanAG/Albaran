package com.rams.albaran.infraestructure.ports.in.rest.controller;

import com.rams.albaran.domain.ports.in.InGetZonePort;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.ZoneMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final InGetZonePort inGetZonePort;

    public ZoneController(InGetZonePort inGetZonePort) {
        this.inGetZonePort = inGetZonePort;
    }

    @GetMapping("/search")
    public List<CatalogDto> search(@RequestParam String query) {
        return inGetZonePort.findAllByFilters(query)
                .stream()
                .map(ZoneMapper::toCatalogDto)
                .toList();
    }
}
