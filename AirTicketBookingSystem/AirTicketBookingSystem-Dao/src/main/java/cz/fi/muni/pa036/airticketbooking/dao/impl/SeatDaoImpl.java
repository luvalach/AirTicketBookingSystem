/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.SeatDao;
import cz.fi.muni.pa036.airticketbooking.entity.Seat;
import cz.fi.muni.pa036.airticketbooking.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Tommy
 */
public class SeatDaoImpl implements SeatDao{
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public SeatDaoImpl() {
    }
    
    public SeatDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Seat entity) {
         try {
            Util.validateSeat(entity);

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
    public void update(Seat entity) {
          try {
            Util.validateSeat(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This seat entity cannot have null id.");
            }
            if (em.find(Seat.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This seat entity does not exist in database.");
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
    public void delete(Seat entity) {
        try {
            Util.validateSeat(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This plane entity cannot have null id.");
            }
            if (em.find(Seat.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This plane entity does not exist in database.");
            }

            Seat objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Seat getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Seat objectTemp = (Seat) em.find(Seat.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Seat> getAll() {
        try {
            Query q = em.createQuery("FROM Seat");
            List<Seat> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Seat> getByPlane(Long plane) {
        try {
            Query q = em.createQuery("FROM Seat WHERE plane_id=:plane");
            q.setParameter("plane", plane);
            List<Seat> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    

}
