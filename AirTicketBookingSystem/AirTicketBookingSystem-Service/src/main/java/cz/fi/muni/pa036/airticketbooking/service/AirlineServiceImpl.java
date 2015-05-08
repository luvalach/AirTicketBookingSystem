/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirlineDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AirlineService;
import cz.fi.muni.pa036.airticketbooking.converter.AirlineConverter;
import cz.fi.muni.pa036.airticketbooking.dao.AirlineDao;
import cz.fi.muni.pa036.airticketbooking.entity.Airline;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tommy
 */
@Service
public class AirlineServiceImpl implements AirlineService{
    
    @Autowired
    private AirlineDao adminDao;
    
    public void setDAO(AirlineDao adminDao) {
        this.adminDao = adminDao;
    }
    
    @Override
    public List<AirlineDto> getAll() {
        List<Airline> airlines = new ArrayList<>();
        airlines = adminDao.getAll();
        return AirlineConverter.airlineEntityToDtoList(airlines);
    }

    @Override
    public AirlineDto getById(Long id) {
        Airline airline = new Airline();
        airline = adminDao.getById(id);
        return AirlineConverter.airlineEntityToDto(airline);
    }

    @Override
    public AirlineDto getMainAirlineDto() {
        Airline airline = new Airline();
        airline = adminDao.getMainAirline();
        return AirlineConverter.airlineEntityToDto(airline);
    }
}
