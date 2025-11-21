package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Zone;

public interface OutCreateAndUpdateZonePort {
    Zone save(Zone request);
}
