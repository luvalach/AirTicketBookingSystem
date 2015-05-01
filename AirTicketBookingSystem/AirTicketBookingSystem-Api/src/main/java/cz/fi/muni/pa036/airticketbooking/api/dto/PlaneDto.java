package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lukáš Valach
 */
public class PlaneDto implements java.io.Serializable {

    private int id;
    private AirlineDto airline;
    private String code;
    private String type;
    private Date creationDate;
    private Integer maxSeats;
    private Set flights = new HashSet(0);
    private Set seats = new HashSet(0);

    public PlaneDto() {
    }

    public PlaneDto(int id, AirlineDto airline) {
        this.id = id;
        this.airline = airline;
    }

    public PlaneDto(int id, AirlineDto airline, String code, String type, Date creationDate, Integer maxSeats, Set flights, Set seats) {
        this.id = id;
        this.airline = airline;
        this.code = code;
        this.type = type;
        this.creationDate = creationDate;
        this.maxSeats = maxSeats;
        this.flights = flights;
        this.seats = seats;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AirlineDto getAirlineDto() {
        return this.airline;
    }

    public void set(AirlineDto airline) {
        this.airline = airline;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getMaxSeats() {
        return this.maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Set getFlights() {
        return this.flights;
    }

    public void setFlights(Set flights) {
        this.flights = flights;
    }

    public Set getSeats() {
        return this.seats;
    }

    public void setSeats(Set seats) {
        this.seats = seats;
    }

}
