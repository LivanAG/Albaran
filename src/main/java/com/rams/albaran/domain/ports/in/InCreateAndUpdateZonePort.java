package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Zone;

public interface InCreateAndUpdateZonePort {
    void create(Zone request);
    void update(Integer id, Zone request);

}
