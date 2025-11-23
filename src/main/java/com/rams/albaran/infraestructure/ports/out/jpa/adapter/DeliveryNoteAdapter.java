package com.rams.albaran.infraestructure.ports.out.jpa.adapter;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.ports.out.OutDeleteDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutDeliveryNotePort;
import com.rams.albaran.infraestructure.ports.in.rest.mapper.DeliveryNoteMapper;
import com.rams.albaran.infraestructure.ports.in.rest.specifications.DeliveryNoteSpecifications;
import com.rams.albaran.infraestructure.ports.out.jpa.entity.DeliveryNoteEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.mapper.DeliveryNoteMapperEntity;
import com.rams.albaran.infraestructure.ports.out.jpa.repository.DeliveryNoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryNoteAdapter implements OutDeliveryNotePort, OutDeleteDeliveryNotePort {
    DeliveryNoteRepository deliveryNoteRepository;
    public DeliveryNoteAdapter(DeliveryNoteRepository deliveryNoteRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    @Override
    public DeliveryNote save(DeliveryNote deliveryNote) {
        deliveryNoteRepository.save(DeliveryNoteMapperEntity.toEntity(deliveryNote));
        return deliveryNote;
    }

    @Override
    public Page<DeliveryNote> findPaged(Integer number, Date start, Date end, int page, int size) {

        List<Specification<DeliveryNoteEntity>> specs = new ArrayList<>();

        if (number != null) {
            specs.add(DeliveryNoteSpecifications.hasNumber(number));
        }

        if (start != null) {
            specs.add(DeliveryNoteSpecifications.dateFrom(start));
        }

        if (end != null) {
            specs.add(DeliveryNoteSpecifications.dateTo(end));
        }

        Specification<DeliveryNoteEntity> spec = Specification.allOf(specs);

        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());

        return deliveryNoteRepository.findAll(spec, pageable)
                .map(DeliveryNoteMapperEntity::toDomain);
    }

    @Override
    public DeliveryNote getDeliveryNoteById(Integer id) {
        return DeliveryNoteMapperEntity.toDomain(deliveryNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Note not found with id: " + id)));
    }

    @Override
    public List<DeliveryNote> findNotesByDates(Date start, Date end) {
        List<Specification<DeliveryNoteEntity>> specs = new ArrayList<>();

        if (start != null) {
            specs.add(DeliveryNoteSpecifications.dateFrom(start));
        }

        if (end != null) {
            specs.add(DeliveryNoteSpecifications.dateTo(end));
        }
        Specification<DeliveryNoteEntity> spec = Specification.allOf(specs);


        return deliveryNoteRepository.findAll(spec).stream().map(DeliveryNoteMapperEntity::toDomain).toList();
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        deliveryNoteRepository.deleteAllByIdIn(ids);
    }
}
