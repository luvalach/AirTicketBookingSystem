package cz.fi.muni.pa036.airticketbooking.converter;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Converts Flight entity to DTO and vice versa.
 * 
 * @author Tomas Smetanka
 */
public class FlightConverter {

    public static Flight flightDtoToEntity(FlightDto flightDto) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flightDto, Flight.class);
    }
     
    public static FlightDto flightEntityToDto(Flight flight) {
        Mapper mapper = new DozerBeanMapper();
        return mapper.map(flight, FlightDto.class);
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
