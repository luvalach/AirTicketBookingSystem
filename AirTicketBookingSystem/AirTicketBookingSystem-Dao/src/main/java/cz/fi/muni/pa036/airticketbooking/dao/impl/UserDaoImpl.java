package cz.fi.muni.pa036.airticketbooking.dao.impl;

import cz.fi.muni.pa036.airticketbooking.dao.UserDao;
import cz.fi.muni.pa036.airticketbooking.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * The implementation of a data object for the entity User.
 * 
 * @author Lukáš Valach
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @Override
    public long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void delete(User user) {
		if (!em.contains(user)){
            user = em.merge(user);
        }
        em.remove(user);
    }

    @Override
    public User find(long id) {
        final Query query = em.createQuery("from User where id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findByName(String firstName) {
        final Query query = em.createQuery("from User where firstName = :firstName");
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public List<User> findBySurname(String surname) {
        final Query query = em.createQuery("from User where surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public User findByNick(String nick) {
        final Query query = em.createQuery("from User where nick = :nick");
        query.setParameter("nick", nick);
        return (User) query.getSingleResult();
    }
    
    @Override
    public List<User> findAll() {
        final Query query = em.createQuery("from User");
        return query.getResultList();
    }

}
