package cz.fi.muni.pa036.airticketbooking.util;

import cz.fi.muni.pa036.airticketbooking.entity.Flight;
import cz.fi.muni.pa036.airticketbooking.entity.FlightPrice;

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
    
}
