package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightPriceDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightPriceService;
import cz.fi.muni.pa036.airticketbooking.converter.FlightConverter;
import cz.fi.muni.pa036.airticketbooking.converter.FlightPriceConverter;
import cz.fi.muni.pa036.airticketbooking.dao.FlightPriceDao;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author Tomas Smetanka
 */
@Service
@Transactional
public class FlightPriceServiceImpl implements FlightPriceService {

    @Autowired
    public FlightPriceDao flightPriceDao;

    public void setDAO(FlightPriceDao flightPriceDao) {
        this.flightPriceDao = flightPriceDao;
    }

    @Override
    public void create(FlightPriceDto entity) {
        FlightPrice flightPrice = FlightPriceConverter.flightPriceDtoToEntity(entity);
        flightPriceDao.create(flightPrice);
        entity.setId(flightPrice.getId());
    }

    @Override
    public void update(FlightPriceDto entity) {
        FlightPrice flightPrice = FlightPriceConverter.flightPriceDtoToEntity(entity);
        flightPriceDao.update(flightPrice);
        entity = FlightPriceConverter.flightPriceEntityToDto(flightPrice);
    }

    @Override
    public void delete(FlightPriceDto entity) {
        FlightPrice flightPrice = FlightPriceConverter.flightPriceDtoToEntity(entity);
        flightPriceDao.delete(flightPrice);
    }

    @Override
    public FlightPriceDto getByFlight(FlightDto flight) {
        FlightPrice flightPrice = new FlightPrice();
        flightPrice = flightPriceDao.getByFlight(FlightConverter.flightDtoToEntity(flight));
        return FlightPriceConverter.flightPriceEntityToDto(flightPrice);
    }

    @Override
    public HashMap<FlightDto, FlightPriceDto> getByListOfFlights(List<FlightDto> flights) {
        Map<Flight, FlightPrice> tempMap = new HashMap<>();
        tempMap = flightPriceDao.getByListOfFlights(FlightConverter.flightDtoToEntityList(flights));
        return FlightConverter.flightFlightPriceMapToDto(tempMap);
    }

}
