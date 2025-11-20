package com.rams.albaran.infraestructure.ports.in.rest.mapper;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;

public class ServiceMapper {

    public static CatalogDto toCatalogDto(Service service) {
        CatalogDto dto = new CatalogDto();
        dto.setId(service.getId());
        dto.setName(service.getType());
        return dto;
    }
}
