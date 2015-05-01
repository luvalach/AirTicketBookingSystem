/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketPriceDto;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface FlightTicketPriceService {

    void create(FlightTicketPriceDto entity);

    void delete(FlightTicketPriceDto entity);

    List<FlightTicketPriceDto> getAll();

    FlightTicketPriceDto getById(Long id);

    void update(FlightTicketPriceDto entity);
    
}
