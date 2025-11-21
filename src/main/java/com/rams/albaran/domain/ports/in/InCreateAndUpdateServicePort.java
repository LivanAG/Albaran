package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;

public interface InCreateAndUpdateServicePort {

    void create(Service request);
    void update(Integer id, Service request);
}
