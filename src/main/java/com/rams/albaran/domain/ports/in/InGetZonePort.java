package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Zone;

import java.util.List;

public interface InGetZonePort {
    List<Zone> findAllByFilters(String text);
}
