package com.rams.albaran.application.useCase;

import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.in.InGetZonePort;
import com.rams.albaran.domain.ports.out.OutGetZonePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetZoneUseCase implements InGetZonePort {
    private final OutGetZonePort outGetZonePort;
    public GetZoneUseCase(OutGetZonePort outGetZonePort) {
        this.outGetZonePort = outGetZonePort;
    }
    @Override
    public List<Zone> findAllByFilters(String query) {
        return outGetZonePort.findAllByFilters(query);
    }
}
