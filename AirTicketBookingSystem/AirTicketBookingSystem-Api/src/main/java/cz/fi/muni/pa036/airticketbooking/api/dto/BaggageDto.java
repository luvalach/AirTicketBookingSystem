/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.dto;

/**
 *
 * @author Tommy
 */
public class BaggageDto {
    private Long id;
    private String type;
    private Long amount;
    private FlightTicketDto flightTicket;

    public BaggageDto() {
    }

    public BaggageDto(Long id, String type, Long amount, FlightTicketDto flightTicket) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.flightTicket = flightTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public FlightTicketDto getFlightTicket() {
        return flightTicket;
    }

    public void setFlightTicket(FlightTicketDto flightTicket) {
        this.flightTicket = flightTicket;
    }
}
