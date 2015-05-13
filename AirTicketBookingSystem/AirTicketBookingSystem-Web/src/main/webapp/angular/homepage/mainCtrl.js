var mainControllers = angular.module('mainControllers', []);

mainControllers.controller('MainCtrl', ['$scope', '$window', '$rootScope', '$log', '$filter', 'FlightService', 'AirportService', function ($scope, $window, $rootScope, $log, $filter, FlightService, AirportService) {
 
    $scope.airports = AirportService("").query();
        
    // zobrazenie výsledkov
    $scope.search = function (from, to, departure) {
        from = $scope.search.airportByAirportFromId.id;
        to = $scope.search.airportByAirportToId.id;
        departure = $scope.search.departure;
        departure = $filter('date')(departure, "yyyy-MM-dd");
        $window.location.href = '/AirTicketBooking/#/searchResults/' + from + '/' + to + '/' + departure;
//        $window.location.href = '/AirTicketBooking/#/searchResults/';
    };

}]);