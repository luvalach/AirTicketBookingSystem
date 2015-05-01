package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lukáš Valach
 */
public class CityDto  implements java.io.Serializable {


     private Long id;
     private String city;
     private String country;
     private Set airports = new HashSet(0);

    public CityDto() {
    }

	
    public CityDto(Long id) {
        this.id = id;
    }
    public CityDto(Long id, String city, String country, Set airports) {
       this.id = id;
       this.city = city;
       this.country = country;
       this.airports = airports;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public Set getAirports() {
        return this.airports;
    }
    
    public void setAirports(Set airports) {
        this.airports = airports;
    }




}


