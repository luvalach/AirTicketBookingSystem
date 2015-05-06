/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import cz.fi.muni.pa036.airticketbooking.api.service.SeatService;
import cz.fi.muni.pa036.airticketbooking.converter.SeatConverter;
import cz.fi.muni.pa036.airticketbooking.dao.SeatDao;
import cz.fi.muni.pa036.airticketbooking.entity.Seat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class SeatServiceImpl implements SeatService{
    private SeatDao seatDao;
    
    public void setDAO(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    
    @Override
    public void create(SeatDto entity) {
        Seat seat = SeatConverter.seatDtoToEntity(entity);
        seatDao.create(seat);
        entity.setId(seat.getId());   
    }

     @Override
    public void update(SeatDto entity) {
        Seat seat = SeatConverter.seatDtoToEntity(entity);
        seatDao.update(seat);
        entity = SeatConverter.seatEntityToDto(seat);
    }

    @Override
    public void delete(SeatDto entity) {
        Seat seat = SeatConverter.seatDtoToEntity(entity);
        seatDao.delete(seat);
    }

    @Override
    public SeatDto getById(Long id) {
        return SeatConverter.seatEntityToDto(seatDao.getById(id));
    }

    @Override
    public List<SeatDto> getAll() {
        List<Seat> seats = new ArrayList<>();
        seats = seatDao.getAll();
        return SeatConverter.seatEntityToDtoList(seats);
    }

    @Override
    public List<SeatDto> getByPlane(Long plane) {
        return SeatConverter.seatEntityToDtoList(seatDao.getByPlane(plane));
    }
    
    
}
