package com.rams.albaran.infraestructure.ports.in.rest.specifications;

import com.rams.albaran.infraestructure.ports.out.jpa.entity.ServiceEntity;
import org.springframework.data.jpa.domain.Specification;

public class ServiceSpecifications {

    public static Specification<ServiceEntity> typeContains(String query) {
        return (root, q, cb) -> {

            if (query == null || query.trim().length() < 3) {
                return null;
            }

            String pattern = "%" + query.toLowerCase() + "%";

            return cb.like(cb.lower(root.get("type")), pattern);
        };
    }
}
