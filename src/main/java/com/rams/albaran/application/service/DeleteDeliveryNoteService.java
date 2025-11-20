package com.rams.albaran.application.service;

import com.rams.albaran.domain.ports.in.InDeleteDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutDeleteDeliveryNotePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeleteDeliveryNoteService implements InDeleteDeliveryNotePort {

    private final OutDeleteDeliveryNotePort outDeleteDeliveryNotePort;

    public DeleteDeliveryNoteService(OutDeleteDeliveryNotePort outDeleteDeliveryNotePort) {
        this.outDeleteDeliveryNotePort = outDeleteDeliveryNotePort;
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        outDeleteDeliveryNotePort.deleteByIds(ids);
    }
}