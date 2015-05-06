package cz.fi.muni.pa036.airticketbooking.util;

import cz.fi.muni.pa036.airticketbooking.entity.Baggage;
import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;
import cz.fi.muni.pa036.airticketbooking.entity.Plane;
import cz.fi.muni.pa036.airticketbooking.entity.Seat;

/**
 * Checks if the mandatory fields are not empty or null.
 * 
 * @author Tomas Smetanka
 */
public class Util {

    public static void validateFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null.") {
            };
        }
        if (flight.getPlane() == null) {
            throw new IllegalArgumentException("Flight's plane is null.") {
            };
        }
        if (flight.getAirportByAirportFromId() == null) {
            throw new IllegalArgumentException("Flight's airport of departure is null.") {
            };
        }
        if (flight.getAirportByAirportToId() == null) {
            throw new IllegalArgumentException("Flight's airport of arrival is null.") {
            };
        }
        if (flight.getCode() == null || "".equals(flight.getCode())) {
            throw new IllegalArgumentException("Flight's code is null or empty.") {
            };
        }
        if (flight.getDeparture() == null) {
            throw new IllegalArgumentException("Flight's departure date is null.") {
            };
        }
        if (flight.getArrival() == null) {
            throw new IllegalArgumentException("Flight's arrival date is null.") {
            };
        }
        if (flight.getFlightPrices() == null) {
            throw new IllegalArgumentException("Flight's prices are not set.") {
            };
        }
    }
    
    public static void validateFlightPrice(FlightPrice flightPrice) {
         if (flightPrice == null) {
            throw new IllegalArgumentException("FlightPrice cannot be null.") {
            };
        }
        if (flightPrice.getFlight() == null) {
            throw new IllegalArgumentException("Flight must be defined for FlightPrice.") {
            };
        }
        if (flightPrice.getAdult() == null || flightPrice.getBaggageA() == null || flightPrice.getSecondClass() == null) {
            throw new IllegalArgumentException("At least price for an adult, for one baggage type (type A) and for a second class must be defined for FlightPrice.") {
            };
        }
    }

    public static void validatePlane(Plane plane) {
        if (plane == null) {
            throw new IllegalArgumentException("Plane cannot be null.") {
            };
        }
        if (plane.getAirline() == null) {
            throw new IllegalArgumentException("Airline must be defined for Plane.") {
            };
        }
        if (plane.getCode()== null) {
            throw new IllegalArgumentException("Code must be defined for Plane.") {
            };
        }
        if (plane.getType() == null) {
            throw new IllegalArgumentException("Type must be defined for Plane.") {
            };
            
        }
        if (plane.getMaxSeats()== null) {
            throw new IllegalArgumentException("Maximum seat count must be defined for Plane.") {
            };
        }
        if (plane.getCreationDate()== null) {
            throw new IllegalArgumentException("Creation date must be defined for Plane.") {
            };
        }
    }

    public static void validateSeat(Seat seat) {
        if (seat == null) {
            throw new IllegalArgumentException("Seat cannot be null.") {
            };
        }
        if (seat.getPlane() == null) {
            throw new IllegalArgumentException("Plane date must be defined for Seat.") {
            };
        }
        if (seat.getInTheMiddle()== null && seat.getNextToAisle() == null && seat.getNextToWindow() == null) {
            throw new IllegalArgumentException("Seat position must be defined for Seat.") {
            };
        }
        if (seat.getFirstClass()== null && seat.getSecondClass()== null && seat.getBusinessClass()== null && seat.getEconomyClass()== null) {
            throw new IllegalArgumentException("Seat class must be defined for Seat.") {
            };
        }
    }

    public static void validateBaggage(Baggage baggage) {
        if (baggage == null) {
            throw new IllegalArgumentException("Baggage cannot be null.") {
            };
        }
        if (baggage.getAmount() == 0) {
            throw new IllegalArgumentException("Baggage amount must be greater than 0 for Seat.") {
            };
        }
        if (baggage.getType().isEmpty()) {
            throw new IllegalArgumentException("Baggage type must be defined for Seat.") {
            };
        }
        if (baggage.getFlightTicket() == null) {
            throw new IllegalArgumentException("Flight ticket must be defined for Seat.") {
            };
        }
    }
    
}
