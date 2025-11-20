package com.rams.albaran.infraestructure.ports.in.rest.mapper;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;

public class ZoneMapper {

    public static CatalogDto toCatalogDto(Zone zone) {
        CatalogDto dto = new CatalogDto();
        dto.setId(zone.getId());
        if (zone.getName() == null || zone.getName().trim().isEmpty()) {
            dto.setName(zone.getCp());
        }else{
            dto.setName(zone.getName());
        }

        return dto;
    }
}
