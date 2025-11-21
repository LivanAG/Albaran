package com.rams.albaran.application.service;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.in.InCreateAndUpdateZonePort;
import com.rams.albaran.domain.ports.in.InGetZonePort;
import com.rams.albaran.domain.ports.out.OutCreateAndUpdateZonePort;
import com.rams.albaran.domain.ports.out.OutGetZonePort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService implements InCreateAndUpdateZonePort, InGetZonePort {


    private final OutGetZonePort outGetZonePort;
    private final OutCreateAndUpdateZonePort outCreateAndUpdateZonePort;
    public ZoneService(OutGetZonePort outGetZonePort, OutCreateAndUpdateZonePort outCreateAndUpdateZonePort) {
        this.outGetZonePort = outGetZonePort;
        this.outCreateAndUpdateZonePort = outCreateAndUpdateZonePort;
    }
    @Override
    public List<Zone> findAllByFilters(String query) {
        return outGetZonePort.findAllByFilters(query);
    }

    @Override
    public Page<Zone> getPaged(String query, int page, int size) {
        return outGetZonePort.getPaged(query, page, size);
    }


    @Override
    public void create(Zone request) {
        outCreateAndUpdateZonePort.save(request);
    }

    @Override
    public void update(Integer id, Zone request) {
        Zone last = outGetZonePort.getZoneById(id);
        request.setId(last.getId());
        outCreateAndUpdateZonePort.save(request);
    }
}
