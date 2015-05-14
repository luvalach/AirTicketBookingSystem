/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightTicketService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/ticket")
public class FlightTicket {

    @Autowired
    FlightTicketService flightTicketService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightTicketDto> getTicketList() {
        List<FlightTicketDto> ticketList = flightTicketService.getAll();
        if (ticketList == null) {
            ticketList = new ArrayList<>();
        } else {
            Iterator<FlightTicketDto> iterator = ticketList.iterator();
            while (iterator.hasNext()) {
                FlightTicketDto ticket = iterator.next();
                ticket.setBaggages(null);
                ticket.getFlight().setAirportByAirportFromId(null);
                ticket.getFlight().setAirportByAirportToId(null);
                ticket.getFlight().setFlightPrices(null);
                ticket.getFlight().setFlightTickets(null);
                ticket.getFlight().setPlane(null);
                ticket.setNextFlightTicket(null);
                ticket.setSeatReservations(null);
            }
        }
        return ticketList;
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    public FlightTicketDto getTicketDetail(@PathVariable Long ticketId) {
        FlightTicketDto ticket = flightTicketService.getById(ticketId);
        ticket.setBaggages(null);
        ticket.getFlight().getAirportByAirportFromId().setFlightsForAirportFromId(null);
        ticket.getFlight().getAirportByAirportFromId().setFlightsForAirportToId(null);
        ticket.getFlight().getAirportByAirportFromId().getCity().setAirports(null);
        ticket.getFlight().getAirportByAirportToId().setFlightsForAirportFromId(null);
        ticket.getFlight().getAirportByAirportToId().setFlightsForAirportToId(null);
        ticket.getFlight().getAirportByAirportToId().getCity().setAirports(null);
        ticket.getFlight().setFlightPrices(null);
        ticket.getFlight().setFlightTickets(null);
        ticket.getFlight().getPlane().set(null);
        ticket.getFlight().getPlane().setFlights(null);
        ticket.getFlight().getPlane().setSeats(null);
        ticket.setNextFlightTicket(null);
        ticket.setSeatReservations(null);
        return ticket;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody @Valid FlightTicketDto flightTicketDto) {
        flightTicketService.update(flightTicketDto);
    }

    @RequestMapping(value = "{ticketId}", method = RequestMethod.DELETE)
    public void deleteTicket(@PathVariable Long ticketId) {
        FlightTicketDto ticket = flightTicketService.getById(ticketId);
        flightTicketService.delete(ticket);
    }
}
