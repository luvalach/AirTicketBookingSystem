var ticketReservationControllers = angular.module('ticketReservationControllers', []);

ticketReservationControllers.controller('TicketReservationCtrl', ['$scope', '$window', 'TicketReservationService', '$log', '$routeParams', '$rootScope', '$location', '$timeout', function ($scope, $window, TicketReservationService, $log, $routeParams, $rootScope, $location, $timeout) {
        $log.info("Ticket reservation CTRL");

        $scope.accordianOpen1 = true;
        $scope.accordianOpen2 = false;

        $scope.flightId = $routeParams.flightId;
        $scope.showTicketForm = true;
        $scope.ticketsLength = function () {
            if ($rootScope.tickets != undefined) {
                return $rootScope.tickets.length;
            } else {
                return 0;
            }
        }
        $scope.editMode = false;
        $scope.currency = "$";
        $scope.saveButtonText = "Save as new reservation";
        
         $scope.countSeats = function () {
            var a = 0; var b = 0; var c = 0; var d = 0;
                    for (var i = 0; i < $scope.seats.length; i++) {
                        if ($scope.seats[i].airplane.id === $scope.flight.plane.id) {
                            if (($scope.seats[i].firstClass === "1" && $scope.ticket.ticketClass === "first" ) || 
                                ($scope.seats[i].secondClass === "1" && $scope.ticket.ticketClass === "second" ) || 
                                ($scope.seats[i].businessClass === "1" && $scope.ticket.ticketClass === "business" ) || 
                                ($scope.seats[i].economyClass === "1" && $scope.ticket.ticketClass === "economy" )) {
                                if ($scope.seats[i].nextToWindow === "1") a++;
                                if ($scope.seats[i].nextToAisle === "1") b++;
                                if ($scope.seats[i].inTheMiddle === "1") c++;
                                if ($scope.seats[i].disabledSeating === "1") d++;
                            }
                        }                        
                    }
                    $scope.seatCount = [4];
                    $scope.seatCount[0] = a;
                    $scope.seatCount[1] = b;
                    $scope.seatCount[2] = c;
                    $scope.seatCount[3] = d;
        };
        
        $scope.seats = TicketReservationService($routeParams.flightId).getSats(
                function (data, status, headers, config) {
                    $log.info("Seats loaded.");
                    $scope.seats = data;
                    $scope.seat = "nextToWindow";
                    
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Seats cannot be loaded.");
                });

        $scope.flight = TicketReservationService($routeParams.flightId).getFlightDetail(
                function (data, status, headers, config) {
                    $log.info("Flight(id=" + $routeParams.flightId + ") detail loaded.");
                    $scope.flight = data;
                    $scope.countSeats();
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of flight(id=" + $routeParams.flightId + ") cannot be loaded.");
                });


        $scope.prepareTickets = function () {
            if ($rootScope.tickets == undefined) {
                $rootScope.tickets = [];  //Created ticket which waiting for registration confirmation 
            }
        };
        $scope.prepareTickets();
        $scope.tickets = $rootScope.tickets;

        $scope.searchText = '';
        $scope.orderByField = 'departure';

        $scope.ticket = {
            "id": null,
            "flight": null,
            "nextFlightTicketId": null,
            "passangerName": null,
            "passangerSurname": null,
            "passangerMiddleName": null,
            "passangerTitle": null,
            "passangerResidance": null,
            "passangerIdNumeric": null,
            "passangerPhoneNumeric": null,
            "checkedIn": null,
            "seatReservations": [],
            "baggages": [],
            "age": "adult",
            "ticketClass": "second",
            "paymentFee": null,
            "airportTaxFee": null,
            "smsFlightInfo": false,
            "offlineCheckIn": false
        };

        $scope.setFlightIntoTicket = function () {
            if (!$scope.editMode) {
                $scope.ticket.flight = angular.copy($scope.flight);
            }
        };

        $scope.addNewTicket = function () {
            ticketCopy = angular.copy($scope.ticket)
            $rootScope.tickets.push(ticketCopy);
            $scope.editTicket(ticketCopy);
        };

        //Save modified ticket
//        $scope.editTicket = function () {
//            $scope.ticket.flight = $scope.flight;
//            $rootScope.tickets.push($scope.ticket);
//        };

        //Save new of modified ticket
        $scope.save = function () {
            $scope.setFlightIntoTicket();
            $scope.validateAndSaveTicket();
        };
        
        $scope.getTotalPrice = function() {
            var cost = 0;
            if ($scope.ticket.age === "adult") cost += $scope.flight.flightPrices[0].adult;
            if ($scope.ticket.age === "teen") cost += $scope.flight.flightPrices[0].teen;
            if ($scope.ticket.age === "child") cost += $scope.flight.flightPrices[0].child;
            if ($scope.ticket.baggages[0] > 0) cost += ($scope.flight.flightPrices[0].baggageA * $scope.ticket.baggages[0]);
            if ($scope.ticket.baggages[1] > 0) cost += ($scope.flight.flightPrices[0].baggageB * $scope.ticket.baggages[1]); 
            if ($scope.ticket.baggages[2] > 0) cost += ($scope.flight.flightPrices[0].baggageC * $scope.ticket.baggages[2]); 
            if ($scope.ticket.baggages[3] > 0) cost += ($scope.flight.flightPrices[0].baggageD * $scope.ticket.baggages[3]); 
            if ($scope.ticket.baggages[4] > 0) cost += ($scope.flight.flightPrices[0].baggageE * $scope.ticket.baggages[4]); 
            if ($scope.ticket.baggages[5] > 0) cost += ($scope.flight.flightPrices[0].baggageSport * $scope.ticket.baggages[5]); 
            if ($scope.ticket.baggages[6] > 0) cost += ($scope.flight.flightPrices[0].baggageMusical * $scope.ticket.baggages[6]); 
            if ($scope.ticket.ticketClass === "first") cost += $scope.flight.flightPrices[0].firstClass;
            if ($scope.ticket.ticketClass === "second") cost += $scope.flight.flightPrices[0].secondClass;    
            if ($scope.ticket.ticketClass === "business") cost += $scope.flight.flightPrices[0].businessClass;    
            if ($scope.ticket.ticketClass === "economy") cost += $scope.flight.flightPrices[0].economyClass;
            cost += $scope.flight.flightPrices[0].paymentFee;
            cost += $scope.flight.flightPrices[0].airportTaxFee;
            if ($scope.ticket.smsFlightInfo) cost += $scope.flight.flightPrices[0].smsFlightInfo;
            if ($scope.ticket.offlineCheckIn) cost += $scope.flight.flightPrices[0].offlineCheckIn;
            $scope.ticketCost = cost;
        }


        //Open saved ticket for editing
        $scope.editTicket = function (ticket) {
            $scope.ticket = ticket;
            $scope.flight = ticket.flight;
            $scope.editMode = true;
            $scope.showTicketForm = true;
            $scope.saveButtonText = "Save changes";
        };

        $scope.reserveTickets = function () {
            TicketReservationService("").create($rootScope.tickets,
                    function (data, status, headers, config) {
                        $log.info("Tickets reserved");
                        $scope.errorMessages = {};
                        $rootScope.tickets = [];
                        $scope.tickets = $rootScope.tickets;
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Tickets can't be reserved.");
                        $scope.errorMessages = data.data;
                    });
        };

        $scope.validateAndSaveTicket = function () {
            $scope.saveButtonText = "Validating and saving ticket..."
            TicketReservationService("").validateTicket($scope.ticket,
                    function (data, status, headers, config) {
                        $log.info("Tickets is valid");
                        $scope.errorMessages = {};

                        //Edit if exists or add new
                        if ($scope.editMode) {
                            //$scope.editTicket();
                        } else {
                            $scope.addNewTicket();
                        }

                        if ($scope.editMode) {
                            $scope.saveButtonText = "Save changes";
                        } else {
                            $scope.saveButtonText = "Save as new reservation";
                        }

                        $scope.showTicketForm = false;
                    },
                    function (data, status, headers, config) {
                        $log.warn("Tickets is not valid");
                        $scope.errorMessages = data.data;

                        //Show warning message
                        $scope.saveButtonText = "Validation error"

                        //Wait for a while and change button text back
                        $timeout(function () {
                            if ($scope.editMode) {
                                $scope.saveButtonText = "Save changes";
                            } else {
                                $scope.saveButtonText = "Save reservation";
                            }
                        }, 500);
                    });
        };

        //Sort table by field(column) or switch asc/desc ordering
        $scope.sortByField = function (field) {
            //Switch between asc/desc ordering after click on column according which the table is already sorted.
            if ($scope.orderByField == field) {
                $scope.reverseSort = !$scope.reverseSort;
            }
            //Set column according which the table will be sorted
            $scope.orderByField = field;
        };

        //Set apropriatry icon to indicate ordering
        $scope.getOrderIcon = function (field) {
            if ($scope.orderByField == field) {
                if ($scope.reverseSort) {
                    return 'glyphicon glyphicon-sort-by-attributes-alt';
                }
                else {
                    return 'glyphicon glyphicon-sort-by-attributes';
                }
            }
        };

        $scope.goToSearch = function () {
            $window.location.href = '/AirTicketBooking/#/';
        };

        $scope.nextTicketForThisFlight = function () {
            $scope.showTicketForm = true;
            $scope.editMode = false;
            $scope.ticket = angular.copy($scope.ticket);
            $scope.saveButtonText = "Save reservation";
        };

        $scope.delete = function () {
            if ($scope.editMode) {
                var index = $rootScope.tickets.indexOf($scope.ticket);
                $rootScope.tickets.splice(index, 1);
            }
            $scope.showTicketForm = false;
        };

    }]);

var ticketReservationServices = angular.module('ticketReservationServices', ['ngResource']);
ticketReservationServices.factory('TicketReservationService', ['$resource', function ($resource) {
        return function (id) {
            return $resource('rest/ticketReservation/' + id + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getFlightDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false},
                validateTicket: {method: 'POST', isArray: false, url: "rest/ticketReservation/validateTicket"},
                getSats: {method: 'GET', isArray: true, url: "rest/ticketReservation/getSeats"}
            });
        };
    }]);