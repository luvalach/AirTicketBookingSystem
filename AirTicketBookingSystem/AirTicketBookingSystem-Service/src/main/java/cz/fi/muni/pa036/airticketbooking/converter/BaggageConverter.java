/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.BaggageDto;
import cz.fi.muni.pa036.airticketbooking.entity.Baggage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class BaggageConverter {
     public static Baggage baggageDtoToEntity(BaggageDto baggage) {
        Baggage b = new Baggage();
        b.setId(baggage.getId());
        b.setAmount(baggage.getAmount());
        b.setFlightTicket(FlightTicketConverter.flightTicketDtoToEntity(baggage.getFlightTicket()));
        b.setType(baggage.getType());
        return b;
    }

    public static BaggageDto baggageEntityToDto(Baggage baggage) {
        BaggageDto b = new BaggageDto();
        b.setId(baggage.getId());
        b.setAmount(baggage.getAmount());
        b.setFlightTicket(FlightTicketConverter.flightTicketEntityToDto(baggage.getFlightTicket()));
        b.setType(baggage.getType());
        return b;
    }

    public static List<BaggageDto> baggageEntityToDtoList(List<Baggage> baggages) {
        List<BaggageDto> baggageDtoList = new ArrayList<>();
        for (Baggage baggage : baggages) {
            baggageDtoList.add(BaggageConverter.baggageEntityToDto(baggage));
        }
        return baggageDtoList;}
}
