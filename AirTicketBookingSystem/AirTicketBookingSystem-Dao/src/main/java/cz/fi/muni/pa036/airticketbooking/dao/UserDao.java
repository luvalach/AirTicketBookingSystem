package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.User;
import java.util.List;

/**
 * The interface of a data object for the entity User.
 * 
 * @author Lukáš Valach
 */
public interface UserDao {
    
    /**
    * Saves user into database.
    * 
    * @param user The user to be saved.
    * @return ID of the saved user.
    */
    public long save(User user);
    
    /**
    * Updates given user in database.
    * 
    * @param user The location to be updated.
    * @return Updated user.
    */
    public User update(User user);
    
    /**
     * Deletes given user from the database.
     * 
     * @param user The user to be deleted.
     */
    public void delete(User user);

    /**
     * Finds a user by ID.
     * 
     * @param id The ID of the searched user.
     * @return The found user.
     */
    public User find(long id);

    /**
     * Finds a user by firstName.
     * 
     * @param firstName The firstName of the searched user.
     * @return The found user.
     */
    public List<User> findByName(String firstName);

    /**
     * Finds a user by surname.
     * 
     * @param surname The surname of the searched user.
     * @return The found user.
     */
    public List<User> findBySurname(String surname);
    
    /**
     * Finds a user by nick.
     * 
     * @param nick The nick of the searched user.
     * @return The found user
     */
    public User findByNick(String nick);

    /**
     * Finds all users in the database.
     * 
     * @return The list of all users.
     */
    public List<User> findAll();
}
