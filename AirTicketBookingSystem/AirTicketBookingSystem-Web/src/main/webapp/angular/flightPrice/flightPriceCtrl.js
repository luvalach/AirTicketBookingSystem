var flightPriceControllers = angular.module('flightPriceControllers', []);

flightPriceControllers.controller('FlightPriceListCtrl', ['$scope', '$window', 'FlightPriceService', '$log', function ($scope, $window, FlightPriceService, $log) {

    //Table will be ordered by flightPrice departure by default
    $scope.orderByField = 'flight.code';
    //Table will be ordered ascending by default
    $scope.reverseSort = false;
    //Array of locations will be stored here
    $scope.flightPrices = {};

    //refresh flightPrice list
    $scope.refreshflightPrices = function () {
        FlightPriceService("").query(
            function (data, status, headers, config) {
                $scope.flightPrices = data;
                $log.info("List of flightPrices loaded.");
            }, function (data, status, headers, config) { 
                $log.info("An error occurred on server! List of flightPrices cannot be loaded." + data + status + headers + config);
            });
    };

    //Call refresh function after app start
    $scope.refreshflightPrices();
        
    $scope.showFlightPriceDetail = function (flightPriceId) {
        $window.location.href = '/AirTicketBooking/#/flightPrice/detail/' + flightPriceId;
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

    $scope.goToCreateFlightPrice = function () {
        $window.location.href = '/AirTicketBooking/#/flightPrice/create';
    };
        
    $scope.goToHomePage = function () {
        $window.location.href = '/AirTicketBooking/';
    };
}]);


flightPriceControllers.controller('FlightPriceDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'FlightPriceService',
    function ($scope, $routeParams, $window, $log, FlightPriceService) {
        $scope.errorMessages = {};
        
        $scope.flightPrice = FlightPriceService($routeParams.flightPriceId).getFlightPriceDetail(
            function (data, status, headers, config) {
                $log.info("FlightPrice detail loaded.");
                $scope.flightPrice = data;
            },
            function (data, status, headers, config) {
                $log.error("An error occurred on server! Detail of flightPrice cannot be loaded.");
            });

        $scope.goToFlightPriceList = function () {
            $window.location.href = '/AirTicketBooking/#/flightPrice';
        };
    }]);

flightPriceControllers.controller('FlightPriceCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'FlightPriceService', function ($scope, $routeParams, $window, $log, FlightPriceService) {
    $scope.errorMessages = {
        "fieldErrors": []
    };
        
    $scope.flightPrice = {
        "id": null,
        "plane": "",
        "adult": "",
        "teen": "",
        "child": "",
        "baggageA": "",
        "baggageB": "",
        "baggageC": "",
        "baggageD": "",
        "baggageE": "",
        "baggageMusical": "",
        "baggageSport": "",
        "firstClass": "",
        "secondClass": "",
        "economyClass": "",
        "businessClass": "",
        "paymentFee": "",
        "airportTaxFee": "",
        "smsFlightInfo": "",
        "offlineCheckIn": ""
    };

    $scope.goToFlightPriceList = function () {
        $window.location.href = '/AirTicketBooking/#/flightPrice';
    };

    $scope.createFlightPrice = function () {
        $log.info("Creating flightPrice with code: " + $scope.flightPrice.code);
        FlightPriceService("").create($scope.flightPrice,
            function (data, status, headers, config) {
                $log.info("FlightPrice created");
                //$scope.errorMessages = {};
                //$scope.showFlightPriceDetail(data);
            },
            function (data, status, headers, config) {
                $log.error("An error occurred on server! FlightPrice cannot be created.");
                $scope.errorMessages = data.data;
            });
    };

    $scope.showFlightPriceDetail = function (flightPriceId) {
        $window.location.href = '/AirTicketBooking/#/flightPrice/detail/' + flightPriceId;
    };
}]);

var flightPriceServices = angular.module('flightPriceServices', ['ngResource']);
flightPriceServices.factory('FlightPriceService', ['$resource', function ($resource) {
    return function (flightPrice) {
        return $resource('rest/flightPrice/' + flightPrice + ":param", {}, {
            query: {
                method: 'GET', 
                isArray: true
            },
            getFlightPriceDetail: {
                method: 'GET', 
                isArray: false
            }
        });
    };
}])
;