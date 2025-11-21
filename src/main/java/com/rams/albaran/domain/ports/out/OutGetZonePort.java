package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Zone;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OutGetZonePort {

    Zone getZoneById(Integer id);
    List<Zone> getZoneListByIds(List<Integer> id);
    List<Zone> findAllByFilters(String text);
    Page<Zone> getPaged(String query, int page, int size);
}
