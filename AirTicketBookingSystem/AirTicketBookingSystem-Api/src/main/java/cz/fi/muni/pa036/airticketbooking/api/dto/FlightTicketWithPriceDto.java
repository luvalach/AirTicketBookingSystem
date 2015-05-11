package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Lukáš Valach
 */
public class FlightTicketWithPriceDto  implements java.io.Serializable {


     private Long id;
     private FlightDto flight;
     private Long nextFlightTicketId;
     private String passanger;
     private String passangerSurname;
     private String passangerMiddleName;
     private String passangerTitle;
     private String passangerResidance;
     private String passangerIdNumeric;
     private String passangerPhoneNumeric;
     private Character checkedIn;
     private Set<SeatReservationDto> seatReservations = new HashSet(0);
     private Set<BaggageDto> baggages = new HashSet(0);
    
     private String age;
     private String ticketClass;
     private BigDecimal paymentFee;
     private BigDecimal airportTaxFee;
     private Boolean smsFlightInfo;
     private Boolean offlineCheckIn;

    public FlightTicketWithPriceDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNextFlightTicketId() {
        return nextFlightTicketId;
    }

    public void setNextFlightTicketId(Long nextFlightTicketId) {
        this.nextFlightTicketId = nextFlightTicketId;
    }

    public String getPassangerName() {
        return passanger;
    }

    public void setPassangerName(String passanger) {
        this.passanger = passanger;
    }

    public String getPassangerSurname() {
        return passangerSurname;
    }

    public void setPassangerSurname(String passangerSurname) {
        this.passangerSurname = passangerSurname;
    }

    public String getPassangerMiddleName() {
        return passangerMiddleName;
    }

    public void setPassangerMiddleName(String passangerMiddleName) {
        this.passangerMiddleName = passangerMiddleName;
    }

    public String getPassangerTitle() {
        return passangerTitle;
    }

    public void setPassangerTitle(String passangerTitle) {
        this.passangerTitle = passangerTitle;
    }

    public String getPassangerResidance() {
        return passangerResidance;
    }

    public void setPassangerResidance(String passangerResidance) {
        this.passangerResidance = passangerResidance;
    }

    public String getPassangerIdNumeric() {
        return passangerIdNumeric;
    }

    public void setPassangerIdNumeric(String passangerIdNumeric) {
        this.passangerIdNumeric = passangerIdNumeric;
    }

    public String getPassangerPhoneNumeric() {
        return passangerPhoneNumeric;
    }

    public void setPassangerPhoneNumeric(String passangerPhoneNumeric) {
        this.passangerPhoneNumeric = passangerPhoneNumeric;
    }

    public Character getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Character checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Set<SeatReservationDto> getSeatReservations() {
        return seatReservations;
    }

    public void setSeatReservations(Set<SeatReservationDto> seatReservations) {
        this.seatReservations = seatReservations;
    }

    public Set<BaggageDto> getBaggages() {
        return baggages;
    }

    public void setBaggages(Set<BaggageDto> baggages) {
        this.baggages = baggages;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public BigDecimal getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(BigDecimal paymentFee) {
        this.paymentFee = paymentFee;
    }

    public BigDecimal getAirportTaxFee() {
        return airportTaxFee;
    }

    public void setAirportTaxFee(BigDecimal airportTaxFee) {
        this.airportTaxFee = airportTaxFee;
    }

    public Boolean getSmsFlightInfo() {
        return smsFlightInfo;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public void setSmsFlightInfo(Boolean smsFlightInfo) {
        this.smsFlightInfo = smsFlightInfo;
    }

    public Boolean getOfflineCheckIn() {
        return offlineCheckIn;
    }

    public void setOfflineCheckIn(Boolean offlineCheckIn) {
        this.offlineCheckIn = offlineCheckIn;
    }




}


