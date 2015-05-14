/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public class PlaneConverter {

    public static Plane planeDtoToEntity(PlaneDto plane) {
        Plane p = new Plane();
        p.setId(plane.getId());
        p.setCode(plane.getCode());
        p.setCreationDate(plane.getCreationDate());
        p.setMaxSeats(plane.getMaxSeats());
        p.setType(plane.getType());
        return p;
    }

    public static PlaneDto planeEntityToDto(Plane plane) {
        PlaneDto p = new PlaneDto();
        p.setId(plane.getId());
        p.setCode(plane.getCode());
        p.setCreationDate(plane.getCreationDate());
        p.setMaxSeats(plane.getMaxSeats());
        p.setType(plane.getType());
        p.set(AirlineConverter.airlineEntityToDto(plane.getAirline()));
        return p;
    }

    public static List<PlaneDto> planeEntityToDtoList(List<Plane> planes) {
        List<PlaneDto> planeDtoList = new ArrayList<>();
        for (Plane plane : planes) {
            planeDtoList.add(PlaneConverter.planeEntityToDto(plane));
        }
        return planeDtoList;
    }
    
}
