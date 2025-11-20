package com.rams.albaran.domain.ports.in;

import java.util.List;

public interface InDeleteDeliveryNotePort {
    void deleteByIds(List<Integer> ids);
}