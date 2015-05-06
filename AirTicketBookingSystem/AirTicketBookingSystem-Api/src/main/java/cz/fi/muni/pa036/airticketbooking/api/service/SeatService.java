/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.SeatDto;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface SeatService {
     void create(SeatDto entity);
    
    void update(SeatDto entity);
    
    void delete(SeatDto entity);

    SeatDto getById(Long id);
    
    List<SeatDto> getAll();
    
    List<SeatDto> getByPlane(Long plane);
}
