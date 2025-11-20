package com.rams.albaran.domain.ports.out;

import com.rams.albaran.domain.model.Price;
import com.rams.albaran.domain.model.Service;

public interface OutGetPricePort {
    public Price getPriceByService(Service service);

}
