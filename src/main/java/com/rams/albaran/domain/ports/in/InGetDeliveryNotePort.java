package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.DeliveryNote;
import org.springframework.data.domain.Page;
import java.sql.Date;
import java.util.List;

public interface InGetDeliveryNotePort {
    DeliveryNote getDeliveryNoteById(Integer id);
    Page<DeliveryNote> getPaged(Integer number, Date start, Date end, int page, int size);

    List<DeliveryNote> getNotesByDates(Date start, Date end);
}
