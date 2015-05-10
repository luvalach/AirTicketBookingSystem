package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import java.util.HashMap;
import java.util.List;

/**
 * Interface fo FlightPrice DAO.
 * 
 * @author Tomas Smetanka
 */
public interface FlightPriceDao {

    void create(FlightPrice entity);
    
    void update(FlightPrice entity);
    
    void delete(FlightPrice entity);
    
    List<FlightPrice> getAll();

    FlightPrice getById(Long id);
    
    FlightPrice getByFlight(Flight flight);
    
    HashMap<Flight, FlightPrice> getByListOfFlights(List<Flight> flights);
    
}
