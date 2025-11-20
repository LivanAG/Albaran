package com.rams.albaran.application.useCase;

import com.rams.albaran.domain.model.DeliveryNote;

import com.rams.albaran.domain.ports.in.InGetDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutDeliveryNotePort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class GetDeliveryNoteUseCase implements InGetDeliveryNotePort {

    private final OutDeliveryNotePort outPort;

    public GetDeliveryNoteUseCase(OutDeliveryNotePort outPort) {
        this.outPort = outPort;
    }


    @Override
    public DeliveryNote getDeliveryNoteById(Integer id) {
        return outPort.getDeliveryNoteById(id);
    }

    @Override
    public Page<DeliveryNote> getPaged(Integer number, Date start, Date end, int page, int size) {
        return outPort.findPaged(number, start, end, page, size);
    }

    @Override
    public List<DeliveryNote> getNotesByDates(Date start, Date end) {
        return outPort.findNotesByDates(start, end);
    }
}
