/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Administrator;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface AdministratorDao {
    List<Administrator> getAll();

    Administrator getById(Long id);
}
