package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.DeliveryNote;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.util.List;

public interface OutDeliveryNotePort {
     DeliveryNote save(DeliveryNote deliveryNote);
    Page<DeliveryNote> findPaged(Integer number, Date start, Date end, int page, int size);
    DeliveryNote getDeliveryNoteById(Integer id);
    List<DeliveryNote> findNotesByDates(Date start, Date end);

}
