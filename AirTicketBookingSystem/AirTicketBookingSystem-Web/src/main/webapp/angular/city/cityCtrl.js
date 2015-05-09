var cityControllers = angular.module('cityControllers', []);

cityControllers.controller('CityListCtrl', ['$scope', '$window', 'CityService', '$log', function ($scope, $window, CityService, $log) {

        //Table will be ordered by city name by default
        $scope.orderByField = 'city';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.cities = {};

        //refresh cities list
        $scope.refreshCities = function () {
            CityService("").query(
                    function (data, status, headers, config) {
                        $scope.cities = data;
                        $log.info("List of cities loaded.");
                    }, function (data, status, headers, config) {
                $log.info("An error occurred on server! List of cities cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshCities();
        
        $scope.showCityDetail = function (cityId) {
            $window.location.href = '/AirTicketBooking/#/city/detail/' + cityId;
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


cityControllers.controller('CityDetailCtrl', ['$scope', '$routeParams', '$window', '$log',
    'CityService',
    function ($scope, $routeParams, $window, $log, CityService) {
        $scope.errorMessages = {};
        
        $scope.city = CityService($routeParams.cityId).getCityDetail(
                function (data, status, headers, config) {
                    $log.info("City detail loaded.");
                    $scope.city = data;
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of city cannot be loaded.");
                });

        $scope.goToCityList = function () {
            $window.location.href = '/AirTicketBooking/#/city';
        };
    }]);

var cityServices = angular.module('cityServices', ['ngResource']);
cityServices.factory('CityService', ['$resource', function ($resource) {
        return function (city) {
            return $resource('rest/city/' + city + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getCityDetail: {method: 'GET', isArray: false},
            });
        };
    }])
        ;