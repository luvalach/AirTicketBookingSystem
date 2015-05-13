var searchControllers = angular.module('searchControllers', []);

searchControllers.controller('SearchCtrl', ['$scope', '$routeParams', '$window', '$rootScope', '$log', 'SearchService', 'AirportService', function ($scope, $routeParams, $window, $rootScope, $log, SearchService, AirportService) {

        $scope.flight = {
            "id": null,
            "plane": null,
            "airportByAirportFromId": null,
            "airportByAirportToId": null,
            "code": "",
            "departure": "",
            "arrival": ""
        };

        //Table will be ordered by flight departure by default
        $scope.orderByField = 'code';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.flights = {};


//        $scope.refreshflights = function () {
//            SearchService("").query(
//                    function (data, status, headers, config) {
//                        $log.info("Flight(id=" + $routeParams.flightId + ") detail loaded.");
//                        $scope.flight = data;
//                    },
//                    function (data, status, headers, config) {
//                        $log.error("An error occurred on server! Detail of flight(id=" + $routeParams.flightId + ") cannot be loaded.");
//                    })
//        };

        //refresh flight list
        $scope.refreshflights = function () {
            SearchService("").search({from: $routeParams.from, to: $routeParams.to, departure: $routeParams.departure},
            function (data, status, headers, config) {
                $scope.flights = data;
                $log.info("List of flights loaded." + $scope.flights.code);
            },
                    function (data, status, headers, config) {
                        $log.info("An error occurred on server! List of flights cannot be loaded." + data + status + headers + config);
                    });
        };

        $scope.refreshflights();

        $scope.showFlightDetail = function (flightId) {
            $window.location.href = '/AirTicketBooking/#/flight/detail/' + flightId;
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

        $scope.goToCreateFlight = function () {
            $window.location.href = '/AirTicketBooking/#/flight/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/AirTicketBooking/';
        };

        //$log.info("from: " + $scope.airportByAirportFromId.name);
        $log.info("from: " + $routeParams.from);
        $log.info("to: " + $routeParams.to);
        $log.info("departure: " + $routeParams.departure);

    }]);

var searchServices = angular.module('searchServices', ['ngResource']);
searchServices.factory('SearchService', ['$resource', function ($resource) {
        return function (id) {
            return $resource('rest/search/:from/:to/:departure', {}, {
                search: {method: 'GET', isArray: true}
            });
        };
    }]);