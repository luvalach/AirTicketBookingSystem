/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import cz.fi.muni.pa036.airticketbooking.entity.Seat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class SeatConverter {
    public static Seat seatDtoToEntity(SeatDto seat) {
        Seat s = new Seat();
        s.setId(seat.getId());
        s.setNumeric(seat.getNumber().intValue());
        s.setBusinessClass(seat.getBusinessClass());
        s.setDisabledSeating(seat.getDisabledSeating());
        s.setEconomyClass(seat.getEconomyClass());
        s.setFirstClass(seat.getFirstClass());
        s.setInTheMiddle(seat.getInTheMiddle());
        s.setNextToAisle(seat.getNextToAisle());
        s.setNextToWindow(seat.getNextToWindow());
        s.setPlane(PlaneConverter.planeDtoToEntity(seat.getAirplane()));
        s.setSecondClass(seat.getSecondClass());
        return s;
    }

    public static SeatDto seatEntityToDto(Seat seat) {
        SeatDto s = new SeatDto();
        s.setId(seat.getId());
        s.setNumber(seat.getNumeric().longValue());
        s.setBusinessClass(seat.getBusinessClass());
        s.setDisabledSeating(seat.getDisabledSeating());
        s.setEconomyClass(seat.getEconomyClass());
        s.setFirstClass(seat.getFirstClass());
        s.setInTheMiddle(seat.getInTheMiddle());
        s.setNextToAisle(seat.getNextToAisle());
        s.setNextToWindow(seat.getNextToWindow());
        s.setAirplane(PlaneConverter.planeEntityToDto(seat.getPlane()));
        s.setSecondClass(seat.getSecondClass());
        return s;
    }

    public static List<SeatDto> seatEntityToDtoList(List<Seat> seats) {
            List<SeatDto> seatDtoList = new ArrayList<>();
        for (Seat seat : seats) {
            seatDtoList.add(SeatConverter.seatEntityToDto(seat));
        }
        return seatDtoList;
    }
}
