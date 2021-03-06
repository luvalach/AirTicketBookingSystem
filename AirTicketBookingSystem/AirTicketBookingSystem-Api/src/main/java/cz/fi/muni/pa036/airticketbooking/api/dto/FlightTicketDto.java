package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lukáš Valach
 */
public class FlightTicketDto  implements java.io.Serializable {


     private Long id;
     private FlightDto flight;
     private Long nextFlightTicket;
     private String passangerName;
     private String passangerSurname;
     private String passangerMiddleName;
     private String passangerTitle;
     private String passangerResidance;
     private String passangerIdNumeric;
     private String passangerPhoneNumeric;
     private Character checkedIn;
     private Set<FlightTicketPriceDto> flightTicketPrices = new HashSet(0);
     private Set<SeatReservationDto> seatReservations = new HashSet(0);
     private Set<BaggageDto> baggages = new HashSet(0);

    public FlightTicketDto() {
    }

	
    public FlightTicketDto(Long id, FlightDto flight) {
        this.id = id;
        this.flight = flight;
    }
    public FlightTicketDto(Long id, FlightDto flight, Long nextFlightTicket, String passangerName, String passangerSurname, String passangerMiddleName, String passangerTitle, String passangerResidance, String passangerIdNumeric, String passangerPhoneNumeric, Character checkedIn, Set<FlightTicketPriceDto> flightTicketPrices, Set<SeatReservationDto> seatReservations, Set<BaggageDto> baggages) {
       this.id = id;
       this.flight = flight;
       this.nextFlightTicket = nextFlightTicket;
       this.passangerName = passangerName;
       this.passangerSurname = passangerSurname;
       this.passangerMiddleName = passangerMiddleName;
       this.passangerTitle = passangerTitle;
       this.passangerResidance = passangerResidance;
       this.passangerIdNumeric = passangerIdNumeric;
       this.passangerPhoneNumeric = passangerPhoneNumeric;
       this.checkedIn = checkedIn;
       this.flightTicketPrices = flightTicketPrices;
       this.seatReservations = seatReservations;
       this.baggages = baggages;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public FlightDto getFlight() {
        return this.flight;
    }
    
    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }
    public Long getNextFlightTicket() {
        return this.nextFlightTicket;
    }
    
    public void setNextFlightTicket(Long nextFlightTicket) {
        this.nextFlightTicket = nextFlightTicket;
    }
    public String getPassangerName() {
        return this.passangerName;
    }
    
    public void setPassangerName(String passangerName) {
        this.passangerName = passangerName;
    }
    public String getPassangerSurname() {
        return this.passangerSurname;
    }
    
    public void setPassangerSurname(String passangerSurname) {
        this.passangerSurname = passangerSurname;
    }
    public String getPassangerMiddleName() {
        return this.passangerMiddleName;
    }
    
    public void setPassangerMiddleName(String passangerMiddleName) {
        this.passangerMiddleName = passangerMiddleName;
    }
    public String getPassangerTitle() {
        return this.passangerTitle;
    }
    
    public void setPassangerTitle(String passangerTitle) {
        this.passangerTitle = passangerTitle;
    }
    public String getPassangerResidance() {
        return this.passangerResidance;
    }
    
    public void setPassangerResidance(String passangerResidance) {
        this.passangerResidance = passangerResidance;
    }
    public String getPassangerIdNumeric() {
        return this.passangerIdNumeric;
    }
    
    public void setPassangerIdNumeric(String passangerIdNumeric) {
        this.passangerIdNumeric = passangerIdNumeric;
    }
    public String getPassangerPhoneNumeric() {
        return this.passangerPhoneNumeric;
    }
    
    public void setPassangerPhoneNumeric(String passangerPhoneNumeric) {
        this.passangerPhoneNumeric = passangerPhoneNumeric;
    }
    public Character getCheckedIn() {
        return this.checkedIn;
    }
    
    public void setCheckedIn(Character checkedIn) {
        this.checkedIn = checkedIn;
    }
    public Set<FlightTicketPriceDto> getFlightTicketPrices() {
        return this.flightTicketPrices;
    }
    
    public void setFlightTicketPrices(Set<FlightTicketPriceDto> flightTicketPrices) {
        this.flightTicketPrices = flightTicketPrices;
    }
    public Set<SeatReservationDto> getSeatReservations() {
        return this.seatReservations;
    }
    
    public void setSeatReservations(Set<SeatReservationDto> seatReservations) {
        this.seatReservations = seatReservations;
    }
    public Set<BaggageDto> getBaggages() {
        return this.baggages;
    }
    
    public void setBaggages(Set<BaggageDto> baggages) {
        this.baggages = baggages;
    }

    @Override
    public String toString() {
        return "FlightTicketDto{" + "id=" + id + ", flight=" + flight + ", nextFlightTicket=" + nextFlightTicket + ", passangerName=" + passangerName + ", passangerSurname=" + passangerSurname + ", passangerMiddleName=" + passangerMiddleName + ", passangerTitle=" + passangerTitle + ", passangerResidance=" + passangerResidance + ", passangerIdNumeric=" + passangerIdNumeric + ", passangerPhoneNumeric=" + passangerPhoneNumeric + ", checkedIn=" + checkedIn + ", flightTicketPrices=" + flightTicketPrices + ", seatReservations=" + seatReservations + ", baggages=" + baggages + '}';
    }

}


