package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Converts Airport entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class AirportConverter {

    public static Airport airportDtoToEntity(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setCity(CityConverter.cityDtoToEntity(airportDto.getCity()));
        airport.setCode(airportDto.getCode());
        airport.setFlightsForAirportFromId(AirportConverter.airportSetToEntity(airportDto.getFlightsForAirportFromId()));
        airport.setFlightsForAirportToId(AirportConverter.airportSetToEntity(airportDto.getFlightsForAirportToId()));
        airport.setId(airportDto.getId());
        airport.setName(airportDto.getName());
        return airport;
    }
     
    public static AirportDto airportEntityToDto(Airport airport) {
        AirportDto airportDto = new AirportDto();
        airportDto.setCity(CityConverter.cityEntityToDto(airport.getCity()));
        airportDto.setCode(airport.getCode());
        airportDto.setFlightsForAirportFromId(AirportConverter.airportSetToDTO(airport.getFlightsForAirportFromId()));
        airportDto.setFlightsForAirportToId(AirportConverter.airportSetToDTO(airport.getFlightsForAirportToId()));
        airportDto.setId(airport.getId());
        airportDto.setName(airport.getName());
        return airportDto;
    }
    
    public static Set<AirportDto> airportSetToDTO(Set<Airport> airports) {
        if (airports == null) {
            return null;
        }
        
        Set<AirportDto> airportsDto = new HashSet<>();
        for (Airport entry : airports) {
            airportsDto.add(AirportConverter.airportEntityToDto(entry));
        }
        return airportsDto;
    }
    
    public static Set<Airport> airportSetToEntity(Set<AirportDto> airportsDto) {
        if (airportsDto == null) {
            return null;
        }
        
        Set<Airport> airports = new HashSet<>();
        for (AirportDto entry : airportsDto) {
            airports.add(AirportConverter.airportDtoToEntity(entry));
        }
        return airports;
    }
    
     public static List<AirportDto> airportEntityToDtoList(List<Airport> airportList) {
        List<AirportDto> airportDtoList = new ArrayList<>();
        for (Airport airport : airportList) {
            airportDtoList.add(AirportConverter.airportEntityToDto(airport));
        }
        return airportDtoList;
    }
    
     public static List<Airport> airportDtoToEntityList(List<AirportDto> airportDtoList) {
        List<Airport> airportList = new ArrayList<>();
        for (AirportDto airportDto : airportDtoList) {
            airportList.add(AirportConverter.airportDtoToEntity(airportDto));
        }
        return airportList;
    }
}
