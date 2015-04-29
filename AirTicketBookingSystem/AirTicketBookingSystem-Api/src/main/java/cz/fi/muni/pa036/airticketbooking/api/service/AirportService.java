package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import java.util.List;

/**
 * 
 * 
 * @author Tomas Smetanka
 */
public interface AirportService {

    AirportDto getById(Long id);
    
    List<AirportDto> getAll();
    
}
