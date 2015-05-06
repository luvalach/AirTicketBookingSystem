/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tommy
 */
public class AdministratorDto {
    private Long id;
     private String name;
     private String code;
     private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     private AirlineDto airline;

    public AdministratorDto() {
    }

	
    public AdministratorDto(Long id) {
        this.id = id;
    }
    public AdministratorDto(Long id, String name, String code, AirlineDto airline) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.airline = airline;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public AirlineDto getAirline() {
        return this.airline;
    }
    
    public void setAirline(AirlineDto airline) {
        this.airline = airline;
    }


}
