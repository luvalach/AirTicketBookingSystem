/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatReservationDto;
import cz.fi.muni.pa036.airticketbooking.entity.SeatReservation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class SeatReservationConverter {
    public static SeatReservation seatReservationDtoToEntity(SeatReservationDto seat) {
        SeatReservation s = new SeatReservation();
        s.setId(seat.getId());
        s.setFlightTicket(FlightTicketConverter.flightTicketDtoToEntity(seat.getFlightTicket()));
        s.setSeat(SeatConverter.seatDtoToEntity(seat.getSeat()));
        return s;
    }

    public static SeatReservationDto seatReservationEntityToDto(SeatReservation seat) {
        SeatReservationDto s = new SeatReservationDto();
        s.setId(seat.getId());
        s.setFlightTicket(FlightTicketConverter.flightTicketEntityToDto(seat.getFlightTicket()));
        s.setSeat(SeatConverter.seatEntityToDto(seat.getSeat()));
        return s;
    }

    public static List<SeatReservationDto> seatReservationEntityToDtoList(List<SeatReservation> seatReservations) {
        List<SeatReservationDto> seatReservationDtoList = new ArrayList<>();
        for (SeatReservation seatReservation : seatReservations) {
            seatReservationDtoList.add(SeatReservationConverter.seatReservationEntityToDto(seatReservation));
        }
        return seatReservationDtoList;
    }
}
