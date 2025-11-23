package com.rams.albaran.infraestructure.ports.out.jpa.repository;

import com.rams.albaran.infraestructure.ports.out.jpa.entity.DeliveryNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNoteEntity, Integer>, JpaSpecificationExecutor<DeliveryNoteEntity> {

    void deleteAllByIdIn(List<Integer> ids);
}
