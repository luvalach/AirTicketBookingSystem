package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.FlightDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import cz.fi.muni.pa036.airticketbooking.api.service.FlightService;
import cz.fi.muni.pa036.airticketbooking.converter.AirportConverter;
import cz.fi.muni.pa036.airticketbooking.converter.FlightConverter;
import cz.fi.muni.pa036.airticketbooking.converter.PlaneConverter;
import cz.fi.muni.pa036.airticketbooking.dao.FlightDao;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;    

    public void setDAO(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    
    @Override
    public void create(FlightDto entity) {
        Flight flight = FlightConverter.flightDtoToEntity(entity);
        flightDao.create(flight);
        entity.setId(flight.getId());   
    }

    @Override
    public void update(FlightDto entity) {
        Flight flight = FlightConverter.flightDtoToEntity(entity);
        flightDao.update(flight);
        entity = FlightConverter.flightEntityToDto(flight);
    }

    @Override
    public void delete(FlightDto entity) {
        Flight flight = FlightConverter.flightDtoToEntity(entity);
        flightDao.delete(flight);
    }

    @Override
    public FlightDto getById(Long id) {
        return FlightConverter.flightEntityToDto(flightDao.getById(id));
    }

    @Override
    public List<FlightDto> getAll() {
        List<Flight> flights = new ArrayList<>();
        flights = flightDao.getAll();
        return FlightConverter.flightEntityToDtoList(flights);
    }

    @Override
    public List<FlightDto> getByFromToAirpot(AirportDto from, AirportDto to) {
        List<Flight> flights = new ArrayList<>();
        flights = flightDao.getByFromToAirpot(AirportConverter.airportDtoToEntity(from), AirportConverter.airportDtoToEntity(to));
        return FlightConverter.flightEntityToDtoList(flights);
    }

    @Override
    public List<FlightDto> getByFromToAirportAndDepartureDate(AirportDto from, AirportDto to, Date departure) {
        List<Flight> flights = new ArrayList<>();
        flights = flightDao.getByFromToAirportAndDepartureDate(AirportConverter.airportDtoToEntity(from), AirportConverter.airportDtoToEntity(to), departure);
        return FlightConverter.flightEntityToDtoList(flights);
    }

    @Override
    public List<FlightDto> getByPlane(PlaneDto plane) {
        List<Flight> flights = new ArrayList<>();
        flights = flightDao.getByPlane(PlaneConverter.planeDtoToEntity(plane));
        return FlightConverter.flightEntityToDtoList(flights);
    }

}
