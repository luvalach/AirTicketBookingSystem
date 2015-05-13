package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AirportService;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/search")
public class SearchRest {

    @Autowired
    FlightService flightService;
    
    @Autowired
    AirportService airportService;
    
    @RequestMapping(value = "/{from}/{to}/{departure}", method = RequestMethod.GET)
    public List<FlightDto> getFlights(@PathVariable Long from, @PathVariable Long to, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date departure) {
        List<FlightDto> flightList = flightService.getByFromToAirportAndDepartureDate(airportService.getById(from), airportService.getById(to), departure);
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(flightList);
    }
    
    /**
     * Eliminate infinite recursive Flight->FlightTicket_set/FlightPrice_set->Flight, which can't be
     * converted to json
     */
    private List<FlightDto> eliminateInfiniteRecursive(List<FlightDto> flightList) {
        for (FlightDto flight : flightList) {
            eliminateInfiniteRecursive(flight);
        }
        return flightList;
    }

    /**
     * Eliminate infinite recursive Flight->FlightTicket_set/FlightPrice_set->Fligth and others, which can't be
     * converted to json
     */
    private FlightDto eliminateInfiniteRecursive(FlightDto flight) {
        Iterator<FlightPriceDto> flightPricesIterator = flight.getFlightPrices().iterator();
        while(flightPricesIterator.hasNext()){
            flightPricesIterator.next().setFlight(null);
        }
        
        flight.setFlightTickets(null);
        flight.getAirportByAirportFromId().setFlightsForAirportFromId(null);
        flight.getAirportByAirportFromId().setFlightsForAirportToId(null);
        flight.getAirportByAirportFromId().setCity(null);
        flight.getAirportByAirportToId().setFlightsForAirportFromId(null);
        flight.getAirportByAirportToId().setFlightsForAirportToId(null);
        flight.getAirportByAirportToId().setCity(null);
        flight.getPlane().setFlights(null);
        flight.getPlane().setSeats(null);
        
        return flight;
    }
}
