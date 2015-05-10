package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AirportService;
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
 * 
 * @author Tomas Smetanka
 */
@RestController
@RequestMapping("/airport")
public class AirportRest {
    
    @Autowired
    AirportService airportService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AirportDto> getAirportList() {
        List<AirportDto> airportList = airportService.getAll();
        if (airportList == null) {
            airportList = new ArrayList<>();
        }
        return this.eliminateInfiniteRecursive(airportList);
    }

    @RequestMapping(value = "{airportId}", method = RequestMethod.GET)
    public AirportDto getCityDetail(@PathVariable Long airportId) {
        AirportDto airport = airportService.getById(airportId);
        return this.eliminateInfiniteRecursive(airport);
    }

    /**
     * Eliminate infinite recursives, which can't be converted to json
     */
    private List<AirportDto> eliminateInfiniteRecursive(List<AirportDto> airportList) {
        for (AirportDto airport : airportList) {
            eliminateInfiniteRecursive(airport);
        }
        return airportList;
    }

    /**
     * Eliminate infinite recurcives, which can't be converted to json
     */
    private AirportDto eliminateInfiniteRecursive(AirportDto airport) {
        
        airport.getCity().setAirports(null);
        airport.setFlightsForAirportFromId(null);
        airport.setFlightsForAirportToId(null);
        
        return airport;
    }
}
