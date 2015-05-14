/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import cz.fi.muni.pa036.airticketbooking.api.service.PlaneService;
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
@RequestMapping("/plane")
public class PlaneRest {

    @Autowired
    PlaneService planeService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<PlaneDto> getPlaneList() {
        List<PlaneDto> flightList = planeService.getAll();
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(flightList);
    }
    
    @RequestMapping(value = "{planeId}", method = RequestMethod.GET)
    public PlaneDto getPlaneDetail(@PathVariable Long planeId) {
        PlaneDto plane = planeService.getById(planeId);
        return eliminateInfiniteRecursive(plane);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createPlane(@RequestBody @Valid PlaneDto plane) {
        planeService.create(plane);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updatePlane(@RequestBody @Valid PlaneDto plane) {
        planeService.update(plane);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePlane(@RequestBody @Valid Long plane) {
        PlaneDto p = planeService.getById(plane);
        planeService.delete(p);
    }
    
    private List<PlaneDto> eliminateInfiniteRecursive(List<PlaneDto> planeList) {
        for (PlaneDto plane : planeList) {
            eliminateInfiniteRecursive(plane);
        }
        return planeList;
    }

    /**
     * Eliminate infinite recurcive Plane->Airport_set->Plane, which can't be
     * converted to json
     */
    private PlaneDto eliminateInfiniteRecursive(PlaneDto plane) {
        for (FlightDto flight : plane.getFlights()) {
            flight.setPlane(null);
            flight.setAirportByAirportFromId(null);
            flight.setAirportByAirportToId(null);
            flight.setFlightPrices(null);
            flight.setFlightTickets(null);
            flight.setPlane(plane);
        }
        //plane.getAirlineDto().setAdministrators(null);
        //plane.getAirlineDto().setPlanes(null);
        for (SeatDto seat : plane.getSeats()) {
            seat.setAirplane(null);
        }
        return plane;
    }
}
