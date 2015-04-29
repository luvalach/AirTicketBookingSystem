package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts Flight entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class FlightConverter {

    public static Flight flightDtoToEntity(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setAirportByAirportFromId(AirportConverter.airportDtoToEntity(flightDto.getAirportByAirportFromId()));
        flight.setAirportByAirportToId(AirportConverter.airportDtoToEntity(flightDto.getAirportByAirportToId()));
        flight.setArrival(flightDto.getArrival());
        flight.setCode(flightDto.getCode());
        flight.setDeparture(flightDto.getDeparture());
        flight.setFlightPrices(flightDto.getFlightPrices());
        flight.setFlightTickets(flightDto.getFlightTickets());
        flight.setId(flightDto.getId());
        flight.setPlane(PlaneConverter.planeDtoToEntity(flightDto.getPlane()));
        return flight;
    }
     
    public static FlightDto flightEntityToDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setAirportByAirportFromId(AirportConverter.airportEntityToDto(flight.getAirportByAirportFromId()));
        flightDto.setAirportByAirportToId(AirportConverter.airportEntityToDto(flight.getAirportByAirportToId()));
        flightDto.setArrival(flight.getArrival());
        flightDto.setCode(flight.getCode());
        flightDto.setDeparture(flight.getDeparture());
        flightDto.setFlightPrices(flight.getFlightPrices());
        flightDto.setFlightTickets(flight.getFlightTickets());
        flightDto.setId(flight.getId());
        flightDto.setPlane(PlaneConverter.planeEntityToDto(flight.getPlane()));
        return flightDto;
    }
    
     public static List<FlightDto> flightEntityToDtoList(List<Flight> flightList) {
        List<FlightDto> flightDtoList = new ArrayList<>();
        for (Flight flight : flightList) {
            flightDtoList.add(FlightConverter.flightEntityToDto(flight));
        }
        return flightDtoList;
    }
    
     public static List<Flight> flightDtoToEntityList(List<FlightDto> flightDtoList) {
        List<Flight> flightList = new ArrayList<>();
        for (FlightDto flightDto : flightDtoList) {
            flightList.add(FlightConverter.flightDtoToEntity(flightDto));
        }
        return flightList;
    }
    
    public static HashMap<FlightDto, FlightPriceDto> flightFlightPriceMapToDto(Map<Flight, FlightPrice> mapTemp) {
        if (mapTemp == null) {
            return null;
        }
        
        HashMap<FlightDto, FlightPriceDto> mapTempDto = new HashMap<>();
        for (Map.Entry<Flight, FlightPrice> entry : mapTemp.entrySet()) {
            mapTempDto.put(FlightConverter.flightEntityToDto(entry.getKey()), FlightPriceConverter.flightPriceEntityToDto(entry.getValue()));
        }
        
        return mapTempDto;
    }
}
