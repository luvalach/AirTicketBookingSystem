/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Airline;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface AirlineDao {
    List<Airline> getAll();

    Airline getById(Long id);
}
