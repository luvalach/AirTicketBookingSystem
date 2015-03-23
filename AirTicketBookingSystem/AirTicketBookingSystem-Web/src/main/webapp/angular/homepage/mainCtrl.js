var mainControllers = angular.module('mainControllers', []);

mainControllers.controller('MainCtrl', [ '$scope', '$window', function ($scope, $window) {

    $scope.goToUserList = function () {
        $window.location.href = '/AirTicketBooking/#/user';
    };

}]);