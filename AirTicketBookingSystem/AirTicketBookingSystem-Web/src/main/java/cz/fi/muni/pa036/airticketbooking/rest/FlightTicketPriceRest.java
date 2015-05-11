package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightTicketPriceService;
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
 * 
 * @author Tomas Smetanka
 */
@RestController
@RequestMapping("/flightTicketPrice")
public class FlightTicketPriceRest {

    @Autowired
    FlightTicketPriceService flightTicketPriceService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<FlightTicketPriceDto> getFlightTicketPriceList() {
        List<FlightTicketPriceDto> flightTicketPriceList = flightTicketPriceService.getAll();
        if (flightTicketPriceList == null) {
            flightTicketPriceList = new ArrayList<>();
        }
        return this.eliminateInfiniteRecursive(flightTicketPriceList);
    }

    @RequestMapping(value = "{flightTicketPriceId}", method = RequestMethod.GET)
    public FlightTicketPriceDto getFlightTicketPriceDetail(@PathVariable Long flightTicketPriceId) {
        FlightTicketPriceDto flightTicketPrice = flightTicketPriceService.getById(flightTicketPriceId);
        return this.eliminateInfiniteRecursive(flightTicketPrice);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void  createFlightTicketPrice(@RequestBody @Valid FlightTicketPriceDto flightTicketPrice) {
  	flightTicketPriceService.create(flightTicketPrice);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateFlightTicketPrice(@RequestBody @Valid FlightTicketPriceDto flightTicketPrice) {
        flightTicketPriceService.update(flightTicketPrice);
    }
    
    @RequestMapping(value = "{flightTicketPriceId}", method = RequestMethod.DELETE)
    public void deleteFlightTicketPrice(@PathVariable Long flightTicketPriceId) {
        FlightTicketPriceDto flightTicketPrice = flightTicketPriceService.getById(flightTicketPriceId);
        flightTicketPriceService.delete(flightTicketPrice);
    }
    
    /**
     * Eliminate infinite recursive FlightTicketPrice->FlightTicketPriceTicket_set/FlightTicketPricePrice_set->FlightTicketPrice, which can't be
     * converted to json
     */
    private List<FlightTicketPriceDto> eliminateInfiniteRecursive(List<FlightTicketPriceDto> flightTicketPriceList) {
        for (FlightTicketPriceDto flightTicketPrice : flightTicketPriceList) {
            eliminateInfiniteRecursive(flightTicketPrice);
        }
        return flightTicketPriceList;
    }

    /**
     * Eliminate infinite recursive FlightTicketPrice->FlightTicketPriceTicket_set/FlightTicketPricePrice_set and others->Fligth and others, which can't be
     * converted to json
     */
    private FlightTicketPriceDto eliminateInfiniteRecursive(FlightTicketPriceDto flightTicketPrice) {
        flightTicketPrice.getFlightTicket().setBaggages(null);
//        flightTicketPrice.getFlightTicket().getFlight().setAirportByAirportToId(null);
//        flightTicketPrice.getFlightTicket().getFlight().setAirportByAirportToId(null);
        eliminateInfiniteRecursiveInFlightPrice((List<FlightPriceDto>) flightTicketPrice.getFlightTicket().getFlight().getFlightPrices());
        flightTicketPrice.getFlightTicket().getFlight().setFlightTickets(null);
        flightTicketPrice.getFlightTicket().getFlight().setPlane(null);
        
        return flightTicketPrice;
    }
    
        /**
     * Eliminate infinite recursive FlightPrice->FlightPriceTicket_set/FlightPricePrice_set->FlightPrice, which can't be
     * converted to json
     */
    private List<FlightPriceDto> eliminateInfiniteRecursiveInFlightPrice(List<FlightPriceDto> flightPriceList) {
        for (FlightPriceDto flightPrice : flightPriceList) {
            flightPrice.setFlight(null);
        }
        return flightPriceList;
    }
}
