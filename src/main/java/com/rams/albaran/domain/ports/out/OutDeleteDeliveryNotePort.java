package com.rams.albaran.domain.ports.out;

import java.util.List;

public interface OutDeleteDeliveryNotePort {
    void deleteByIds(List<Integer> ids);
}