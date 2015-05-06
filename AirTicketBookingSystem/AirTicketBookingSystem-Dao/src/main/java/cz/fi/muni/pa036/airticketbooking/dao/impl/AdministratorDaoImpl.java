/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.AdministratorDao;
import cz.fi.muni.pa036.airticketbooking.entity.Administrator;
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
public class AdministratorDaoImpl implements AdministratorDao {
     @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public AdministratorDaoImpl() {
    }

    @Override
    public Administrator getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Administrator objectTemp = (Administrator) em.find(Administrator.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Administrator> getAll() {
        try {
            Query q = em.createQuery("FROM Administrator");
            List<Administrator> objectTemp = q.getResultList();

            return Collections.unmodifiableList(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
