/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.UserDto;
import java.util.List;

/**
 *
 * @author Lukáš Valach
 */
public interface UserService {

    public long save(UserDto userDto);

    /**
     * Updates given user in database.
     *
     * @param user The location to be updated.
     * @return Updated user.
     */
    public UserDto update(UserDto userDto);

    /**
     * Deletes given user from the database.
     *
     * @param user The user to be deleted.
     */
    public void delete(UserDto userDto);

    /**
     * Finds a user by ID.
     *
     * @param id The ID of the searched user.
     * @return The found user.
     */
    public UserDto find(long id);

    /**
     * Finds all users in the database.
     *
     * @return The list of all users.
     */
    public List<UserDto> findAll();

    /**
     * Finds a user by login name.
     *
     * @param nick The login name.
     * @return The found user.
     */
    public UserDto findByNick(String nick);
}
