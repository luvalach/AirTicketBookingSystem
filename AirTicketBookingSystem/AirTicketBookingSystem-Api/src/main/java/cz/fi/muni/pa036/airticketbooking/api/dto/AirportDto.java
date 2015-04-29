package cz.fi.muni.pa036.airticketbooking.api.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO of Airport entity.
 * 
 * @author Tomas Smetanka
 */
public class AirportDto {
    
    private Long id;
    private CityDto city;
    private String name;
    private String code;
    private Set flightsForAirportFromId = new HashSet(0);
    private Set flightsForAirportToId = new HashSet(0);

    public AirportDto() {
    }

    public AirportDto(CityDto city, String name, String code) {
        this.city = city;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set getFlightsForAirportFromId() {
        return flightsForAirportFromId;
    }

    public void setFlightsForAirportFromId(Set flightsForAirportFromId) {
        this.flightsForAirportFromId = flightsForAirportFromId;
    }

    public Set getFlightsForAirportToId() {
        return flightsForAirportToId;
    }

    public void setFlightsForAirportToId(Set flightsForAirportToId) {
        this.flightsForAirportToId = flightsForAirportToId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AirportDto other = (AirportDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
    
}
