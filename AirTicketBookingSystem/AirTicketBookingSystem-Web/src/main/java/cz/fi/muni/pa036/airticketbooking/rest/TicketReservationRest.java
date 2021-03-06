package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketWithPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightService;
import cz.fi.muni.pa036.airticketbooking.api.service.SeatService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import cz.fi.muni.pa036.airticketbooking.api.service.TicketReservationService;
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
 *
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/ticketReservation")
public class TicketReservationRest {

    @Autowired
    FlightService flightService;

    @Autowired
    SecurityService securityService;
    
    @Autowired
    SeatService seatService;

    @Autowired
    TicketReservationService reservationService;

    @RequestMapping(value = "/{flightId}", method = RequestMethod.GET)
    public FlightDto getFlightWithAllData(@PathVariable Long flightId) {
        FlightDto flight = flightService.getById(flightId);
        return this.eliminateInfiniteRecursiveFromDepthObjects(flight);
    }

    //Only for test
    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public FlightTicketWithPriceDto getEmptyReservationDto() {
        return new FlightTicketWithPriceDto();
    }
    
     @RequestMapping(value = "/getSeats", method = RequestMethod.GET)
    public List<SeatDto> getSeatList() {
        List<SeatDto> flightList = seatService.getAll();
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(flightList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Long> createReservation(@RequestBody @Valid List<FlightTicketWithPriceDto> flightTicketWithPriceDtoList) {
        List<Long> resultList = reservationService.splitAndSaveFlightTicketList(flightTicketWithPriceDtoList);
        return resultList;
    }

    @RequestMapping(value = "/validateTicket", method = RequestMethod.POST)
    public void validateTicket(@RequestBody @Valid FlightTicketWithPriceDto flightTicketWithPriceDtoList) {
    }

    private FlightDto eliminateInfiniteRecursiveFromDepthObjects(FlightDto flight) {
        for (FlightTicketDto flightTicket : flight.getFlightTickets()) {
            flightTicket.setFlight(null);
        }

        Iterator<FlightTicketDto> flightTicketsIterator = flight.getFlightTickets().iterator();
        while (flightTicketsIterator.hasNext()) {
            flightTicketsIterator.next().setFlightTicketPrices(null);
        }

        Iterator<FlightPriceDto> flightPricesIterator = flight.getFlightPrices().iterator();
        while (flightPricesIterator.hasNext()) {
            flightPricesIterator.next().setFlight(null);
        }

        flight.getAirportByAirportFromId().setFlightsForAirportFromId(null);
        flight.getAirportByAirportFromId().setFlightsForAirportToId(null);
        flight.getAirportByAirportFromId().getCity().setAirports(null);
        flight.getAirportByAirportToId().setFlightsForAirportFromId(null);
        flight.getAirportByAirportToId().setFlightsForAirportToId(null);
        flight.getAirportByAirportToId().getCity().setAirports(null);
        flight.getPlane().setFlights(null);
        flight.getPlane().setSeats(null);

        return flight;
    }
    
    
    private List<SeatDto> eliminateInfiniteRecursive(List<SeatDto> seatList) {
        for (SeatDto seat : seatList) {
            eliminateInfiniteRecursive(seat);
        }
        return seatList;
    }

    /**
     * Eliminate infinite recurcive Seat->Airport_set->Seat, which can't be
     * converted to json
     */
    private SeatDto eliminateInfiniteRecursive(SeatDto seat) {
        seat.getAirplane().set(null);
        seat.getAirplane().setFlights(null);
        seat.getAirplane().setSeats(null);
        return seat;
    }
}
