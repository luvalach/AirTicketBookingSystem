/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirlineDto;
import cz.fi.muni.pa036.airticketbooking.entity.Airline;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class AirlineConverter {
     public static Airline airlineDtoToEntity(AirlineDto dto) {
        Airline airline = new Airline();
        airline.setId(dto.getId());
        airline.setCode(dto.getCode());
        airline.setMainAirline(dto.getMainAirline());
        airline.setName(dto.getName());       
        return airline;
    }
    
    public static AirlineDto airlineEntityToDto(Airline ent) {
        AirlineDto airline = new AirlineDto();
        airline.setId(ent.getId());
        airline.setCode(ent.getCode());
        airline.setMainAirline(ent.getMainAirline());
        airline.setName(ent.getName());       
        return airline;
    }

    public static List<AirlineDto> airlineEntityToDtoList(List<Airline> airlines) {
        List<AirlineDto> airlineDtoList = new ArrayList<>();
        for (Airline airline : airlines) {
            airlineDtoList.add(AirlineConverter.airlineEntityToDto(airline));
        }
        return airlineDtoList;
    }
}
