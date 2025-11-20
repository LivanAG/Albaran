package com.rams.albaran.infraestructure.ports.out.jpa.adapter;

import com.rams.albaran.domain.model.Price;
import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.ports.out.OutGetPricePort;
import com.rams.albaran.infraestructure.ports.out.jpa.mapper.PriceEntityMapper;
import com.rams.albaran.infraestructure.ports.out.jpa.repository.PriceRepository;
import org.springframework.stereotype.Component;

@Component
public class PriceAdapter implements OutGetPricePort {

    private PriceRepository priceRepository;
    public PriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPriceByService(Service service) {
        if (service == null || service.getId() == null) {
            throw new IllegalArgumentException("El servicio no puede ser null");
        }

        return priceRepository
                .findByServiceEntityIdAndIsActiveTrue(service.getId())
                .map(PriceEntityMapper::entityToDomain)
                .orElseThrow(() ->
                        new RuntimeException("No existe un precio activo para el servicio con id: " + service.getId())
                );
    }
}
