package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Zone;

import java.util.List;

public interface OutGetZonePort {

    public Zone getZoneById(Integer id);
    public List<Zone> getZoneListByIds(List<Integer> id);
    List<Zone> findAllByFilters(String text);
}
