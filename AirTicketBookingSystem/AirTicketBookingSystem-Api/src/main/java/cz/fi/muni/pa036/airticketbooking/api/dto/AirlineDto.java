package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lukáš Valach
 */
public class AirlineDto  implements java.io.Serializable {


     private Long id;
     private String name;
     private String code;
     private Character mainAirline;
     private Set administrators = new HashSet(0);
     private Set planes = new HashSet(0);

    public AirlineDto() {
    }

	
    public AirlineDto(Long id) {
        this.id = id;
    }
    public AirlineDto(Long id, String name, String code, Character mainAirline, Set administrators, Set planes) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.mainAirline = mainAirline;
       this.administrators = administrators;
       this.planes = planes;
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
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public Character getMainAirline() {
        return this.mainAirline;
    }
    
    public void setMainAirline(Character mainAirline) {
        this.mainAirline = mainAirline;
    }
    public Set getAdministrators() {
        return this.administrators;
    }
    
    public void setAdministrators(Set administrators) {
        this.administrators = administrators;
    }
    public Set getPlanes() {
        return this.planes;
    }
    
    public void setPlanes(Set planes) {
        this.planes = planes;
    }




}


