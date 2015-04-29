package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import java.util.List;

/**
 * Interface for Airport DAO.
 * 
 * @author Tomas Smetanka
 */
public interface AirportDao {

    Airport getById(Long id);
    
    List<Airport> getAll();
    
}
