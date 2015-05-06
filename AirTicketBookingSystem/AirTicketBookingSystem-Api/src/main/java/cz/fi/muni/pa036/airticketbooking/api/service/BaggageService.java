/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.BaggageDto;
import java.util.List;

/**
 *
 * @author Tommy
 */
public interface BaggageService {
     void create(BaggageDto entity);
    
    void update(BaggageDto entity);
    
    void delete(BaggageDto entity);

    BaggageDto getById(Long id);
    
    List<BaggageDto> getAll();
    
    List<BaggageDto> getByFlightTicket(Long ticket);
}
