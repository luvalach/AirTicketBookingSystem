/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatReservationDto;
import cz.fi.muni.pa036.airticketbooking.api.service.SeatReservationService;
import cz.fi.muni.pa036.airticketbooking.converter.SeatReservationConverter;
import cz.fi.muni.pa036.airticketbooking.dao.SeatReservationDao;
import cz.fi.muni.pa036.airticketbooking.entity.SeatReservation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class SeatReservationServiceImpl implements SeatReservationService{
   
    private SeatReservationDao seatReservationDao;
    
    public void setDAO(SeatReservationDao seatReservationDao) {
        this.seatReservationDao = seatReservationDao;
    }
    
     @Override
    public void create(SeatReservationDto entity) {
        SeatReservation seatReservation = SeatReservationConverter.seatReservationDtoToEntity(entity);
        seatReservationDao.create(seatReservation);
        entity.setId(seatReservation.getId());   
    }

     @Override
    public void update(SeatReservationDto entity) {
        SeatReservation seatReservation = SeatReservationConverter.seatReservationDtoToEntity(entity);
        seatReservationDao.update(seatReservation);
        entity = SeatReservationConverter.seatReservationEntityToDto(seatReservation);
    }

    @Override
    public void delete(SeatReservationDto entity) {
        SeatReservation seatReservation = SeatReservationConverter.seatReservationDtoToEntity(entity);
        seatReservationDao.delete(seatReservation);
    }

    @Override
    public SeatReservationDto getById(Long id) {
        return SeatReservationConverter.seatReservationEntityToDto(seatReservationDao.getById(id));
    }

    @Override
    public List<SeatReservationDto> getAll() {
        List<SeatReservation> seatReservations = new ArrayList<>();
        seatReservations = seatReservationDao.getAll();
        return SeatReservationConverter.seatReservationEntityToDtoList(seatReservations);
    }

    @Override
    public List<SeatReservationDto> getByTicket(Long ticket) {
        return SeatReservationConverter.seatReservationEntityToDtoList(seatReservationDao.getByTicket(ticket));
    }

    @Override
    public List<SeatReservationDto> getBySeat(Long seat) {
        return SeatReservationConverter.seatReservationEntityToDtoList(seatReservationDao.getBySeat(seat));
    }
    
}
