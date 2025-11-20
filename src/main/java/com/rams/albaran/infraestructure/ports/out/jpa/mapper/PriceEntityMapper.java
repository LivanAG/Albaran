package com.rams.albaran.infraestructure.ports.out.jpa.mapper;

import com.rams.albaran.domain.model.Price;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.PriceEntity;

public class PriceEntityMapper {
    // ---------------------------------------
    // Entity → Domain
    // ---------------------------------------
    public static Price entityToDomain(PriceEntity entity) {
        if (entity == null) return null;

        Price price = new Price();
        price.setId(entity.getId());
        price.setServicePrice(entity.getServicePrice());
        price.setKm(entity.getKm());
        price.setLoadUnloadTime(entity.getLoadUnloadTime());
        price.setSuburb(entity.getSuburb());
        price.setNational(entity.getNational());
        price.setIsActive(entity.getIsActive());
        return price;

    }

}
