package com.rams.albaran.application.service;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.in.InCreateAndUpdateServicePort;
import com.rams.albaran.domain.ports.in.InGetServicePort;
import com.rams.albaran.domain.ports.out.OutCreateAndUpdateServicePort;
import com.rams.albaran.domain.ports.out.OutGetServicePort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyServiceService implements InGetServicePort, InCreateAndUpdateServicePort {

    private final OutGetServicePort outPort;
    private final OutCreateAndUpdateServicePort outCreateAndUpdateServicePort;

    public MyServiceService(OutGetServicePort outPort, OutCreateAndUpdateServicePort outCreateAndUpdateServicePort) {
        this.outPort = outPort;
        this.outCreateAndUpdateServicePort = outCreateAndUpdateServicePort;
    }

    @Override
    public List<Service> search(String query) {
        return outPort.search(query);
    }

    @Override
    public Page<Service> getPaged(String query, int page, int size) {
        return outPort.getPaged(query, page, size);
    }

    @Override
    public void create(Service request) {
        outCreateAndUpdateServicePort.save(request);

    }

    @Override
    public void update(Integer id, Service request) {
        Service last = outPort.getServiceById(id);
        request.setId(last.getId());
        outCreateAndUpdateServicePort.save(request);
    }
}
