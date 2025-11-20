package com.rams.albaran.infraestructure.ports.out.jpa.adapter;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.out.OutGetZonePort;
import com.rams.albaran.infraestructure.ports.in.rest.specifications.ZoneSpecifications;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.mapper.ZoneMapperEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.repository.ZoneRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneAdapter implements OutGetZonePort {

    private final ZoneRepository zoneRepository;
    public ZoneAdapter(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone getZoneById(Integer id) {
        return zoneRepository.findById(id)
                .map(ZoneMapperEntity::entityToDomain)
                .orElseThrow(() ->
                        new RuntimeException("Zone no encontrada con id: " + id)
                );
    }

    @Override
    public List<Zone> getZoneListByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        return zoneRepository.findAllById(ids)
                .stream()
                .map(ZoneMapperEntity::entityToDomain)
                .toList();
    }

    @Override
    public List<Zone> findAllByFilters(String text) {

        Specification<ZoneEntity> spec = ZoneSpecifications.search(text);

        return zoneRepository.findAll(spec).stream()
                .map(ZoneMapperEntity::entityToDomain)
                .toList();
    }
}
