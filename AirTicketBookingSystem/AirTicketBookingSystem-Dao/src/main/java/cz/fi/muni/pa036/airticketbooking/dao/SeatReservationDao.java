/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.SeatReservation;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface SeatReservationDao {
    void create(SeatReservation entity);
    
    void update(SeatReservation entity);
    
    void delete(SeatReservation entity);

    SeatReservation getById(Long id);
    
    List<SeatReservation> getAll();
    
    List<SeatReservation> getByTicket(Long ticket);
    
    List<SeatReservation> getBySeat(Long seat);
    
}
