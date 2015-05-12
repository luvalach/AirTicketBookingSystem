/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.AdministratorDto;
import cz.fi.muni.pa036.airticketbooking.api.service.AdministratorService;
import cz.fi.muni.pa036.airticketbooking.api.service.SecurityService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tommy
 */
@RestController
@RequestMapping("/administrator")
public class AdministratorRest {

    @Autowired
    AdministratorService adminService;
    
    @Autowired
    SecurityService securityService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<AdministratorDto> getPlaneList() {
        List<AdministratorDto> adminList = adminService.getAll();
        if (adminList == null) {
            adminList = new ArrayList<>();
        }
        return adminList;
    }
    
    @RequestMapping(value = "{administratorId}", method = RequestMethod.GET)
    public AdministratorDto getPlaneDetail(@PathVariable Long administratorId) {
        AdministratorDto plane = adminService.getById(administratorId);
        return plane;
    }
    
}