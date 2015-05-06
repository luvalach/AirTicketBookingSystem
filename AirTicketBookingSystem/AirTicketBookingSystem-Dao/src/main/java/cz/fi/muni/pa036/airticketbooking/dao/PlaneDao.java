/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.dao;

import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface PlaneDao {
    void create(Plane entity);
    
    void update(Plane entity);
    
    void delete(Plane entity);

    Plane getById(Long id);
    
    List<Plane> getAll();
    
    List<Plane> getByAirline(Long airline);
    
}
