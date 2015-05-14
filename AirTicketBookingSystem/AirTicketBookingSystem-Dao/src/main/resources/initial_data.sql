DROP SEQUENCE IF EXISTS s_flight_price;
DROP SEQUENCE IF EXISTS s_flight;
DROP SEQUENCE IF EXISTS s_flight_ticket_price;
DROP SEQUENCE IF EXISTS s_flight_ticket;

CREATE SEQUENCE s_flight_price START 501;
CREATE SEQUENCE s_flight START 501;
CREATE SEQUENCE s_flight_ticket_price START 501;
CREATE SEQUENCE s_flight_ticket START 501;

-- Airline basic confirguration
INSERT INTO airline (id, name, code, main_airline) VALUES (1, 'Lufthansa', 'LH', 1);

-- Cities
INSERT INTO city (id, city, country) VALUES (1, 'Prague', 'Czech Republic');
INSERT INTO city (id, city, country) VALUES (2, 'Brno', 'Czech Republic');
INSERT INTO city (id, city, country) VALUES (3, 'Wien', 'Austria');

-- Airports
INSERT INTO airport (id, city_id, name, code) VALUES (1, '1', 'Václav Havel Airport Prague', '1');
INSERT INTO airport (id, city_id, name, code) VALUES (2, '2', 'Brno Airport', '2');
INSERT INTO airport (id, city_id, name, code) VALUES (3, '3', 'Vienna International Airport', '3');

-- Planes
INSERT INTO plane (id, airline_id, code, type, creation_date, max_seats) VALUES (1, 1, '777', 'Boeing 777', '2000-06-16', 283);

-- Flights
INSERT INTO flight (id, plane_id, airport_from_id, airport_to_id, code, departure, arrival) VALUES (1, 1, 1, 3, 'LHPV55', '2015-06-01 15:30:00', '2015-06-01 16:15:00');
INSERT INTO flight (id, plane_id, airport_from_id, airport_to_id, code, departure, arrival) VALUES (2, 1, 2, 3, 'LHBV93', '2015-06-02 09:05:00', '2015-06-02 09:25:00');
INSERT INTO flight (id, plane_id, airport_from_id, airport_to_id, code, departure, arrival) VALUES (3, 1, 3, 1, 'LHVP008', '2015-06-04 10:30:00', '2015-06-04 11:15:00');

-- Flight prices
INSERT INTO flight_price (id, flight_id, adult, teen, child, baggage_a, baggage_musical, first_class, second_class, economy_class, payment_fee, airport_tax_fee, sms_flight_info) 
VALUES (1, 1, 30, 25, 20, 10, 10, 30, 10, 5, 2, 4, 1);

-- Administrators
INSERT INTO administrator (id, airline_id, name, password) VALUES (1, 1, 'admin', 'password');
