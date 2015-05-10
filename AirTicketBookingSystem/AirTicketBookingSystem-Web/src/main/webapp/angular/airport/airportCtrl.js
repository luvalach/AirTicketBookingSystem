var airportControllers = angular.module('airportControllers', []);

airportControllers.controller('AirportListCtrl', ['$scope', '$window', 'AirportService', '$log', function ($scope, $window, AirportService, $log) {

    //Table will be ordered by city name by default
    $scope.orderByField = 'name';
    //Table will be ordered ascending by default
    $scope.reverseSort = false;
    //Array of locations will be stored here
    $scope.airports = {};

    //refresh cities list
    $scope.refreshAirports = function () {
        AirportService("").query(
            function (data, status, headers, config) {
                $scope.airports = data;
                $log.info("List of airports loaded.");
            }, function (data, status, headers, config) {
                $log.info("An error occurred on server! List of airports cannot be loaded.");
            });
    };

    //Call refresh function after app start
    $scope.refreshAirports();
        
    $scope.showAirportDetail = function (cityId) {
        $window.location.href = '/AirTicketBooking/#/airport/detail/' + airportId;
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

    $scope.goToHomePage = function () {
        $window.location.href = '/AirTicketBooking/';
    };
}]);


airportControllers.controller('AirportDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'AirportService',
    function ($scope, $routeParams, $window, $log, AirportService) {
        $scope.errorMessages = {};
        
        $scope.airport = AirportService($routeParams.airportId).getAirportDetail(
            function (data, status, headers, config) {
                $log.info("Airport detail loaded.");
                $scope.airport = data;
            },
            function (data, status, headers, config) {
                $log.error("An error occurred on server! Detail of airport cannot be loaded.");
            });

        $scope.goToAirportList = function () {
            $window.location.href = '/AirTicketBooking/#/airport';
        };
    }]);

var airportServices = angular.module('airportServices', ['ngResource']);
airportServices.factory('AirportService', ['$resource', function ($resource) {
    return function (airport) {
        return $resource('rest/airport/' + airport + ":param", {}, {
            query: {
                method: 'GET', 
                isArray: true
            },
            getAirportDetail: {
                method: 'GET', 
                isArray: false
            }
        });
    };
}])
;