/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.PlaneDao;
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

/**
 *
 * @author Tommy
 */
public class PlaneDaoImpl implements PlaneDao{
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public PlaneDaoImpl() {
    }
    
    public PlaneDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Plane entity) {
         try {
            Util.validatePlane(entity);

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
    public void update(Plane entity) {
          try {
            Util.validatePlane(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This plane entity cannot have null id.");
            }
            if (em.find(Plane.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This plane entity does not exist in database.");
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
    public void delete(Plane entity) {
        try {
            Util.validatePlane(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This plane entity cannot have null id.");
            }
            if (em.find(Plane.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This plane entity does not exist in database.");
            }

            Plane objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Plane getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Plane objectTemp = (Plane) em.find(Plane.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Plane> getAll() {
        try {
            Query q = em.createQuery("FROM Plane");
            List<Plane> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Plane> getByAirline(Long airline) {
        try {
            Query q = em.createQuery("FROM Plane WHERE airline_id=:airline");
            q.setParameter("airline", airline);
            List<Plane> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}
