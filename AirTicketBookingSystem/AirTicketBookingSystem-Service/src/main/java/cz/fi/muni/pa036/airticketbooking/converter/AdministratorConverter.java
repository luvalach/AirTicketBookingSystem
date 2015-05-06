/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.AdministratorDto;
import cz.fi.muni.pa036.airticketbooking.entity.Administrator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class AdministratorConverter {
    public static Administrator administratorDtoToEntity(AdministratorDto dto) {
        Administrator admin = new Administrator();
        admin.setId(dto.getId());
        admin.setName(dto.getName());
        admin.setPassword(dto.getPassword());
        admin.setAirline(AirlineConverter.airlineDtoToEntity(dto.getAirline()));
        return admin;
    }
    
    public static AdministratorDto administratorEntityToDto(Administrator ent) {
        AdministratorDto dto = new AdministratorDto();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setPassword(ent.getPassword());
        dto.setAirline(AirlineConverter.airlineEntityToDto(ent.getAirline()));
        return dto;
    }
    public static List<AdministratorDto> administratorEntityToDtoList(List<Administrator> admins) {
         List<AdministratorDto> administratorDtoList = new ArrayList<>();
        for (Administrator administrator : admins) {
            administratorDtoList.add(AdministratorConverter.administratorEntityToDto(administrator));
        }
        return administratorDtoList;
    }
}
