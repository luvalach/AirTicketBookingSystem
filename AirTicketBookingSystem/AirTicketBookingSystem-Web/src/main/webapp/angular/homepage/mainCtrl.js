var mainControllers = angular.module('mainControllers', []);

mainControllers.controller('MainCtrl', ['$scope', '$window', '$rootScope', function ($scope, $window, $rootScope) {

        $rootScope.empty_flight = [
            {id: ''},
            {code: ''},
            {airport_from_id: ''},
            {airport_to_id: ''},
            {departure: ''},
            {arrival: ''},
            {plane_id: ''},
        ];
        
        $rootScope.empty_flight_ticket= [
            {id: ''},
            {flight_id: ''},
            {next_flight_ticket: ''},
            {passager_name: ''},
            {id: ''},
            {id: ''},
            {id: ''},
            {id: ''},
            {id: ''},
            {id: ''},
            {id: ''},
        ];
        
        $rootScope.empty_flight_ticket_price = [
            {id: '1'},
            {flight_tickets_id: '1'}
        ];
        
//        $rootScope.flights = [
//            {id: '1'},
//            {code: '1'}
//        ];
        
        $rootScope.flight_tickets = [
            {id: '1'},
            {flight_id: '1'}
        ];
        
        $rootScope.flight_ticket_prices = [
            {id: '1'},
            {flight_tickets_id: '1'}
        ];

        $scope.goToUserList = function () {
            $window.location.href = '/AirTicketBooking/#/user';
        };

    }]);