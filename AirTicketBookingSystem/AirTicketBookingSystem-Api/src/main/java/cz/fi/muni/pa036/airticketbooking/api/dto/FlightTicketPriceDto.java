package cz.fi.muni.pa036.airticketbooking.api.dto;
// Generated 28-Apr-2015 10:09:55 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;

/**
 *
 * @author Lukáš Valach
 */
public class FlightTicketPriceDto  implements java.io.Serializable {


     private Long id;
     private FlightTicketDto flightTicket;
     private BigDecimal adult;
     private BigDecimal teen;
     private BigDecimal child;
     private BigDecimal baggageA;
     private BigDecimal baggageB;
     private BigDecimal baggageC;
     private BigDecimal baggageD;
     private BigDecimal baggageE;
     private BigDecimal baggageSport;
     private BigDecimal baggageMusical;
     private BigDecimal firstClass;
     private BigDecimal secondClass;
     private BigDecimal businessClass;
     private BigDecimal economyClass;
     private BigDecimal paymentFee;
     private BigDecimal airportTaxFee;
     private BigDecimal smsFlightInfo;
     private BigDecimal offlineCheckIn;

    public FlightTicketPriceDto() {
    }

	
    public FlightTicketPriceDto(Long id, FlightTicketDto flightTicket) {
        this.id = id;
        this.flightTicket = flightTicket;
    }
    public FlightTicketPriceDto(Long id, FlightTicketDto flightTicket, BigDecimal adult, BigDecimal teen, BigDecimal child, BigDecimal baggageA, BigDecimal baggageB, BigDecimal baggageC, BigDecimal baggageD, BigDecimal baggageE, BigDecimal baggageSport, BigDecimal baggageMusical, BigDecimal firstClass, BigDecimal secondClass, BigDecimal businessClass, BigDecimal economyClass, BigDecimal paymentFee, BigDecimal airportTaxFee, BigDecimal smsFlightInfo, BigDecimal offlineCheckIn) {
       this.id = id;
       this.flightTicket = flightTicket;
       this.adult = adult;
       this.teen = teen;
       this.child = child;
       this.baggageA = baggageA;
       this.baggageB = baggageB;
       this.baggageC = baggageC;
       this.baggageD = baggageD;
       this.baggageE = baggageE;
       this.baggageSport = baggageSport;
       this.baggageMusical = baggageMusical;
       this.firstClass = firstClass;
       this.secondClass = secondClass;
       this.businessClass = businessClass;
       this.economyClass = economyClass;
       this.paymentFee = paymentFee;
       this.airportTaxFee = airportTaxFee;
       this.smsFlightInfo = smsFlightInfo;
       this.offlineCheckIn = offlineCheckIn;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public FlightTicketDto getFlightTicket() {
        return this.flightTicket;
    }
    
    public void setFlightTicket(FlightTicketDto flightTicket) {
        this.flightTicket = flightTicket;
    }
    public BigDecimal getAdult() {
        return this.adult;
    }
    
    public void setAdult(BigDecimal adult) {
        this.adult = adult;
    }
    public BigDecimal getTeen() {
        return this.teen;
    }
    
    public void setTeen(BigDecimal teen) {
        this.teen = teen;
    }
    public BigDecimal getChild() {
        return this.child;
    }
    
    public void setChild(BigDecimal child) {
        this.child = child;
    }
    public BigDecimal getBaggageA() {
        return this.baggageA;
    }
    
    public void setBaggageA(BigDecimal baggageA) {
        this.baggageA = baggageA;
    }
    public BigDecimal getBaggageB() {
        return this.baggageB;
    }
    
    public void setBaggageB(BigDecimal baggageB) {
        this.baggageB = baggageB;
    }
    public BigDecimal getBaggageC() {
        return this.baggageC;
    }
    
    public void setBaggageC(BigDecimal baggageC) {
        this.baggageC = baggageC;
    }
    public BigDecimal getBaggageD() {
        return this.baggageD;
    }
    
    public void setBaggageD(BigDecimal baggageD) {
        this.baggageD = baggageD;
    }
    public BigDecimal getBaggageE() {
        return this.baggageE;
    }
    
    public void setBaggageE(BigDecimal baggageE) {
        this.baggageE = baggageE;
    }
    public BigDecimal getBaggageSport() {
        return this.baggageSport;
    }
    
    public void setBaggageSport(BigDecimal baggageSport) {
        this.baggageSport = baggageSport;
    }
    public BigDecimal getBaggageMusical() {
        return this.baggageMusical;
    }
    
    public void setBaggageMusical(BigDecimal baggageMusical) {
        this.baggageMusical = baggageMusical;
    }
    public BigDecimal getFirstClass() {
        return this.firstClass;
    }
    
    public void setFirstClass(BigDecimal firstClass) {
        this.firstClass = firstClass;
    }
    public BigDecimal getSecondClass() {
        return this.secondClass;
    }
    
    public void setSecondClass(BigDecimal secondClass) {
        this.secondClass = secondClass;
    }
    public BigDecimal getBusinessClass() {
        return this.businessClass;
    }
    
    public void setBusinessClass(BigDecimal businessClass) {
        this.businessClass = businessClass;
    }
    public BigDecimal getEconomyClass() {
        return this.economyClass;
    }
    
    public void setEconomyClass(BigDecimal economyClass) {
        this.economyClass = economyClass;
    }
    public BigDecimal getPaymentFee() {
        return this.paymentFee;
    }
    
    public void setPaymentFee(BigDecimal paymentFee) {
        this.paymentFee = paymentFee;
    }
    public BigDecimal getAirportTaxFee() {
        return this.airportTaxFee;
    }
    
    public void setAirportTaxFee(BigDecimal airportTaxFee) {
        this.airportTaxFee = airportTaxFee;
    }
    public BigDecimal getSmsFlightInfo() {
        return this.smsFlightInfo;
    }
    
    public void setSmsFlightInfo(BigDecimal smsFlightInfo) {
        this.smsFlightInfo = smsFlightInfo;
    }
    public BigDecimal getOfflineCheckIn() {
        return this.offlineCheckIn;
    }
    
    public void setOfflineCheckIn(BigDecimal offlineCheckIn) {
        this.offlineCheckIn = offlineCheckIn;
    }




}


