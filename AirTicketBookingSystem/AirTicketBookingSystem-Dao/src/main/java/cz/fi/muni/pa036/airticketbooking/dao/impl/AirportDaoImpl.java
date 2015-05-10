package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.AirportDao;
import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation of Airport.
 * 
 * @author Tomas Smetanka
 */
@Repository
public class AirportDaoImpl implements AirportDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public AirportDaoImpl() {
    }
    
    public AirportDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
    @Override
    public Airport getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Airport objectTemp = (Airport) em.find(Airport.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Airport> getAll() {
        try {
            Query q = em.createQuery("FROM Airport");
            List<Airport> airports = q.getResultList();

            return Collections.unmodifiableList(airports);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}
