/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.UserRoleDao;
import cz.fi.muni.pa036.airticketbooking.entity.User;
import cz.fi.muni.pa036.airticketbooking.entity.UserRole;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lukáš Valach
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
   /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(UserRole userRole) {
        em.persist(userRole);
        return userRole.getId();
    }

    @Override
    public UserRole update(UserRole userRole) {
        return em.merge(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
	if (!em.contains(userRole)){
            userRole = em.merge(userRole);
        }
        em.remove(userRole);
    }

    @Override
    public UserRole find(long id) {
        final Query query = em.createQuery("from UserRole where id = :id");
        query.setParameter("id", id);
        return (UserRole) query.getSingleResult();
    }
    
    @Override
    public UserRole findByUser(User user) {
        final Query query = em.createQuery("from UserRole where user = :user");
        query.setParameter("user", user);
        return (UserRole) query.getSingleResult();
    }
}
