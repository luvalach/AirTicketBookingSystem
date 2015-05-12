/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.BaggageDto;
import cz.fi.muni.pa036.airticketbooking.api.service.BaggageService;
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
@RequestMapping("/baggage")
public class BaggageRest {

    @Autowired
    BaggageService baggageService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<BaggageDto> getBaggageList() {
        List<BaggageDto> baggageList = baggageService.getAll();
        if (baggageList == null) {
            baggageList = new ArrayList<>();
        }
        return eliminateInfiniteRecursive(baggageList);
    }
    
    @RequestMapping(value = "{baggageId}", method = RequestMethod.GET)
    public BaggageDto getBaggageDetail(@PathVariable Long baggageId) {
        BaggageDto baggage = baggageService.getById(baggageId);
        return eliminateInfiniteRecursive(baggage);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void createBaggage(@RequestBody @Valid BaggageDto baggage) {
        baggageService.create(baggage);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateBaggage(@RequestBody @Valid BaggageDto baggage) {
        baggageService.update(baggage);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteBaggage(@RequestBody @Valid Long baggage) {
        BaggageDto p = baggageService.getById(baggage);
        baggageService.delete(p);
    }
    
      
     private List<BaggageDto> eliminateInfiniteRecursive(List<BaggageDto> baggageList) {
        for (BaggageDto baggage : baggageList) {
            eliminateInfiniteRecursive(baggage);
        }
        return baggageList;
    }

    /**
     * Eliminate infinite recurcive Baggage->Airport_set->Baggage, which can't be
     * converted to json
     */
    private BaggageDto eliminateInfiniteRecursive(BaggageDto baggage) {
        baggage.getFlightTicket().setBaggages(null);
        baggage.getFlightTicket().setFlight(null);
        baggage.getFlightTicket().setSeatReservations(null);
        baggage.getFlightTicket().setFlightTicketPrices(null);
        return baggage;
    }
    
}

