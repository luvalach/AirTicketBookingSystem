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


-- Administrators
INSERT INTO administrator (id, airline_id, name, password) VALUES (1, 1, 'admin', 'password');