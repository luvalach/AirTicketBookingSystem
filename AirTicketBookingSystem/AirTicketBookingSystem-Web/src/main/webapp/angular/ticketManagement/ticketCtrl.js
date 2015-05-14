var ticketControllers = angular.module('ticketControllers', []);

//
//  TICKET LIST CONTROLLER
//
ticketControllers.controller('TicketListCtrl', ['$scope', '$window', '$log', 'TicketService', function ($scope, $window, $log, TicketService) {

        //Table will be ordered by ticket name by default
        $scope.orderByField = 'passangerName';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of tickets will be stored here
        $scope.tickets = {};

        //Refresh ticket list
        $scope.refreshTickets = function () {
            TicketService("").query(
                    function (data, status, headers, config) {
                        $scope.tickets = data;
                        $log.info("List of ticket loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of ticket cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshTickets();

        $scope.showTicketDetail = function (ticketId) {
            $window.location.href = '/AirTicketBooking/#/ticket/' + ticketId;
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
    }]);

//
//  TICKET DETAIL CONTROLLER
//
ticketControllers.controller('TicketDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'TicketService', function ($scope, $routeParams, $window, $log, TicketService) {
        $scope.errorMessages = {};

        $scope.ticket = TicketService($routeParams.ticketId).getTicketDetail(
                function (data, status, headers, config) {
                    $log.info("Ticket detail loaded.");
                    $scope.ticket = data;
                    $scope.ticketBackup = angular.copy($scope.ticket);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of ticket cannot be loaded.");
                });

        $scope.goToTicketList = function () {
            $window.location.href = '/AirTicketBooking/#/ticket';
        };
        
        $scope.goToTicketDetail = function (ticketId) {
            $window.location.href = '/AirTicketBooking/#/ticket/'+ticketId;
        };
        
        $scope.updateTicket = function (ticket) {
            $log.info("Saving ticket with ID: " + ticket.id);
            TicketService("").update(ticket,
                    function (data, status, headers, config) {
                        $log.info("Ticket updated");
                        $scope.errorMessages = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Ticket cannot be updated.");
                        $scope.errorMessages = data.data;
                    });
        };

        $scope.deleteTicket = function (ticket) {
            $log.info("Deleting ticket with ID: " + ticket.id);
            TicketService(ticket.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Ticket deleted");
                        $scope.goToTicketList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Ticket cannot be deleted.");
                    });
        };
    }]);

//
//  TICKET SERVICES 
//
var ticketServices = angular.module('ticketServices', ['ngResource']);
ticketServices.factory('TicketService', ['$resource', function ($resource) {
        return function (ticket) {
            return $resource('rest/ticket/' + ticket + ':param', {}, {
                query: {method: 'GET', isArray: true},
                getTicketDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }])
        ;


