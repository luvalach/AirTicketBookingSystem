/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.service.CityService;
import cz.fi.muni.pa036.airticketbooking.api.dto.CityDto;
import cz.fi.muni.pa036.airticketbooking.converter.CityConverter;
import cz.fi.muni.pa036.airticketbooking.dao.CityDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukáš Valach
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao; 

    public CityServiceImpl() {
    }

    @Override
    public CityDto getById(Long id) {
        return CityConverter.cityEntityToDto(cityDao.getById(id));
    }

    @Override
    public List<CityDto> getAll() {
        return CityConverter.cityEntityToDtoList(cityDao.getAll());
    }
}
