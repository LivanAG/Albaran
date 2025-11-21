package com.rams.albaran.infraestructure.ports.in.rest.controller;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.in.InCreateAndUpdateServicePort;
import com.rams.albaran.domain.ports.in.InGetServicePort;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.ServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final InGetServicePort port;
    private final InCreateAndUpdateServicePort createService;
    public ServiceController(InGetServicePort port, InCreateAndUpdateServicePort createService) {
        this.port = port;
        this.createService = createService;
    }

    @GetMapping("/search")
    public List<CatalogDto> search(@RequestParam String query) {
        return port.search(query)
                .stream()
                .map(ServiceMapper::toCatalogDto)
                .toList();
    }


    @GetMapping("/paged")
    public Page<Service> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String query
    ) {

        return port.getPaged(query, page, size);

    }



    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Service request) {


        if (request.getType() == null || request.getType().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe introducir el tipo de servicio");
        }

        request.setId(null);

        createService.create(request);
        return ResponseEntity.ok("Creación de servicio correcta.");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Service request) {


        if (request.getType() == null || request.getType().isEmpty()) {
            return ResponseEntity.badRequest().body("Debe introducir el tipo de servicio");
        }

        request.setId(null);
        createService.update(id,request);
        return ResponseEntity.ok("Edicion de servicio correcta.");
    }
}