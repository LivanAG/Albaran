package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Zone;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InGetZonePort {
    List<Zone> findAllByFilters(String text);
    Page<Zone> getPaged(String query, int page, int size);


}
