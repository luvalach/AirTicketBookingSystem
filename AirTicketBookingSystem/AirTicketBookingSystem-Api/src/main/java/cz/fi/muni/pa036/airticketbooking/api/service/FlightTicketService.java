/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface FlightTicketService {

    void create(FlightTicketDto entity);

    void delete(FlightTicketDto entity);

    List<FlightTicketDto> getAll();

    FlightTicketDto getById(Long id);

    void update(FlightTicketDto entity);
    
}
