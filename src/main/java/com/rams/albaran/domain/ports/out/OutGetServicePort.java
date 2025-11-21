package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Service;
import com.rams.albaran.domain.model.Zone;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OutGetServicePort {
    public Service getServiceById(Integer id);
    List<Service> search(String query);
    Page<Service> getPaged(String query, int page, int size);

}
