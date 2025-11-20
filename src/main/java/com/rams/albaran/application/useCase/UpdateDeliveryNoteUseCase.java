package com.rams.albaran.application.useCase;

import com.rams.albaran.application.service.CreateDeliveryNoteService;
import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.ports.in.InUpdateDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutDeliveryNotePort;
import org.springframework.stereotype.Component;

@Component

public class UpdateDeliveryNoteUseCase implements InUpdateDeliveryNotePort {

    private final OutDeliveryNotePort outDeliveryNotePort;
    private final CreateDeliveryNoteService createDeliveryNoteService;
    public UpdateDeliveryNoteUseCase(OutDeliveryNotePort outDeliveryNotePort, CreateDeliveryNoteService createDeliveryNoteService) {
        this.outDeliveryNotePort = outDeliveryNotePort;
        this.createDeliveryNoteService = createDeliveryNoteService;
    }

    @Override
    public void update(Integer id, DeliveryNote deliveryNote) {
        DeliveryNote last = outDeliveryNotePort.getDeliveryNoteById(id);
        DeliveryNote updated = createDeliveryNoteService.create(deliveryNote);
        updated.setId(last.getId());
        outDeliveryNotePort.save(updated);
    }
}
