/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightTicketWithPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.SeatReservationDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightService;
import cz.fi.muni.pa036.airticketbooking.api.service.TicketReservationService;
import cz.fi.muni.pa036.airticketbooking.converter.FlightTicketConverter;
import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketDao;
import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketPriceDao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
@Transactional
public class TicketReservationServiceImpl implements TicketReservationService {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightTicketDao flightTicketDao;
    
    @Autowired
    FlightTicketPriceDao flightTicketPriceDao;
    
    
    //Přijme FlightTicketWithPriceDto
    //převede ho na FlightTicket a FlightTicketPrice
    //uloží FlightTicket a FlightTicketPrice
    public TicketReservationServiceImpl() {
    }

    @Override
    public List<Long> splitAndSaveFlightTicketList(List<FlightTicketWithPriceDto> flightTicketWithPriceDtoList) {
        int listSize = flightTicketWithPriceDtoList.size();
        Long lastTicketId = null;
        List<Long> ticketIdList = new ArrayList<>();
        for(int i = (listSize-1); i>=0; i--){
            lastTicketId = this.splitAndSaveFlightTicket(flightTicketWithPriceDtoList.get(i), lastTicketId);
            ticketIdList.add(lastTicketId);
        }
        return ticketIdList;
    }
    
    /**
     * Split FlightTicketWithPriceDto into FlightTicket and FlightTicketPrice and save it.
     * @param flightTicketWithPriceDto
     * @return id of saved FlightTicket
     */
    public Long splitAndSaveFlightTicket(FlightTicketWithPriceDto flightTicketWithPriceDto, Long nextTicketId) {
        FlightTicketDto flightTicket = new FlightTicketDto();
        FlightTicketPriceDto flightTicketPrice = new FlightTicketPriceDto();
        
        Set<FlightTicketPriceDto> flightTicketPrices = new HashSet<FlightTicketPriceDto> ();
        flightTicketPrices.add(flightTicketPrice);

        //Get flight prices template
        FlightPriceDto flightPrices = getFlightPrices(flightTicketWithPriceDto.getFlight().getId());
        
        flightTicket.setFlight(this.getFlight(flightTicketWithPriceDto.getFlight().getId()));
        flightTicket.setFlightTicketPrices(flightTicketPrices);
        flightTicket.setPassangerIdNumeric(flightTicketWithPriceDto.getPassangerIdNumeric() == null ? "" : flightTicketWithPriceDto.getPassangerIdNumeric());
        flightTicket.setPassangerMiddleName(flightTicketWithPriceDto.getPassangerMiddleName() == null ? "" : flightTicketWithPriceDto.getPassangerMiddleName());
        flightTicket.setPassangerName(flightTicketWithPriceDto.getPassangerName() == null ? "" : flightTicketWithPriceDto.getPassangerName());
        flightTicket.setPassangerPhoneNumeric(flightTicketWithPriceDto.getPassangerPhoneNumeric() == null ? "" : flightTicketWithPriceDto.getPassangerPhoneNumeric());
        flightTicket.setPassangerResidance(flightTicketWithPriceDto.getPassangerResidance() == null ? "" : flightTicketWithPriceDto.getPassangerResidance());
        flightTicket.setPassangerSurname(flightTicketWithPriceDto.getPassangerSurname() == null ? "" : flightTicketWithPriceDto.getPassangerSurname());
        flightTicket.setPassangerTitle(flightTicketWithPriceDto.getPassangerTitle() == null ? "" : flightTicketWithPriceDto.getPassangerTitle());
        flightTicket.setSeatReservations(flightTicketWithPriceDto.getSeatReservations() == null ? new HashSet<SeatReservationDto>() : flightTicketWithPriceDto.getSeatReservations());
        flightTicket.setNextFlightTicket(nextTicketId == null ? 0 : nextTicketId);
        
        switch (flightTicketWithPriceDto.getAge()) {
            case "adult":
                flightTicketPrice.setAdult(flightPrices.getAdult());
                break;
            case "teen":
                flightTicketPrice.setTeen(flightPrices.getTeen());
                break;
            case "child":
                flightTicketPrice.setChild(flightPrices.getChild());
                break;
            default:
                throw new IllegalArgumentException("Invalid value of filed 'age': " + flightTicketWithPriceDto.getAge());
        }

        switch (flightTicketWithPriceDto.getTicketClass()) {
            case "first":
                flightTicketPrice.setFirstClass(flightPrices.getFirstClass());
                break;
            case "second":
                flightTicketPrice.setSecondClass(flightPrices.getSecondClass());
                break;
            case "business":
                flightTicketPrice.setBusinessClass(flightPrices.getBusinessClass());
                break;
            case "economy":
                flightTicketPrice.setEconomyClass(flightPrices.getEconomyClass());
                break;
            default:
                throw new IllegalArgumentException("Invalid value of filed 'age': " + flightTicketWithPriceDto.getAge());
        }
        
        if (flightTicketWithPriceDto.getOfflineCheckIn() == null) {
            flightTicketPrice.setOfflineCheckIn(new BigDecimal(0));            
        } else {
            if (flightTicketWithPriceDto.getOfflineCheckIn()) {
                flightTicketPrice.setOfflineCheckIn(flightPrices.getOfflineCheckIn());
            } 
        }
        
        if (flightTicketWithPriceDto.getSmsFlightInfo() == null) {
            flightTicketPrice.setSmsFlightInfo(new BigDecimal(0));
        } else {            
            if (flightTicketWithPriceDto.getSmsFlightInfo()) {
                flightTicketPrice.setSmsFlightInfo(flightPrices.getSmsFlightInfo());
            } 
        }

        flightTicketPrice.setAirportTaxFee(flightPrices.getAirportTaxFee() == null ? new BigDecimal(0) : flightPrices.getAirportTaxFee());
        flightTicketPrice.setPaymentFee(flightPrices.getPaymentFee() == null ? new BigDecimal(0) : flightPrices.getPaymentFee());
        flightTicketPrice.setFlightTicket(flightTicket);
        flightTicketDao.create(FlightTicketConverter.flightTicketDtoToEntity(flightTicket));
        //flightTicketPriceDao.create(FlightTicketPriceConverter.flightTicketPriceDtoToEntity(flightTicketPrice));
                
        return flightTicket.getId();
    }

    /**
     * Get flight prices template
     *
     * @param flightId
     * @return
     */
    private FlightPriceDto getFlightPrices(Long flightId) {
        return flightService.getById(flightId).getFlightPrices().iterator().next();
    }
    
    private FlightDto getFlight(Long flightId) {
        return flightService.getById(flightId);
    }

}
