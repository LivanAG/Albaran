package com.rams.albaran.infraestructure.ports.out.jpa.mapper;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;

public class ZoneMapperEntity {


    public static Zone entityToDomain(ZoneEntity entity) {
        if (entity == null) return null;

        Zone zone = new Zone();
        zone.setId(entity.getId());
        zone.setName(entity.getName());
        zone.setCp(entity.getCp());
        zone.setSuburb(entity.getSuburb());
        zone.setKms(entity.getKms());

        return zone;
    }

    // ---------------------------------------
    // Domain → Entity
    // ---------------------------------------
    public static ZoneEntity domainToEntity(Zone domain) {
        if (domain == null) return null;

        ZoneEntity entity = new ZoneEntity();
        if (domain.getId() != null){
            entity.setId(domain.getId());
        }

        entity.setName(domain.getName());
        entity.setCp(domain.getCp());
        entity.setSuburb(domain.getSuburb());
        entity.setKms(domain.getKms());

        return entity;
    }
}
