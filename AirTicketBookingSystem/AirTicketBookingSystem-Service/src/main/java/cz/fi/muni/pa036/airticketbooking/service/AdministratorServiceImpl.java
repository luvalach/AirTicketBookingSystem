/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.service;

import cz.fi.muni.pa036.airticketbooking.api.dto.AdministratorDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AdministratorService;
import cz.fi.muni.pa036.airticketbooking.converter.AdministratorConverter;
import cz.fi.muni.pa036.airticketbooking.dao.AdministratorDao;
import cz.fi.muni.pa036.airticketbooking.entity.Administrator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tommy
 */
public class AdministratorServiceImpl implements AdministratorService {

    private AdministratorDao adminDao;
    
    public void setDAO(AdministratorDao adminDao) {
        this.adminDao = adminDao;
    }
    
    @Override
    public List<AdministratorDto> getAll() {
        List<Administrator> admins = new ArrayList<>();
        admins = adminDao.getAll();
        return AdministratorConverter.administratorEntityToDtoList(admins);
    }

    @Override
    public AdministratorDto getById(Long id) {
        Administrator admin = new Administrator();
        admin = adminDao.getById(id);
        return AdministratorConverter.administratorEntityToDto(admin);
    }
    
}
