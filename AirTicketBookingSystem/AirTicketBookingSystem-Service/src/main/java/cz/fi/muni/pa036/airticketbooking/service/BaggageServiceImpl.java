/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.BaggageDto;
import cz.fi.muni.pa036.airticketbooking.api.service.BaggageService;
import cz.fi.muni.pa036.airticketbooking.converter.BaggageConverter;
import cz.fi.muni.pa036.airticketbooking.dao.BaggageDao;
import cz.fi.muni.pa036.airticketbooking.entity.Baggage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tommy
 */
@Service
@Transactional
public class BaggageServiceImpl implements BaggageService {
    
    @Autowired
    private BaggageDao baggageDao;
    
    public void setDAO(BaggageDao baggageDao) {
        this.baggageDao = baggageDao;
    }

    
    @Override
    public void create(BaggageDto entity) {
        Baggage baggage = BaggageConverter.baggageDtoToEntity(entity);
        baggageDao.create(baggage);
        entity.setId(baggage.getId());   
    }

     @Override
    public void update(BaggageDto entity) {
        Baggage baggage = BaggageConverter.baggageDtoToEntity(entity);
        baggageDao.update(baggage);
        entity = BaggageConverter.baggageEntityToDto(baggage);
    }

    @Override
    public void delete(BaggageDto entity) {
        Baggage baggage = BaggageConverter.baggageDtoToEntity(entity);
        baggageDao.delete(baggage);
    }

    @Override
    public BaggageDto getById(Long id) {
        return BaggageConverter.baggageEntityToDto(baggageDao.getById(id));
    }

    @Override
    public List<BaggageDto> getAll() {
        List<Baggage> baggages = new ArrayList<>();
        baggages = baggageDao.getAll();
        return BaggageConverter.baggageEntityToDtoList(baggages);
    }

    @Override
    public List<BaggageDto> getByFlightTicket(Long ticket) {
        return BaggageConverter.baggageEntityToDtoList(baggageDao.getByFlightTicket(ticket));
    }
}
