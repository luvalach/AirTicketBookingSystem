package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import java.util.Date;
import java.util.List;

/**
 * Interface for Flight DAO.
 * 
 * @author Tomas Smetanka
 */
public interface FlightDao {

    void create(Flight entity);
    
    void update(Flight entity);
    
    void delete(Flight entity);

    Flight getById(Long id);
    
    List<Flight> getAll();
    
    List<Flight> getByFromToAirpot(Airport from, Airport to);
    
    List<Flight> getByFromToAirportAndDepartureDate(Airport from, Airport to, Date departure);
    
    List<Flight> getByPlane(Plane plane);    
    
}
