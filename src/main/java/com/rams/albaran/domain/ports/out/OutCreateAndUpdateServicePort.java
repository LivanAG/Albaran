package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;

public interface OutCreateAndUpdateServicePort {
    Service save(Service request);

}
