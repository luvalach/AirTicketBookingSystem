/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.PlaneDto;
import cz.fi.muni.pa036.airticketbooking.api.service.PlaneService;
import cz.fi.muni.pa036.airticketbooking.converter.PlaneConverter;
import cz.fi.muni.pa036.airticketbooking.dao.PlaneDao;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class PlaneServiceImpl implements PlaneService{
    
    private PlaneDao planeDao;
    
    public void setDAO(PlaneDao planeDao) {
        this.planeDao = planeDao;
    }
    

    @Override
    public void create(PlaneDto entity) {
        Plane plane = PlaneConverter.planeDtoToEntity(entity);
        planeDao.create(plane);
        entity.setId(plane.getId());   
    }

     @Override
    public void update(PlaneDto entity) {
        Plane plane = PlaneConverter.planeDtoToEntity(entity);
        planeDao.update(plane);
        entity = PlaneConverter.planeEntityToDto(plane);
    }

    @Override
    public void delete(PlaneDto entity) {
        Plane plane = PlaneConverter.planeDtoToEntity(entity);
        planeDao.delete(plane);
    }

    @Override
    public PlaneDto getById(Long id) {
        return PlaneConverter.planeEntityToDto(planeDao.getById(id));
    }

    @Override
    public List<PlaneDto> getAll() {
        List<Plane> planes = new ArrayList<>();
        planes = planeDao.getAll();
        return PlaneConverter.planeEntityToDtoList(planes);
    }


    @Override
    public List<PlaneDto> getByAirline(Long airline) {
        List<Plane> planes = new ArrayList<>();
        planes = planeDao.getByAirline(airline);
        return PlaneConverter.planeEntityToDtoList(planes);
    }
    
}
