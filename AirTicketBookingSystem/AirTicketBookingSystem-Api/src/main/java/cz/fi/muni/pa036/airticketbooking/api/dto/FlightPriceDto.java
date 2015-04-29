package cz.fi.muni.pa036.airticketbooking.api.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO of FlightPrice entity.
 * 
 * @author Tomas Smetanka
 */
public class FlightPriceDto {

    private Long id;
    private FlightDto flight;
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

    public FlightPriceDto() {
    }

    public FlightPriceDto(Long id, FlightDto flight, BigDecimal adult, BigDecimal teen, BigDecimal child, BigDecimal baggageA, BigDecimal baggageB, BigDecimal baggageC, BigDecimal baggageD, BigDecimal baggageE, BigDecimal baggageSport, BigDecimal baggageMusical, BigDecimal firstClass, BigDecimal secondClass, BigDecimal businessClass, BigDecimal economyClass, BigDecimal paymentFee, BigDecimal airportTaxFee, BigDecimal smsFlightInfo, BigDecimal offlineCheckIn) {
        this.id = id;
        this.flight = flight;
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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public BigDecimal getAdult() {
        return adult;
    }

    public void setAdult(BigDecimal adult) {
        this.adult = adult;
    }

    public BigDecimal getTeen() {
        return teen;
    }

    public void setTeen(BigDecimal teen) {
        this.teen = teen;
    }

    public BigDecimal getChild() {
        return child;
    }

    public void setChild(BigDecimal child) {
        this.child = child;
    }

    public BigDecimal getBaggageA() {
        return baggageA;
    }

    public void setBaggageA(BigDecimal baggageA) {
        this.baggageA = baggageA;
    }

    public BigDecimal getBaggageB() {
        return baggageB;
    }

    public void setBaggageB(BigDecimal baggageB) {
        this.baggageB = baggageB;
    }

    public BigDecimal getBaggageC() {
        return baggageC;
    }

    public void setBaggageC(BigDecimal baggageC) {
        this.baggageC = baggageC;
    }

    public BigDecimal getBaggageD() {
        return baggageD;
    }

    public void setBaggageD(BigDecimal baggageD) {
        this.baggageD = baggageD;
    }

    public BigDecimal getBaggageE() {
        return baggageE;
    }

    public void setBaggageE(BigDecimal baggageE) {
        this.baggageE = baggageE;
    }

    public BigDecimal getBaggageSport() {
        return baggageSport;
    }

    public void setBaggageSport(BigDecimal baggageSport) {
        this.baggageSport = baggageSport;
    }

    public BigDecimal getBaggageMusical() {
        return baggageMusical;
    }

    public void setBaggageMusical(BigDecimal baggageMusical) {
        this.baggageMusical = baggageMusical;
    }

    public BigDecimal getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(BigDecimal firstClass) {
        this.firstClass = firstClass;
    }

    public BigDecimal getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(BigDecimal secondClass) {
        this.secondClass = secondClass;
    }

    public BigDecimal getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(BigDecimal businessClass) {
        this.businessClass = businessClass;
    }

    public BigDecimal getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(BigDecimal economyClass) {
        this.economyClass = economyClass;
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

    public BigDecimal getSmsFlightInfo() {
        return smsFlightInfo;
    }

    public void setSmsFlightInfo(BigDecimal smsFlightInfo) {
        this.smsFlightInfo = smsFlightInfo;
    }

    public BigDecimal getOfflineCheckIn() {
        return offlineCheckIn;
    }

    public void setOfflineCheckIn(BigDecimal offlineCheckIn) {
        this.offlineCheckIn = offlineCheckIn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.flight);
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
        final FlightPriceDto other = (FlightPriceDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.flight, other.flight)) {
            return false;
        }
        return true;
    }
    
}
