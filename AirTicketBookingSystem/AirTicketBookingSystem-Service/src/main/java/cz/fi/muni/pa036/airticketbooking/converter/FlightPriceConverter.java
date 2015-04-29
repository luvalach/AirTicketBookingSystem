package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;

/**
 * Converts FlightPrice entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class FlightPriceConverter {

     public static FlightPrice flightPriceDtoToEntity(FlightPriceDto flightPriceDto) {
        FlightPrice flightPrice = new FlightPrice();
        flightPrice.setAdult(flightPriceDto.getAdult());
        flightPrice.setAirportTaxFee(flightPriceDto.getAirportTaxFee());
        flightPrice.setBaggageA(flightPriceDto.getBaggageA());
        flightPrice.setBaggageB(flightPriceDto.getBaggageB());
        flightPrice.setBaggageC(flightPriceDto.getBaggageC());
        flightPrice.setBaggageD(flightPriceDto.getBaggageD());
        flightPrice.setBaggageE(flightPriceDto.getBaggageE());
        flightPrice.setBaggageMusical(flightPriceDto.getBaggageMusical());
        flightPrice.setBaggageSport(flightPriceDto.getBaggageSport());
        flightPrice.setBusinessClass(flightPriceDto.getBusinessClass());
        flightPrice.setChild(flightPriceDto.getChild());
        flightPrice.setEconomyClass(flightPriceDto.getEconomyClass());
        flightPrice.setFirstClass(flightPriceDto.getFirstClass());
        flightPrice.setFlight(FlightConverter.flightDtoToEntity(flightPriceDto.getFlight()));
        flightPrice.setOfflineCheckIn(flightPriceDto.getOfflineCheckIn());
        flightPrice.setId(flightPriceDto.getId());
        flightPrice.setPaymentFee(flightPriceDto.getPaymentFee());
        flightPrice.setSecondClass(flightPriceDto.getSecondClass());
        flightPrice.setSmsFlightInfo(flightPriceDto.getSmsFlightInfo());
        flightPrice.setTeen(flightPriceDto.getTeen());
        return flightPrice;
    }
     
    public static FlightPriceDto flightPriceEntityToDto(FlightPrice flightPrice) {
        FlightPriceDto flightPriceDto = new FlightPriceDto();
        flightPriceDto.setAdult(flightPrice.getAdult());
        flightPriceDto.setAirportTaxFee(flightPrice.getAirportTaxFee());
        flightPriceDto.setBaggageA(flightPrice.getBaggageA());
        flightPriceDto.setBaggageB(flightPrice.getBaggageB());
        flightPriceDto.setBaggageC(flightPrice.getBaggageC());
        flightPriceDto.setBaggageD(flightPrice.getBaggageD());
        flightPriceDto.setBaggageE(flightPrice.getBaggageE());
        flightPriceDto.setBaggageMusical(flightPrice.getBaggageMusical());
        flightPriceDto.setBaggageSport(flightPrice.getBaggageSport());
        flightPriceDto.setBusinessClass(flightPrice.getBusinessClass());
        flightPriceDto.setChild(flightPrice.getChild());
        flightPriceDto.setEconomyClass(flightPrice.getEconomyClass());
        flightPriceDto.setFirstClass(flightPrice.getFirstClass());
        flightPriceDto.setFlight(FlightConverter.flightEntityToDto(flightPrice.getFlight()));
        flightPriceDto.setOfflineCheckIn(flightPrice.getOfflineCheckIn());
        flightPriceDto.setId(flightPrice.getId());
        flightPriceDto.setPaymentFee(flightPrice.getPaymentFee());
        flightPriceDto.setSecondClass(flightPrice.getSecondClass());
        flightPriceDto.setSmsFlightInfo(flightPrice.getSmsFlightInfo());
        flightPriceDto.setTeen(flightPrice.getTeen());
        return flightPriceDto;
    }
     
}
