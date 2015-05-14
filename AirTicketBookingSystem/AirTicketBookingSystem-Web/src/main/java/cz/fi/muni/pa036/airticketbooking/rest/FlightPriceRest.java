package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightPriceService;
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
@RequestMapping("/flightPrice")
public class FlightPriceRest {

    @Autowired
    FlightPriceService flightPriceService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<FlightPriceDto> getFlightPriceList() {
        List<FlightPriceDto> flightPriceList = flightPriceService.getAll();
        if (flightPriceList == null) {
            flightPriceList = new ArrayList<>();
        }
        return this.eliminateInfiniteRecursive(flightPriceList);
    }

    @RequestMapping(value = "{flightPriceId}", method = RequestMethod.GET)
    public FlightPriceDto getFlightPriceDetail(@PathVariable Long flightPriceId) {
        FlightPriceDto flightPrice = flightPriceService.getById(flightPriceId);
        return this.eliminateInfiniteRecursive(flightPrice);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void  createFlightPrice(@RequestBody @Valid FlightPriceDto flightPrice) {
  	flightPriceService.create(flightPrice);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateFlightPrice(@RequestBody @Valid FlightPriceDto flightPrice) {
        flightPriceService.update(flightPrice);
    }
    
    @RequestMapping(value = "{flightPriceId}", method = RequestMethod.DELETE)
    public void deleteFlightPrice(@PathVariable Long flightPriceId) {
        FlightPriceDto flightPrice = flightPriceService.getById(flightPriceId);
        flightPriceService.delete(flightPrice);
    }
    
    /**
     * Eliminate infinite recursive FlightPrice->FlightPriceTicket_set/FlightPricePrice_set->FlightPrice, which can't be
     * converted to json
     */
    private List<FlightPriceDto> eliminateInfiniteRecursive(List<FlightPriceDto> flightPriceList) {
        for (FlightPriceDto flightPrice : flightPriceList) {
            eliminateInfiniteRecursive(flightPrice);
        }
        return flightPriceList;
    }

    /**
     * Eliminate infinite recursive FlightPrice->FlightPriceTicket_set/FlightPricePrice_set and others->Fligth and others, which can't be
     * converted to json
     */
    private FlightPriceDto eliminateInfiniteRecursive(FlightPriceDto flightPrice) {
        if (flightPrice.getFlight() != null) {
            flightPrice.getFlight().setAirportByAirportFromId(null);
            flightPrice.getFlight().setAirportByAirportToId(null);
            flightPrice.getFlight().setFlightPrices(null);
            flightPrice.getFlight().setFlightTickets(null);
            flightPrice.getFlight().setPlane(null);
        }
        
        return flightPrice;
    }
}
