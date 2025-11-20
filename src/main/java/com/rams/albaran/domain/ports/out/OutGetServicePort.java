package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Service;

import java.util.List;

public interface OutGetServicePort {
    public Service getServiceById(Integer id);
    List<Service> search(String query);
}
