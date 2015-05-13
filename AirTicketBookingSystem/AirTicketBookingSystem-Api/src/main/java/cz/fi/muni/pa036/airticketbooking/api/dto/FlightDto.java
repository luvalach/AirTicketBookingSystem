package cz.fi.muni.pa036.airticketbooking.api.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Dto of Flight entity.
 * 
 * @author Tomas Smetanka
 */
public class FlightDto {

    private Long id;
    private PlaneDto plane;
    private AirportDto airportByAirportToId;
    private AirportDto airportByAirportFromId;
    private String code;
    private Date departure;
    private Date arrival;
    private Set<FlightPriceDto> flightPrices = new HashSet(0);
    private Set<FlightTicketDto> flightTickets = new HashSet(0);

    public FlightDto() {
    }

    public FlightDto(Long id, PlaneDto plane, AirportDto airportByAirportToId, AirportDto airportByAirportFromId, String code, Date departure, Date arrival, Set<FlightPriceDto> flightPrices, Set<FlightTicketDto> flightTickets) {
        this.id = id;
        this.plane = plane;
        this.airportByAirportToId = airportByAirportToId;
        this.airportByAirportFromId = airportByAirportFromId;
        this.code = code;
        this.departure = departure;
        this.arrival = arrival;
        this.flightPrices = flightPrices;
        this.flightTickets = flightTickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaneDto getPlane() {
        return plane;
    }

    public void setPlane(PlaneDto plane) {
        this.plane = plane;
    }

    public AirportDto getAirportByAirportToId() {
        return airportByAirportToId;
    }

    public void setAirportByAirportToId(AirportDto airportByAirportToId) {
        this.airportByAirportToId = airportByAirportToId;
    }

    public AirportDto getAirportByAirportFromId() {
        return airportByAirportFromId;
    }

    public void setAirportByAirportFromId(AirportDto airportByAirportFromId) {
        this.airportByAirportFromId = airportByAirportFromId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Set<FlightPriceDto> getFlightPrices() {
        return flightPrices;
    }

    public void setFlightPrices(Set<FlightPriceDto> flightPrices) {
        this.flightPrices = flightPrices;
    }

    public Set<FlightTicketDto> getFlightTickets() {
        return flightTickets;
    }

    public void setFlightTickets(Set<FlightTicketDto> flightTickets) {
        this.flightTickets = flightTickets;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.plane);
        hash = 53 * hash + Objects.hashCode(this.airportByAirportToId);
        hash = 53 * hash + Objects.hashCode(this.airportByAirportFromId);
        hash = 53 * hash + Objects.hashCode(this.code);
        hash = 53 * hash + Objects.hashCode(this.departure);
        hash = 53 * hash + Objects.hashCode(this.arrival);
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
        final FlightDto other = (FlightDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.plane, other.plane)) {
            return false;
        }
        if (!Objects.equals(this.airportByAirportToId, other.airportByAirportToId)) {
            return false;
        }
        if (!Objects.equals(this.airportByAirportFromId, other.airportByAirportFromId)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.departure, other.departure)) {
            return false;
        }
        if (!Objects.equals(this.arrival, other.arrival)) {
            return false;
        }
        return true;
    }
    
}
