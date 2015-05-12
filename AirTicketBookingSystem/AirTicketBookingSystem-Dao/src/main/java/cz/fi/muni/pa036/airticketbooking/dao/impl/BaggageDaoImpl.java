/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.BaggageDao;
import cz.fi.muni.pa036.airticketbooking.entity.Baggage;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import cz.fi.muni.pa036.airticketbooking.util.Util;
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
 * @author Tommy
 */
@Repository
public class BaggageDaoImpl implements BaggageDao{
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public BaggageDaoImpl() {
    }
    
    public BaggageDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Baggage entity) {
         try {
            Util.validateBaggage(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This flight entity is already in database.");
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
    public void update(Baggage entity) {
          try {
            Util.validateBaggage(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This baggage entity cannot have null id.");
            }
            if (em.find(Plane.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This baggage entity does not exist in database.");
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
    public void delete(Baggage entity) {
        try {
            Util.validateBaggage(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This plane entity cannot have null id.");
            }
            if (em.find(Plane.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This plane entity does not exist in database.");
            }

            Baggage objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Baggage getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Baggage objectTemp = (Baggage) em.find(Baggage.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Baggage> getAll() {
        try {
            Query q = em.createQuery("FROM Plane");
            List<Baggage> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Baggage> getByFlightTicket(Long ticket) {
        try {
            Query q = em.createQuery("FROM Baggage WHERE flight_ticket_id=:ticket");
            q.setParameter("ticket", ticket);
            List<Baggage> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}
