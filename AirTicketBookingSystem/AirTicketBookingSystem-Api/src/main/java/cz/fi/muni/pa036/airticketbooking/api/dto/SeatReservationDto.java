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
public class SeatReservationDto {
    private Long id;
    private SeatDto seat;
    private FlightTicketDto flightTicket;

    public SeatReservationDto() {
    }

    public SeatReservationDto(Long id, SeatDto seat, FlightTicketDto flightTicket) {
        this.id = id;
        this.seat = seat;
        this.flightTicket = flightTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeatDto getSeat() {
        return seat;
    }

    public void setSeat(SeatDto seat) {
        this.seat = seat;
    }

    public FlightTicketDto getFlightTicket() {
        return flightTicket;
    }

    public void setFlightTicket(FlightTicketDto flightTicket) {
        this.flightTicket = flightTicket;
    }
}
