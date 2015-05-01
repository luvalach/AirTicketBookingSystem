/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.FlightTicketPrice;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface FlightTicketPriceDao {

    void create(FlightTicketPrice entity);

    void delete(FlightTicketPrice entity);

    List<FlightTicketPrice> getAll();

    FlightTicketPrice getById(Long id);

    void update(FlightTicketPrice entity);
    
}
