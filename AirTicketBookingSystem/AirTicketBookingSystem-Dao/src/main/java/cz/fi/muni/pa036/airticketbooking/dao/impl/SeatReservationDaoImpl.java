/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.SeatReservationDao;
import cz.fi.muni.pa036.airticketbooking.entity.SeatReservation;
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
public class SeatReservationDaoImpl implements SeatReservationDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public SeatReservationDaoImpl() {
    }
    
    public SeatReservationDaoImpl(EntityManager em) {
        this.em = em;
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(SeatReservation entity) {
        try {
            if (entity.getFlightTicket() == null) {
                throw new IllegalArgumentException("Flight ticket must be defined.");
            }
            
            if (entity.getSeat() == null) {
                throw new IllegalArgumentException("Seat number must be defined.");
            }

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
    public void update(SeatReservation entity) {
         try {
             if (entity.getFlightTicket() == null) {
                throw new IllegalArgumentException("Flight ticket must be defined.");
            }
            
            if (entity.getSeat() == null) {
                throw new IllegalArgumentException("Seat number must be defined.");
            }

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This seat reservation entity cannot have null id.");
            }
            if (em.find(SeatReservation.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This seat reservation entity does not exist in database.");
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
    public void delete(SeatReservation entity) {
         try {
            

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This plane entity cannot have null id.");
            }
            if (em.find(SeatReservation.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This plane entity does not exist in database.");
            }

            SeatReservation objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }}

    @Override
    public SeatReservation getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            SeatReservation objectTemp = (SeatReservation) em.find(SeatReservation.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<SeatReservation> getAll() {
         try {
            Query q = em.createQuery("FROM SeatReservation");
            List<SeatReservation> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<SeatReservation> getByTicket(Long ticket) {
         try {
            Query q = em.createQuery("FROM SeatReservation WHERE ticket_id=:ticket");
            q.setParameter("ticket", ticket);
            List<SeatReservation> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<SeatReservation> getBySeat(Long seat) {
          try {
            Query q = em.createQuery("FROM SeatReservation WHERE seat_id=:seat");
            q.setParameter("seat", seat);
            List<SeatReservation> planes = q.getResultList();

            return Collections.unmodifiableList(planes);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}
