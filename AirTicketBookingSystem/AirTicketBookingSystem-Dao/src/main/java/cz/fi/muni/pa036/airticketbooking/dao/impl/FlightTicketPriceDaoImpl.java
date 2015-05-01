/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketPriceDao;
import cz.fi.muni.pa036.airticketbooking.entity.FlightTicketPrice;
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
 *
 * @author Lukáš Valach
 */
@Repository
public class FlightTicketPriceDaoImpl implements FlightTicketPriceDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    public FlightTicketPriceDaoImpl() {
    }
    
    @Override
        public FlightTicketPrice getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            FlightTicketPrice objectTemp = (FlightTicketPrice) em.find(FlightTicketPrice.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<FlightTicketPrice> getAll() {
        try {
            Query q = em.createQuery("FROM FlightTicketPrice");
            List<FlightTicketPrice> objectTemp = q.getResultList();

            return Collections.unmodifiableList(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
    @Override
    public void create(FlightTicketPrice entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("This flightTicketPrice entity is already in database.");
        }

        em.persist(entity);
    }

    @Override
    public void update(FlightTicketPrice entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This flightTicketPrice entity cannot have null id.");
        }
        if (em.find(FlightTicketPrice.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This flightTicketPrice entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(FlightTicketPrice entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This flightTicketPrice entity cannot have null id.");
        }
        if (em.find(FlightTicketPrice.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This flightTicketPrice entity does not exist in database.");
        }

        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        
        em.remove(entity);
    }
}
