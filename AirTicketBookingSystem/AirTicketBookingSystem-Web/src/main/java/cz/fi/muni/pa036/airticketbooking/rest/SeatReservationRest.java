/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatReservationDto;
import cz.fi.muni.pa036.airticketbooking.api.service.SeatReservationService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import java.util.ArrayList;
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
 * @author Tommy
 */
@RestController
@RequestMapping("/seatReservation")
public class SeatReservationRest {

    @Autowired
    SeatReservationService seatReservationService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<SeatReservationDto> getSeatReservationList() {
        List<SeatReservationDto> flightList = seatReservationService.getAll();
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(flightList);
    }
    
    @RequestMapping(value = "{seatReservationId}", method = RequestMethod.GET)
    public SeatReservationDto getSeatReservationDetail(@PathVariable Long seatReservationId) {
        SeatReservationDto seatReservation = seatReservationService.getById(seatReservationId);
        return eliminateInfiniteRecursive(seatReservation);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createSeatReservation(@RequestBody @Valid SeatReservationDto seatReservation) {
        seatReservationService.create(seatReservation);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateSeatReservation(@RequestBody @Valid SeatReservationDto seatReservation) {
        seatReservationService.update(seatReservation);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteSeatReservation(@RequestBody @Valid Long seatReservation) {
        SeatReservationDto p = seatReservationService.getById(seatReservation);
        seatReservationService.delete(p);
    }
    
    private List<SeatReservationDto> eliminateInfiniteRecursive(List<SeatReservationDto> seatReservationList) {
        for (SeatReservationDto seatReservation : seatReservationList) {
            eliminateInfiniteRecursive(seatReservation);
        }
        return seatReservationList;
    }

    /**
     * Eliminate infinite recurcive SeatReservation->Airport_set->SeatReservation, which can't be
     * converted to json
     */
    private SeatReservationDto eliminateInfiniteRecursive(SeatReservationDto seatReservation) {
        seatReservation.getSeat().setAirplane(null);
        seatReservation.getFlightTicket().setBaggages(null);
        seatReservation.getFlightTicket().setFlight(null);
        seatReservation.getFlightTicket().setSeatReservations(null);
        seatReservation.getFlightTicket().setFlightTicketPrices(null);
        return seatReservation;
    }
}
