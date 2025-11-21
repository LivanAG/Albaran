package com.rams.albaran.domain.ports.in;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InGetServicePort {
    List<Service> search(String query);
    Page<Service> getPaged(String query, int page, int size);

}
