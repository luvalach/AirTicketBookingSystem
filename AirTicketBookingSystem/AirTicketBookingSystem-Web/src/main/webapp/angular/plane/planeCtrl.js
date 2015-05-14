var planeControllers = angular.module('planeControllers', []);

planeControllers.controller('PlaneListCtrl', ['$scope', '$window', 'PlaneService', '$log', function ($scope, $window, PlaneService, $log) {

        //Table will be ordered by plane name by default
        $scope.orderByField = 'plane';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.planes = {};

        //refresh planes list
        $scope.refreshPlanes = function () {
            PlaneService("").query(
                    function (data, status, headers, config) {
                        $scope.planes = data;
                        $log.info("List of planes loaded.");
                    }, function (data, status, headers, config) {
                $log.info("An error occurred on server! List of planes cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshPlanes();
        
        $scope.showPlaneDetail = function (planeId) {
            $window.location.href = '/AirTicketBooking/#/plane/detail/' + planeId;
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


planeControllers.controller('PlaneDetailCtrl', ['$scope', '$routeParams', '$window', '$log',
    'PlaneService',
    function ($scope, $routeParams, $window, $log, PlaneService) {
        $scope.errorMessages = {};
        
        $scope.plane = PlaneService($routeParams.planeId).getPlaneDetail(
                function (data, status, headers, config) {
                    $log.info("Plane detail loaded.");
                    $scope.plane = data;
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of plane cannot be loaded.");
                });

        $scope.goToPlaneList = function () {
            $window.location.href = '/AirTicketBooking/#/plane';
        };
    }]);

var planeServices = angular.module('planeServices', ['ngResource']);
planeServices.factory('PlaneService', ['$resource', function ($resource) {
        return function (plane) {
            return $resource('rest/plane/' + plane + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getPlaneDetail: {method: 'GET', isArray: false}
            });
        };
    }])
        ;