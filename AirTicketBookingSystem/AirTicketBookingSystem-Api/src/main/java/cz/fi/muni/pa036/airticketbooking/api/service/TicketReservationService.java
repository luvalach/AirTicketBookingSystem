/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketWithPriceDto;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface TicketReservationService {

    public List<Long> splitAndSaveFlightTicketList(List<FlightTicketWithPriceDto> flightTicketWithPriceDtoList);

    public Long splitAndSaveFlightTicket(FlightTicketWithPriceDto flightTicketWithPriceDto, Long nextTicketId);
}
