package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 * 
 * @author Tomas Smetanka
 */
@RestController
@RequestMapping("/flight")
public class FlightRest {

    @Autowired
    FlightService flightService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<FlightDto> getFlightList() {
        List<FlightDto> flightList = flightService.getAll();
        if (flightList == null) {
            flightList = new ArrayList<>();
        }
        return this.eliminateInfiniteRecursive(flightList);
    }

    @RequestMapping(value = "{flightId}", method = RequestMethod.GET)
    public FlightDto getFlightDetail(@PathVariable Long flightId) {
        FlightDto flight = flightService.getById(flightId);
        return this.eliminateInfiniteRecursive(flight);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void /*List<Long>*/ createFlight(@RequestBody @Valid FlightDto flight) {
  	/*List<Long> resultList = new ArrayList<>();
        resultList.add(*/flightService.create(flight)/*)*/;
        /*return resultList;*/
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void /*FlightDto */updateFlight(@RequestBody @Valid FlightDto flight) {
        //Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        //if (!securityService.hasPermissionToModifyEntity(user.getId())) {
        //    throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot update user " + user.getId());
        //}
        /*return */flightService.update(flight);
    }
    
    @RequestMapping(value = "{flightId}", method = RequestMethod.DELETE)
    public void deleteFlight(@PathVariable Long flightId) {
	//UserDto user = userService.find(userId);
        //Long currentUserId = securityService.getCurrentlyLoggedUser().getId();
        //if (!securityService.hasPermissionToModifyEntity(user.getId())) {
        //    throw new AccessDeniedException("Access denied: User " + currentUserId + " cannot delete user " + user.getId());
        //}
        FlightDto flight = flightService.getById(flightId);
        flightService.delete(flight);
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
        for (FlightTicketDto flightTicket : flight.getFlightTickets()) {
            flightTicket.setFlight(null);
        }
        
        // TODO: NullPointerException
        //for (FlightPriceDto flightPrice : flight.getFlightPrices()) {
        //    flightPrice.setFlight(null);
        //}
        flight.setFlightPrices(null);
        
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
