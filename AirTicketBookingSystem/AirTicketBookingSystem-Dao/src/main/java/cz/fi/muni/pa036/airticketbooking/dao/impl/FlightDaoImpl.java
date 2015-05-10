package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.FlightDao;
import cz.fi.muni.pa036.airticketbooking.entity.Airport;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import cz.fi.muni.pa036.airticketbooking.util.Util;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation of Flight.
 * 
 * @author Tomas Smetanka
 */
@Repository
public class FlightDaoImpl implements FlightDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public FlightDaoImpl() {
    }

    public FlightDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Flight entity) {
        try {
            Util.validateFlight(entity);

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
    public void update(Flight entity) {
        try {
            Util.validateFlight(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This flight entity cannot have null id.");
            }
            if (em.find(Flight.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This flight entity does not exist in database.");
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
    public void delete(Flight entity) {
        try {
            Util.validateFlight(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This flight entity cannot have null id.");
            }
            if (em.find(Flight.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This flight entity does not exist in database.");
            }

            Flight objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Flight getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Flight objectTemp = (Flight) em.find(Flight.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Flight> getAll() {
        try {
            Query q = em.createQuery("FROM Flight");
            List<Flight> flights = q.getResultList();

            return Collections.unmodifiableList(flights);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    // TODO: WHERE plane_id=:plane.getId() ......... alebo .......... WHERE plane:=plane ............ ??
    // TODO: použiť validate..()
    @Override
    public List<Flight> getByFromToAirpot(Airport from, Airport to) {
        try {
            if (from == null) {
                throw new IllegalArgumentException("Departure airport cannot be null.");
            }
            
            if (to == null) {
                throw new IllegalArgumentException("Arrival airport cannot be null.");
            }

            Query q = em.createQuery("FROM Flight WHERE airportFrom=:from AND airportTo=:to");
            q.setParameter("from", from);
            q.setParameter("to", to);
            List<Flight> flights = q.getResultList();

            return Collections.unmodifiableList(flights);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Flight> getByFromToAirportAndDepartureDate(Airport from, Airport to, Date departure) {
        try {
            if (from == null) {
                throw new IllegalArgumentException("Departure airport cannot be null.");
            }
            
            if (to == null) {
                throw new IllegalArgumentException("Arrival airport cannot be null.");
            }
            
            if (departure == null) {
                throw new IllegalArgumentException("Departure date cannot be null.");
            }

            Query q = em.createQuery("FROM Flight WHERE airportFrom=:from AND airportTo=:to AND departure=:departure");
            q.setParameter("from", from);
            q.setParameter("to", to);
            q.setParameter("departure", departure);
            List<Flight> flights = q.getResultList();

            return Collections.unmodifiableList(flights);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Flight> getByPlane(Plane plane) {
        try {
            if (plane == null) {
                throw new IllegalArgumentException("Plane cannot be null.");
            }

            Query q = em.createQuery("FROM Flight WHERE plane_id=:planeId");
            q.setParameter("planeId", plane.getId());
            List<Flight> flights = q.getResultList();

            return Collections.unmodifiableList(flights);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

}
