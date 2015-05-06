/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface PlaneService {
    void create(PlaneDto entity);
    
    void update(PlaneDto entity);
    
    void delete(PlaneDto entity);

    PlaneDto getById(Long id);
    
    List<PlaneDto> getAll();
    
    List<PlaneDto> getByAirline(Long airline);
}
