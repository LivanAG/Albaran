package com.rams.albaran.infraestructure.ports.out.jpa.repository;

import com.rams.albaran.infraestructure.ports.out.jpa.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ZoneRepository extends JpaRepository<ZoneEntity, Integer>, JpaSpecificationExecutor<ZoneEntity> {}