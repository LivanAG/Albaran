package com.rams.albaran.application.useCase;

import com.rams.albaran.application.service.CreateDeliveryNoteService;
import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.ports.in.InCreateDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutDeliveryNotePort;

import org.springframework.stereotype.Service;

@Service
public class CreateDeliveryNoteUseCase implements InCreateDeliveryNotePort {


    private final OutDeliveryNotePort outDeliveryNotePort;
    private final CreateDeliveryNoteService createDeliveryNoteService;
    public CreateDeliveryNoteUseCase(OutDeliveryNotePort outDeliveryNotePort, CreateDeliveryNoteService createDeliveryNoteService) {
        this.outDeliveryNotePort = outDeliveryNotePort;
        this.createDeliveryNoteService = createDeliveryNoteService;
    }

    @Override
    public void create(DeliveryNote deliveryNote) {
        outDeliveryNotePort.save(createDeliveryNoteService.create(deliveryNote));

    }
}
