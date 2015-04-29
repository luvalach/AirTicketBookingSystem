package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author Tomas Smetanka
 */
public interface FlightService {

    void create(FlightDto entity);
    
    void update(FlightDto entity);
    
    void delete(FlightDto entity);

    FlightDto getById(Long id);
    
    List<FlightDto> getAll();
    
    List<FlightDto> getByFromToAirpot(AirportDto from, AirportDto to);
    
    List<FlightDto> getByFromToAirportAndDepartureDate(AirportDto from, AirportDto to, Date departure);
    
    List<FlightDto> getByPlane(PlaneDto plane);    
    
}
