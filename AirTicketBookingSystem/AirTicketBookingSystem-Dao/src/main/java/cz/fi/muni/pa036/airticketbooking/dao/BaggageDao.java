/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Baggage;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface BaggageDao {
    void create(Baggage entity);
    
    void update(Baggage entity);
    
    void delete(Baggage entity);

    Baggage getById(Long id);
    
    List<Baggage> getAll();
    
    List<Baggage> getByFlightTicket(Long ticket);
}
