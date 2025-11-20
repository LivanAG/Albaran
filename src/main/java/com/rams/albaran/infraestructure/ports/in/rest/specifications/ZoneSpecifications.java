package com.rams.albaran.infraestructure.ports.in.rest.specifications;


import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;
import org.springframework.data.jpa.domain.Specification;

public class ZoneSpecifications {

    public static Specification<ZoneEntity> search(String query) {
        return (root, queryObj, cb) -> {
            if (query == null || query.trim().length() < 3) {
                return null;
            }

            String pattern = "%" + query.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("cp")), pattern)
            );
        };
    }
}
