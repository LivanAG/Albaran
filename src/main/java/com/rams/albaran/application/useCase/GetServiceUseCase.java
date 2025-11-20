package com.rams.albaran.application.useCase;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.ports.in.InGetServicePort;
import com.rams.albaran.domain.ports.out.OutGetServicePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetServiceUseCase implements InGetServicePort {

    private final OutGetServicePort outPort;

    public GetServiceUseCase(OutGetServicePort outPort) {
        this.outPort = outPort;
    }

    @Override
    public List<Service> search(String query) {
        return outPort.search(query);
    }
}
