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

        $scope.flight = TicketReservationService($routeParams.flightId).getFlightDetail(
                function (data, status, headers, config) {
                    $log.info("Flight(id=" + $routeParams.flightId + ") detail loaded.");
                    $scope.flight = data;
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
        $scope.editTicket = function () {
            $scope.ticket.flight = $scope.flight;
            $rootScope.tickets.push($scope.ticket);
        };

        //Save new of modified ticket
        $scope.save = function () {
            $scope.setFlightIntoTicket();
            $scope.validateAndSaveTicket();
        };

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
                                $scope.saveButtonText = "Save as new reservation";
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
            $window.location.href = '/AirTicketBooking/#/ticketReservation';
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
                validateTicket: {method: 'POST', isArray: false, url: "rest/ticketReservation/validateTicket"}
            });
        };
    }]);