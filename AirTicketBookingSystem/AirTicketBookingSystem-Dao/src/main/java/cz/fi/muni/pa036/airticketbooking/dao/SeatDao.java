/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Seat;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface SeatDao {
    void create(Seat entity);
    
    void update(Seat entity);
    
    void delete(Seat entity);

    Seat getById(Long id);
    
    List<Seat> getAll();
    
    List<Seat> getByPlane(Long plane);
    
}
