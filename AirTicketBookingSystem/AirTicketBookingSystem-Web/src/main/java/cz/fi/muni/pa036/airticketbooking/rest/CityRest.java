/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.rest;

import cz.fi.muni.pa036.airticketbooking.api.dto.AirportDto;
import cz.fi.muni.pa036.airticketbooking.api.dto.CityDto;
import cz.fi.muni.pa036.airticketbooking.api.service.CityService;
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
 * @author Lukáš Valach
 */
@RestController
@RequestMapping("/city")
public class CityRest {

    @Autowired
    CityService cityService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CityDto> getCityList() {
        List<CityDto> cityList = cityService.getAll();
        if (cityList == null) {
            cityList = new ArrayList<>();
        }
        return this.eliminateInfiniteRecursive(cityList);
    }

    @RequestMapping(value = "{cityId}", method = RequestMethod.GET)
    public CityDto getCityDetail(@PathVariable Long cityId) {
        CityDto city = cityService.getById(cityId);
        return this.eliminateInfiniteRecursive(city);
    }

    /**
     * Eliminate infinite recurcive City->Airport_set->City, which can't be
     * converted to json
     */
    private List<CityDto> eliminateInfiniteRecursive(List<CityDto> cityList) {
        for (CityDto city : cityList) {
            eliminateInfiniteRecursive(city);
        }
        return cityList;
    }

    /**
     * Eliminate infinite recurcive City->Airport_set->City, which can't be
     * converted to json
     */
    private CityDto eliminateInfiniteRecursive(CityDto city) {
        for (AirportDto airport : city.getAirports()) {
            airport.setCity(null);
        }
        return city;
    }
}
