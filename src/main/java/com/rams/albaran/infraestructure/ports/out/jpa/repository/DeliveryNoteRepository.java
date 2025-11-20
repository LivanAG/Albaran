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

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM delivery_note_zone WHERE delivery_note_id IN (:ids)", nativeQuery = true)
    void deleteRelations(@Param("ids") List<Integer> ids);

    void deleteAllByIdIn(List<Integer> ids);
}
