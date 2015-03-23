/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.User;
import cz.fi.muni.pa036.airticketbooking.entity.UserRole;

/**
 *
 * @author Lukáš Valach
 */
public interface UserRoleDao {

    void delete(UserRole userRole);

    UserRole find(long id);

    long save(UserRole userRole);

    UserRole update(UserRole userRole);
    
    public UserRole findByUser(User user);
}
