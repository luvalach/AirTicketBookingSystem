package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author Tomas Smetanka
 */
public interface FlightPriceService {

    void create(FlightPriceDto entity);
    
    void update(FlightPriceDto entity);
    
    void delete(FlightPriceDto entity);
    
    FlightPriceDto getByFlight(FlightDto flight);
    
    HashMap<FlightDto, FlightPriceDto> getByListOfFlights(List<FlightDto> flights);
    
}
