/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.service.FlightTicketService;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.converter.FlightTicketConverter;
import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
@Transactional
public class FlightTicketServiceImpl implements FlightTicketService {

    @Autowired
    FlightTicketDao flightTicketDao;

    public FlightTicketServiceImpl() {
    }

    @Override
    public FlightTicketDto getById(Long id) {
        return FlightTicketConverter.flightTicketEntityToDto(flightTicketDao.getById(id));
    }

    @Override
    public List<FlightTicketDto> getAll() {
        return FlightTicketConverter.flightTicketEntityToDtoList(flightTicketDao.getAll());
    }

    @Override
    public void create(FlightTicketDto entity) {
        flightTicketDao.create(FlightTicketConverter.flightTicketDtoToEntity(entity));
    }

    @Override
    public void update(FlightTicketDto entity) {
        flightTicketDao.update(FlightTicketConverter.flightTicketDtoToEntity(entity));
    }

    @Override
    public void delete(FlightTicketDto entity) {
        flightTicketDao.delete(FlightTicketConverter.flightTicketDtoToEntity(entity));
    }
}
