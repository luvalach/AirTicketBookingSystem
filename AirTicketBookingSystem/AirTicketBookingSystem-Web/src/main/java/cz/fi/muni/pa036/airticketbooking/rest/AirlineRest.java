/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirlineDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AirlineService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tommy
 */
    @RestController
@RequestMapping("/airline")
public class AirlineRest {

    @Autowired
    AirlineService airlineService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AirlineDto> getAirlineList() {
        List<AirlineDto> airlineList = airlineService.getAll();
        if (airlineList == null) {
            airlineList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(airlineList);
    }
    
    @RequestMapping(value = "{airline}", method = RequestMethod.GET)
    public AirlineDto getAirlineDetail(@PathVariable Long airlineId) {
        AirlineDto airline = airlineService.getById(airlineId);
        return eliminateInfiniteRecursive(airline);
    }
    
     private List<AirlineDto> eliminateInfiniteRecursive(List<AirlineDto> airlineList) {
        for (AirlineDto airline : airlineList) {
            eliminateInfiniteRecursive(airline);
        }
        return airlineList;
    }

    /**
     * Eliminate infinite recurcive Airline->Airport_set->Airline, which can't be
     * converted to json
     */
    private AirlineDto eliminateInfiniteRecursive(AirlineDto airline) {
        for (PlaneDto plane : airline.getPlanes()) {
            plane.setFlights(null);
            plane.setSeats(null);
            plane.set(null);
        }
        return airline;
    }
    
}
