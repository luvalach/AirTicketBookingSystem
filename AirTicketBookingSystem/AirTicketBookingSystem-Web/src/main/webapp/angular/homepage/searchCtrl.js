var searchControllers = angular.module('searchControllers', []);

searchControllers.controller('SearchCtrl', ['$scope', '$routeParams', '$window', '$rootScope', '$log', 'FlightService', 'AirportService', function ($scope, $routeParams, $window, $rootScope, $log, FlightService, AirportService) {
    
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
    
    //refresh flight list
    $scope.refreshflights = function () {            
        FlightService($routeParams.from, $routeParams.to, $routeParams.departure).search(
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
searchServices.factory('FlightService', ['$resource', function ($resource) {
    return function (search) {
//        return $resource('rest/search/:from/:to/:departure', {}, {
        return $resource('rest/search/:from/:to/:departure', {from: '@from', to: '@to', departure: '@departure'}, {
//        return $resource('rest/search/' + search + ":param", {}, {
            search: {
                method: 'GET', 
//                params: {from: '@from', to: '@to', departure: '@departure'},
                isArray: true
            }
        });
    };
}]);