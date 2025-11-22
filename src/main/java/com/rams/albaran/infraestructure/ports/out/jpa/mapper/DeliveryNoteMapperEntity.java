package com.rams.albaran.infraestructure.ports.out.jpa.mapper;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Zone;

import com.rams.albaran.infraestructure.ports.out.jpa.entity.DeliveryNoteEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryNoteMapperEntity {

    // ---------------------------------------------------------
    // ENTITY → DOMAIN
    // ---------------------------------------------------------
    public static DeliveryNote toDomain(DeliveryNoteEntity entity) {
        if (entity == null) return null;

        DeliveryNote dn = new DeliveryNote();
        dn.setId(entity.getId());
        dn.setNumber(entity.getNumber());
        dn.setDirectPayment(entity.getDirectPayment());
        dn.setDate(entity.getDate()); // LocalDate directly

        dn.setService(ServiceMapperEntity.entityToDomain(entity.getServiceEntity()));

        List<Zone> zones = entity.getZoneEntities() != null
                ? entity.getZoneEntities().stream()
                .map(ZoneMapperEntity::entityToDomain)
                .collect(Collectors.toList())
                : List.of();

        dn.setZones(zones);

        dn.setServiceCount(entity.getServiceCount());
        dn.setSuburbCount(entity.getSuburbCount());
        dn.setLoadUnloadTimeCount(entity.getLoadUnloadTimeCount());
        dn.setKmsCount(entity.getKmsCount());

        dn.setTotalAmountService(entity.getTotalAmountService());
        dn.setTotalAmountSuburb(entity.getTotalAmountSuburb());
        dn.setTotalAmountloadUnloadTime(entity.getTotalAmountloadUnloadTime());
        dn.setTotalAmountKms(entity.getTotalAmountKms());
        dn.setTotalDeliveryNoteAmount(entity.getTotalDeliveryNoteAmount());
        dn.setIsNational(entity.getIsNational());
        if (dn.getIsNational()){
            dn.setTotalNational(entity.getTotalNational());
        }else{
            dn.setTotalNational(null);
        }

        dn.setIsOutOfTime(entity.getIsOutOfTime());
        dn.setComment(entity.getComment());
        return dn;
    }

    // ---------------------------------------------------------
    // DOMAIN → ENTITY
    // ---------------------------------------------------------
    public static DeliveryNoteEntity toEntity(DeliveryNote domain) {
        if (domain == null) return null;

        DeliveryNoteEntity entity = new DeliveryNoteEntity();
        entity.setId(domain.getId());
        entity.setNumber(domain.getNumber());
        entity.setDirectPayment(domain.getDirectPayment());
        entity.setDate(domain.getDate()); // LocalDate directly

        entity.setServiceEntity(ServiceMapperEntity.domainToEntity(domain.getService()));

        List<ZoneEntity> zoneEntities = domain.getZones() != null
                ? domain.getZones().stream()
                .map(ZoneMapperEntity::domainToEntity)
                .collect(Collectors.toList())
                : List.of();

        entity.setZoneEntities(zoneEntities);

        entity.setServiceCount(domain.getServiceCount());
        entity.setSuburbCount(domain.getSuburbCount());
        entity.setLoadUnloadTimeCount(domain.getLoadUnloadTimeCount());
        entity.setKmsCount(domain.getKmsCount());

        entity.setTotalAmountService(domain.getTotalAmountService());
        entity.setTotalAmountSuburb(domain.getTotalAmountSuburb());
        entity.setTotalAmountloadUnloadTime(domain.getTotalAmountloadUnloadTime());
        entity.setTotalAmountKms(domain.getTotalAmountKms());
        entity.setTotalDeliveryNoteAmount(domain.getTotalDeliveryNoteAmount());
        entity.setIsNational(domain.getIsNational());
        if (entity.getIsNational()){
            entity.setTotalNational(domain.getTotalNational());
        }else{
            entity.setTotalNational(null);
        }

        entity.setIsOutOfTime(domain.getIsOutOfTime());
        entity.setComment(domain.getComment());
        return entity;
    }
}
