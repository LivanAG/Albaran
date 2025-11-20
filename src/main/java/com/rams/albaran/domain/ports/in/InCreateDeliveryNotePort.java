package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.DeliveryNote;

public interface InCreateDeliveryNotePort {
    void create(DeliveryNote deliveryNote);
}
