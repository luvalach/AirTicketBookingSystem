/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.FlightTicket;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface FlightTicketDao {

    void create(FlightTicket entity);

    void delete(FlightTicket entity);

    List<FlightTicket> getAll();

    FlightTicket getById(Long id);

    void update(FlightTicket entity);
    
}
