package com.rams.albaran.infraestructure.ports.in.rest.specifications;


import com.rams.albaran.infraestructure.ports.out.jpa.entity.DeliveryNoteEntity;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;

public class DeliveryNoteSpecifications {

    public static Specification<DeliveryNoteEntity> hasNumber(Integer number) {
        return (root, query, cb) -> {
            if (number == null) return null;
            return cb.like(
                    root.get("number").as(String.class),
                    "%" + number + "%"
            );
        };
    }

    public static Specification<DeliveryNoteEntity> dateFrom(Date startDate) {
        return (root, query, cb) -> {
            if (startDate == null) return null;
            return cb.greaterThanOrEqualTo(root.get("date"), startDate);
        };
    }

    public static Specification<DeliveryNoteEntity> dateTo(Date endDate) {
        return (root, query, cb) -> {
            if (endDate == null) return null;
            return cb.lessThanOrEqualTo(root.get("date"), endDate);
        };
    }
}
