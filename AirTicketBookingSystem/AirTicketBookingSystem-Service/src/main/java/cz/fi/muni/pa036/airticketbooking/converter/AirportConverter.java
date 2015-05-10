package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Converts Airport entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class AirportConverter {
     
    public static Airport airportDtoToEntity(AirportDto airportDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(airportDto, Airport.class);
    }
    
    public static AirportDto airportEntityToDto(Airport airport) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(airport, AirportDto.class);
    }
    
    public static Set<AirportDto> airportSetToDTO(Set<Airport> airports) {
        if (airports == null) {
            return null;
        }
        
        Set<AirportDto> airportsDto = new HashSet<>();
        for (Airport entry : airports) {
            airportsDto.add(airportEntityToDto(entry));
        }
        return airportsDto;
    }
    
    public static Set<Airport> airportSetToEntity(Set<AirportDto> airportsDto) {
        if (airportsDto == null) {
            return null;
        }
        
        Set<Airport> airports = new HashSet<>();
        for (AirportDto entry : airportsDto) {
            airports.add(airportDtoToEntity(entry));
        }
        return airports;
    }
    
     public static List<AirportDto> airportEntityToDtoList(List<Airport> airportList) {
        List<AirportDto> airportDtoList = new ArrayList<>();
        for (Airport airport : airportList) {
            airportDtoList.add(airportEntityToDto(airport));
        }
        return airportDtoList;
    }
    
     public static List<Airport> airportDtoToEntityList(List<AirportDto> airportDtoList) {
        List<Airport> airportList = new ArrayList<>();
        for (AirportDto airportDto : airportDtoList) {
            airportList.add(airportDtoToEntity(airportDto));
        }
        return airportList;
    }
}
