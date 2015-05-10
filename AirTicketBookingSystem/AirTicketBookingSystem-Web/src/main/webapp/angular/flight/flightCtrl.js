var flightControllers = angular.module('flightControllers', []);

flightControllers.controller('FlightListCtrl', ['$scope', '$window', 'FlightService', '$log', function ($scope, $window, FlightService, $log) {

    //Table will be ordered by flight departure by default
    $scope.orderByField = 'code';
    //Table will be ordered ascending by default
    $scope.reverseSort = false;
    //Array of locations will be stored here
    $scope.flights = {};

    //refresh flight list
    $scope.refreshflights = function () {
        FlightService("").query(
            function (data, status, headers, config) {
                $scope.flights = data;
                $log.info("List of flights loaded.");
            }, function (data, status, headers, config) { 
                $log.info("An error occurred on server! List of flights cannot be loaded." + data + status + headers + config);
            });
    };

    //Call refresh function after app start
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
}]);


flightControllers.controller('FlightDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'FlightService',
    function ($scope, $routeParams, $window, $log, FlightService) {
        $scope.errorMessages = {};
        
        $scope.flight = FlightService($routeParams.flightId).getFlightDetail(
            function (data, status, headers, config) {
                $log.info("Flight detail loaded.");
                $scope.flight = data;
            },
            function (data, status, headers, config) {
                $log.error("An error occurred on server! Detail of flight cannot be loaded.");
            });

        $scope.goToFlightList = function () {
            $window.location.href = '/AirTicketBooking/#/flight';
        };
    }]);

flightControllers.controller('FlightCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'FlightService', function ($scope, $routeParams, $window, $log, FlightService) {
    $scope.errorMessages = {
        "fieldErrors": []
    };
        
    $scope.flight = {
        "id": null,
        "plane": "",
        "airportByAirportFromId": "",
        "airportByAirportToId": "",
        "code": "",
        "departure": "",
        "arrival": ""
    };

    $scope.goToFlightList = function () {
        $window.location.href = '/AirTicketBooking/#/flight';
    };

    $scope.createFlight = function () {
        $log.info("Creating flight with code: " + $scope.flight.code);
        FlightService("").create($scope.flight,
            function (data, status, headers, config) {
                $log.info("Flight created");
                //$scope.errorMessages = {};
                //$scope.showFlightDetail(data);
            },
            function (data, status, headers, config) {
                $log.error("An error occurred on server! Flight cannot be created.");
                $scope.errorMessages = data.data;
            });
    };

    $scope.showFlightDetail = function (flightId) {
        $window.location.href = '/AirTicketBooking/#/flight/detail/' + flightId;
    };
}]);

var flightServices = angular.module('flightServices', ['ngResource']);
flightServices.factory('FlightService', ['$resource', function ($resource) {
    return function (flight) {
        return $resource('rest/flight/' + flight + ":param", {}, {
            query: {
                method: 'GET', 
                isArray: true
            },
            getFlightDetail: {
                method: 'GET', 
                isArray: false
            },
            create: {
                method: 'POST', 
                isArray: true
            }
        });
    };
}])
;