package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Service;

import java.util.List;

public interface InGetServicePort {
    List<Service> search(String query);

}
