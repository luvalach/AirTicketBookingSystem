-- Airline basic confirguration
INSERT INTO airline (id, name, code, main_airline) VALUES (1, 'Lufthansa', 'LH', 1);

-- Administrators
INSERT INTO administrator (id, airline_id, name, password) VALUES (1, 1, 'admin', 'password');