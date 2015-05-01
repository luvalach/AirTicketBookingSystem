/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.service.FlightTicketPriceService;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketPriceDto;
import cz.fi.muni.pa036.airticketbooking.converter.FlightTicketPriceConverter;
import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketPriceDao;
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
public class FlightTicketPriceServiceImpl implements FlightTicketPriceService {

    @Autowired
    FlightTicketPriceDao flightTicketPriceDao;

    public FlightTicketPriceServiceImpl() {
    }

    @Override
    public FlightTicketPriceDto getById(Long id) {
        return FlightTicketPriceConverter.flightTicketPriceEntityToDto(flightTicketPriceDao.getById(id));
    }

    @Override
    public List<FlightTicketPriceDto> getAll() {
        return FlightTicketPriceConverter.flightTicketPriceEntityToDtoList(flightTicketPriceDao.getAll());
    }

    @Override
    public void create(FlightTicketPriceDto entity) {
        flightTicketPriceDao.create(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(entity));
    }

    @Override
    public void update(FlightTicketPriceDto entity) {
        flightTicketPriceDao.update(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(entity));
    }

    @Override
    public void delete(FlightTicketPriceDto entity) {
        flightTicketPriceDao.delete(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(entity));
    }
}
