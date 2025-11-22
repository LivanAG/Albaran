package com.rams.albaran.infraestructure.ports.in.rest.mapper;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.infraestructure.ports.in.rest.dto.CatalogDto;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteRequestDto;
import com.rams.albaran.infraestructure.ports.in.rest.dto.DeliveryNoteResponseDto;

import java.math.BigDecimal;

import java.util.List;

public class DeliveryNoteMapper {

    public static DeliveryNoteResponseDto toDto(DeliveryNote domain) {
        DeliveryNoteResponseDto dto = new DeliveryNoteResponseDto();
        dto.setId(domain.getId());
        dto.setDirectPayment(domain.getDirectPayment());
        dto.setNumber(domain.getNumber());
        dto.setDate(domain.getDate());

        // SAFE SERVICE ACCESS
        if (domain.getService() != null) {
            dto.setServiceName(domain.getService().getType());
            dto.setServiceId(domain.getService().getId());
        } else {
            dto.setServiceName("PAGO DIRECTO");
            dto.setServiceId(null);
        }

        dto.setZones(domain.getZones() != null
                ? domain.getZones().stream().map(ZoneMapper::toCatalogDto).toList()
                : List.of()
        );

        dto.setServiceCount(domain.getServiceCount());
        dto.setSuburbCount(domain.getSuburbCount());
        dto.setLoadUnloadTimeCount(domain.getLoadUnloadTimeCount());
        dto.setKmsCount(domain.getKmsCount());

        dto.setTotalAmountService(domain.getTotalAmountService());
        dto.setTotalAmountKms(domain.getTotalAmountKms());
        dto.setTotalAmountSuburb(domain.getTotalAmountSuburb());
        dto.setTotalAmountloadUnloadTime(domain.getTotalAmountloadUnloadTime());

        dto.setTotalAmount(domain.getTotalDeliveryNoteAmount());
        dto.setNational(domain.getIsNational());

        dto.setComment(domain.getComment());
        dto.setIsOutOfTime(domain.getIsOutOfTime());
        if (dto.isNational()) {
            dto.setTotalNational(domain.getTotalNational());
        }

        return dto;
    }
    public static DeliveryNote dtoToDomain(DeliveryNoteRequestDto dto) {
        DeliveryNote deliveryNote = new DeliveryNote();


        deliveryNote.setDate(dto.getDate());
        deliveryNote.setDirectPayment(dto.getDirectPayment());
        if (dto.getServiceId() != null) {
            Service service = new Service();
            service.setId(dto.getServiceId());
            deliveryNote.setService(service);
        } else {
            deliveryNote.setService(null);
        }

        if(!dto.getZonesId().isEmpty()){
            List<Zone> zoneList = dto.getZonesId().stream()
                    .map(z -> {
                        Zone zone = new Zone();
                        zone.setId(z);
                        return zone;
                    })
                    .toList();
            deliveryNote.setZones(zoneList);
        }



        if (dto.getNumber() != null) {
            deliveryNote.setNumber(dto.getNumber());
        }

        deliveryNote.setComment(dto.getComment());
        deliveryNote.setIsOutOfTime(dto.getIsOutOfTime());

        // Valores que vienen del DTO
        deliveryNote.setLoadUnloadTimeCount(dto.getLoadUnloadTimeCount() != null ? dto.getLoadUnloadTimeCount() : 0);

        BigDecimal totalAmount = dto.getTotalAmount() != null
                ? BigDecimal.valueOf(dto.getTotalAmount())
                : BigDecimal.ZERO;

        deliveryNote.setTotalDeliveryNoteAmount(totalAmount);

        deliveryNote.setIsNational(dto.isNational());
        return deliveryNote;
    }
}