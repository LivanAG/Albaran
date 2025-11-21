package com.rams.albaran.infraestructure.ports.out.jpa.mapper;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ServiceEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;

public class ServiceMapperEntity {
    // ---------------------------------------
    // Entity → Domain
    // ---------------------------------------
    public static Service entityToDomain(ServiceEntity entity) {
        if (entity == null) return null;

        Service service = new Service();
        service.setId(entity.getId());
        service.setType(entity.getType());

        return service;
    }

    // ---------------------------------------
    // Domain → Entity
    // ---------------------------------------
    public static ServiceEntity domainToEntity(Service domain) {
        if (domain == null) return null;

        ServiceEntity entity = new ServiceEntity();

        if (domain.getId() != null){
            entity.setId(domain.getId());
        }
        entity.setType(domain.getType());

        return entity;
    }
}
