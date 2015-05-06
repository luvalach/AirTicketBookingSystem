/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatReservationDto;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface SeatReservationService {
     void create(SeatReservationDto entity);
    
    void update(SeatReservationDto entity);
    
    void delete(SeatReservationDto entity);

    SeatReservationDto getById(Long id);
    
    List<SeatReservationDto> getAll();
    
    List<SeatReservationDto> getByTicket(Long ticket);
    
    List<SeatReservationDto> getBySeat(Long seat);
}
