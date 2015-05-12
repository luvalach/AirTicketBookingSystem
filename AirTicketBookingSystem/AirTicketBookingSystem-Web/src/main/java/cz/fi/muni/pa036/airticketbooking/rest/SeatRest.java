/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import cz.fi.muni.pa036.airticketbooking.api.service.SeatService;
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
@RequestMapping("/seat")
public class SeatRest {

    @Autowired
    SeatService seatService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<SeatDto> getSeatList() {
        List<SeatDto> flightList = seatService.getAll();
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(flightList);
    }
    
    @RequestMapping(value = "{seatId}", method = RequestMethod.GET)
    public SeatDto getSeatDetail(@PathVariable Long seatId) {
        SeatDto seat = seatService.getById(seatId);
        return eliminateInfiniteRecursive(seat);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createSeat(@RequestBody @Valid SeatDto seat) {
        seatService.create(seat);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateSeat(@RequestBody @Valid SeatDto seat) {
        seatService.update(seat);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteSeat(@RequestBody @Valid Long seat) {
        SeatDto p = seatService.getById(seat);
        seatService.delete(p);
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
