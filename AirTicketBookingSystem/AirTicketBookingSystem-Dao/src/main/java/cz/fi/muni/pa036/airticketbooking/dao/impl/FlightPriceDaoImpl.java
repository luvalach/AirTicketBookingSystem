package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.FlightPriceDao;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import cz.fi.muni.pa036.airticketbooking.util.Util;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;

/**
 * DAO implementation of FlightPrice.
 * 
 * @author Tomas Smetanka
 */
public class FlightPriceDaoImpl implements FlightPriceDao {

    private EntityManager em;

    public FlightPriceDaoImpl() {
    }

    public FlightPriceDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(FlightPrice entity) {
        try {
            Util.validateFlightPrice(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This flight price entity is already in databse.");
            }

            em.persist(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void update(FlightPrice entity) {
        try {
            Util.validateFlightPrice(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This flight price entity cannot have null id.");
            }
            if (em.find(FlightPrice.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This flight price entity does not exist in database.");
            }

            em.merge(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void delete(FlightPrice entity) {
        try {
            Util.validateFlightPrice(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This flight price entity cannot have null id.");
            }
            if (em.find(FlightPrice.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This flight price entity does not exist in database.");
            }

            FlightPrice objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public FlightPrice getByFlight(Flight flight) {
        try {
            Util.validateFlight(flight);

            if (flight.getId() == null) {
                throw new IllegalArgumentException("This flight entity cannot have null id.");
            }

            Query q = em.createQuery("FROM FlightPrice WHERE flight=:flight");
            q.setParameter("flight", flight);
            FlightPrice flightPrice = (FlightPrice) q.getSingleResult();

            return flightPrice;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public HashMap<Flight, FlightPrice> getByListOfFlights(List<Flight> flights) {
        try {
            for (int i = 0; i < flights.size(); i++) {
                Util.validateFlight(flights.get(i));
                if (flights.get(i).getId() == null) {
                    throw new IllegalArgumentException("This flight entity cannot have null id.");
                }
            }

            Query q = em.createQuery("FROM FlightPrice WHERE flight IN (:flights)");
            q.setParameter("flights", flights);
            List<FlightPrice> flightPrices = q.getResultList();
            
            if (flightPrices.size() != flights.size()) {
                throw new ArrayIndexOutOfBoundsException("The query returned incorrect number od results.");
            }
            
            // TODO: overit či sedí poradie flight[i] = flightProce[i]
            HashMap<Flight, FlightPrice> tempHashMap = new HashMap<>();
            for (int i = 0; i < flights.size(); i++) {
                tempHashMap.put(flights.get(i), flightPrices.get(i));
            }
            
            // TODO: HashMap alebo Map
            return tempHashMap;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    
    
}
