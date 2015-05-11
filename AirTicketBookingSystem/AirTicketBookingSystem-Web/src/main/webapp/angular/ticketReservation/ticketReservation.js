var ticketReservationControllers = angular.module('ticketReservationControllers', []);

ticketReservationControllers.controller('TicketReservationCtrl', ['$scope', '$window', 'TicketReservationService', '$log', '$routeParams', '$rootScope', '$location', function ($scope, $window, TicketReservationService, $log, $routeParams, $rootScope, $location) {
        $log.info("Ticket reservation CTRL");

        $scope.accordianOpen1 = true;
        $scope.accordianOpen2 = false;

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

//        $scope.ticket = [
//            {id: null},
//            {flight_id: null},
//            {next_flight_ticket: null},
//            {passager_name: ''},
//            {passager_surname: ''},
//            {passager_middle_name: ''},
//            {passager_title: ''},
//            {passager_residance: ''},
//            {passager_id_number: ''},
//            {passager_phone_number: ''},
//            {checked_in: ''},
//            {flightTicketPrices: null},
//            {seatReservations: null},
//            {baggages: null}
//        ];

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
            "age": null,
            "ticketClass": null,
            "paymentFee": null,
            "airportTaxFee": null,
            "smsFlightInfo": null,
            "offlineCheckIn": null
        };

        $scope.addNewTicket = function () {
            $scope.ticket.flight = angular.copy($scope.flight);
            $rootScope.tickets.push(angular.copy($scope.ticket));
        };

        $scope.editTicket = function () {
            $scope.ticket.flight = $scope.flight;
            $rootScope.tickets.push($scope.ticket);
        };

        $scope.save = function () {
            if ($scope.editMode) {
                $scope.editTicket();
            } else {
                $scope.addNewTicket();
            }
        };

        $scope.editTicket = function (ticket) {
            $scope.ticket = ticket;
            $scope.flight = ticket.flight;
            $scope.editMode = true;
            $scope.saveButtonText = "Save changes";
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
    }]);

var ticketReservationServices = angular.module('ticketReservationServices', ['ngResource']);
ticketReservationServices.factory('TicketReservationService', ['$resource', function ($resource) {
        return function (id) {
            return $resource('rest/ticketReservation/' + id + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getFlightDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }])
        ;