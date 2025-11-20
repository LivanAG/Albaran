package com.rams.albaran.infraestructure.ports.in.rest.controller;

import com.rams.albaran.domain.ports.in.InGetServicePort;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.ServiceMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final InGetServicePort port;

    public ServiceController(InGetServicePort port) {
        this.port = port;
    }

    @GetMapping("/search")
    public List<CatalogDto> search(@RequestParam String query) {
        return port.search(query)
                .stream()
                .map(ServiceMapper::toCatalogDto)
                .toList();
    }
}