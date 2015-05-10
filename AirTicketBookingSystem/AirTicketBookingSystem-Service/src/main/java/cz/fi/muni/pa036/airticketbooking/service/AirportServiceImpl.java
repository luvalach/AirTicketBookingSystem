package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AirportService;
import cz.fi.muni.pa036.airticketbooking.converter.AirportConverter;
import cz.fi.muni.pa036.airticketbooking.dao.AirportDao;
import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author Tomas Smetanka
 */
@Service
@Transactional
public class AirportServiceImpl implements AirportService {

    private AirportDao airportDao;
    
    public void setDAO(AirportDao airportDao) {
        this.airportDao = airportDao;
    }
    
    @Override
    public AirportDto getById(Long id) {
        Airport airport = new Airport();
        airport = airportDao.getById(id);
        return AirportConverter.airportEntityToDto(airport);
    }

    @Override
    public List<AirportDto> getAll() {
        return AirportConverter.airportEntityToDtoList(airportDao.getAll());
    }

}
