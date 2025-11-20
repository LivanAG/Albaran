package com.rams.albaran.application.service;

import com.rams.albaran.domain.model.DeliveryNote;
import com.rams.albaran.domain.model.Price;
import com.rams.albaran.domain.model.Zone;
import com.rams.albaran.domain.ports.out.OutDeliveryNotePort;
import com.rams.albaran.domain.ports.out.OutGetPricePort;
import com.rams.albaran.domain.ports.out.OutGetServicePort;
import com.rams.albaran.domain.ports.out.OutGetZonePort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CreateDeliveryNoteService {


    private final OutGetZonePort outGetZonePort;
    private final OutGetServicePort outGetServicePort;
    private final OutGetPricePort outGetPricePort;

    public CreateDeliveryNoteService(OutGetZonePort outGetZonePort, OutGetServicePort outGetServicePort, OutGetPricePort outGetPricePort) {
        this.outGetZonePort = outGetZonePort;
        this.outGetServicePort = outGetServicePort;
        this.outGetPricePort = outGetPricePort;
    }

    public DeliveryNote create(DeliveryNote deliveryNote) {
        DeliveryNote d = new DeliveryNote();


        // lógica cuando es un servicio pagado directo
        if(deliveryNote.getDirectPayment()){
            d.setDate(deliveryNote.getDate());
            d.setNumber(deliveryNote.getNumber());
            d.setTotalDeliveryNoteAmount(deliveryNote.getTotalDeliveryNoteAmount());

            if(deliveryNote.getService() != null){
                d.setService(deliveryNote.getService());
            }

            if(deliveryNote.getZones() != null){
                d.setZones(deliveryNote.getZones());
            }
            d.setDirectPayment(true);

            // SETEAR TODO LO DEMÁS A NULL
            d.setServiceCount(null);
            d.setSuburbCount(null);
            d.setLoadUnloadTimeCount(null);
            d.setKmsCount(null);

            d.setTotalAmountService(null);
            d.setTotalAmountSuburb(null);
            d.setTotalAmountloadUnloadTime(null);
            d.setTotalAmountKms(null);
            d.setIsNational(false);
        }else {

            //Comprobamos que el servicio exista y lo cargamos
            d.setService(outGetServicePort.getServiceById(deliveryNote.getService().getId()));
            d.setDirectPayment(false);
            //Obtenemos los precios activos para ese servicio

            //Comprobamos si es un servicio por zona o pago directo
            Price price = outGetPricePort.getPriceByService(d.getService());


            // lógica cuando es un servicio por zonas
            if (deliveryNote.getZones() != null && !deliveryNote.getZones().isEmpty()) {
                //Comprobamos que las zonas existan y las cargamos completas
                List<Integer> zoneIds = deliveryNote.getZones().stream()
                        .map(Zone::getId)
                        .toList();
                d.setZones(outGetZonePort.getZoneListByIds(zoneIds));
            }


            //Asignamos fecha del albaran
            d.setDate(deliveryNote.getDate());
            //Asignamos numero de albaran
            d.setNumber(deliveryNote.getNumber());
            //Asignamos la cantidad de servicios en dependencia de la cantidad de zonas
            d.setServiceCount(d.getZones() != null ? d.getZones().size() : 0);
            d.setIsNational(deliveryNote.getIsNational());

            //Asignamos cantidad de extrarradios en dependencia de las zonas
            int countSuburb = d.getZones().stream()
                    .filter(zone -> zone.getSuburb() != null)
                    .mapToInt(Zone::getSuburb)
                    .sum();
            d.setSuburbCount(countSuburb);



            //Asignamos el total de kms segun todas las zonas
            int totalKms = d.getZones().stream()
                    .filter(zone -> zone.getKms() != null)
                    .mapToInt(Zone::getKms)
                    .sum();

            d.setKmsCount(totalKms);


            //Aignamos el tiempo de carga y descarga
            d.setLoadUnloadTimeCount(deliveryNote.getLoadUnloadTimeCount());


            //Calculamos los valores count con los precios

            // Multiplicamos extrarradios × precio suburb
            BigDecimal totalSuburb = BigDecimal
                    .valueOf(d.getSuburbCount())
                    .multiply(price.getSuburb())
                    .setScale(2, RoundingMode.HALF_UP);
            d.setTotalAmountSuburb(totalSuburb);

            // Multiplicamos servicio × precio servicio
            BigDecimal totalService = BigDecimal
                    .valueOf(d.getServiceCount())
                    .multiply(price.getServicePrice())
                    .setScale(2, RoundingMode.HALF_UP);
            d.setTotalAmountService(totalService);

            // Multiplicamos tiempo carga/descarga × su precio
            BigDecimal totalLoadUnloadTime = BigDecimal
                    .valueOf(d.getLoadUnloadTimeCount())
                    .multiply(price.getLoadUnloadTime())
                    .setScale(2, RoundingMode.HALF_UP);
            d.setTotalAmountloadUnloadTime(totalLoadUnloadTime);

            // Multiplicamos kms × precio km
            BigDecimal totalkms = BigDecimal
                    .valueOf(d.getKmsCount())
                    .multiply(price.getKm())
                    .setScale(2, RoundingMode.HALF_UP);
            d.setTotalAmountKms(totalkms);

            // NATIONAL PLUSS
            BigDecimal nationalPlus = BigDecimal.ZERO;
            if (deliveryNote.getIsNational()) {
                nationalPlus = price.getNational().setScale(2, RoundingMode.HALF_UP);
                d.setTotalNational(nationalPlus);
            }

            // Sumar todos los totales con precisión
            BigDecimal totalDeliveryNote = totalSuburb
                    .add(totalService)
                    .add(totalLoadUnloadTime)
                    .add(totalkms)
                    .add(nationalPlus)
                    .setScale(2, RoundingMode.HALF_UP);




            d.setTotalDeliveryNoteAmount(totalDeliveryNote);



        }



        return d;







    }
}
