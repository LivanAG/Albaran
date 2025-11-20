package com.rams.albaran.infraestructure.ports.out.jpa.repository;

import com.rams.albaran.infraestructure.ports.out.jpa.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Integer> {

    Optional<PriceEntity> findByServiceEntityIdAndIsActiveTrue(Integer serviceId);

}