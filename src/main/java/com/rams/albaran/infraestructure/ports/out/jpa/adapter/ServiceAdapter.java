package com.rams.albaran.infraestructure.ports.out.jpa.adapter;

import com.rams.albaran.domain.ports.out.OutGetServicePort;
import com.rams.albaran.domain.model.Service;
import com.rams.albaran.infraestructure.ports.in.rest.specifications.ServiceSpecifications;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ServiceEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.mapper.ServiceMapperEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.repository.ServiceRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceAdapter implements OutGetServicePort {

    private final ServiceRepository serviceRepository;

    public ServiceAdapter(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service getServiceById(Integer id) {
        return serviceRepository.findById(id)
                .map(ServiceMapperEntity::entityToDomain)
                .orElseThrow(() ->
                        new RuntimeException("Service no encontrado con id: " + id)
                );
    }

    @Override
    public List<Service> search(String query) {

        Specification<ServiceEntity> spec =
                ServiceSpecifications.typeContains(query);

        return serviceRepository.findAll(spec)
                .stream()
                .map(ServiceMapperEntity::entityToDomain)
                .toList();
    }

}
