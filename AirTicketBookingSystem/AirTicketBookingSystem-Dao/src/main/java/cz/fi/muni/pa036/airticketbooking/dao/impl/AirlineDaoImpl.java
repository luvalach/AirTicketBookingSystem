/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.AirlineDao;
import cz.fi.muni.pa036.airticketbooking.entity.Airline;
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
public class AirlineDaoImpl implements AirlineDao {
     @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public AirlineDaoImpl() {
    }

    @Override
    public Airline getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Airline objectTemp = (Airline) em.find(Airline.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Airline> getAll() {
        try {
            Query q = em.createQuery("FROM Airline");
            List<Airline> objectTemp = q.getResultList();

            return Collections.unmodifiableList(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
