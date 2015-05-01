/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.FlightTicketDao;
import cz.fi.muni.pa036.airticketbooking.entity.FlightTicket;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukáš Valach
 */
@Repository
public class FlightTicketDaoImpl implements FlightTicketDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public FlightTicketDaoImpl() {
    }

    @Override
    public FlightTicket getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        FlightTicket objectTemp = (FlightTicket) em.find(FlightTicket.class, id);
        return objectTemp;
    }

    @Override
    public List<FlightTicket> getAll() {
        Query q = em.createQuery("FROM FlightTicket");
        List<FlightTicket> objectTemp = q.getResultList();

        return Collections.unmodifiableList(objectTemp);
    }

    @Override
    public void create(FlightTicket entity) {
        if (entity.getId() != null) {
            throw new IllegalArgumentException("This flightTicket entity is already in database.");
        }

        em.persist(entity);
    }

    @Override
    public void update(FlightTicket entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This flightTicket entity cannot have null id.");
        }
        if (em.find(FlightTicket.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This flightTicket entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(FlightTicket entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This flightTicket entity cannot have null id.");
        }
        if (em.find(FlightTicket.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This flightTicket entity does not exist in database.");
        }

        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        
        em.remove(entity);
    }
}
