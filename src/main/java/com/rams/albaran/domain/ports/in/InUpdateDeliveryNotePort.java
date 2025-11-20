package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.DeliveryNote;

public interface InUpdateDeliveryNotePort {
    void update(Integer id, DeliveryNote deliveryNote);
}
