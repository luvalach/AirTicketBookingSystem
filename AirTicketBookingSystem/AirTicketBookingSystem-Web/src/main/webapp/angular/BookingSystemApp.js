//Vytvoří naši angulární aplikaci, ta má dependence na kontrolery pro jednotlivé UC
var app = angular.module('airTicketBookingApp', [
    'ngRoute',
    'xeditable',
    'mainControllers',
    'userControllers',
    'userServices',
    'cityControllers',
    'cityServices',
    'airportControllers',
    'airportServices',
    'flightControllers',
    'flightServices',
    'flightPriceControllers',
    'flightPriceServices',
    'ui.bootstrap',
    'ui.bootstrap.datetimepicker' 
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                //říká, že když do URL zadám http://localhost:8080/AirTicketBooking/#/homepage
                //tak mi angular do stránky vloží obsah z angular/homepage/homepage.html
                //Vnitřek homepage.html bude obládán kontrolerem MainCtrl
                when('/homepage', {
                    templateUrl: 'angular/homepage/homepage.html',
                    controller: 'MainCtrl'
                }).
                when('/user', {
                    templateUrl: 'angular/user/userList.html',
                    controller: 'UserListCtrl'
                }).
                when('/user/detail/:userId', {
                    templateUrl: 'angular/user/userDetail.html',
                    controller: 'UserDetailCtrl'
                }).
                when('/user/create', {
                    templateUrl: 'angular/user/userCreate.html',
                    controller: 'UserCreateCtrl'
                }). 
                when('/city', {
                    templateUrl: 'angular/city/cityList.html',
                    controller: 'CityListCtrl'
                }).
                when('/city/detail/:cityId', {
                    templateUrl: 'angular/city/cityDetail.html',
                    controller: 'CityDetailCtrl'
                }).
                when('/airport', {
                    templateUrl: 'angular/airport/airportList.html',
                    controller: 'AirportListCtrl'
                }).
                when('/airport/detail/:airportId', {
                    templateUrl: 'angular/airport/airportDetail.html',
                    controller: 'AirportDetailCtrl'
                }).
                when('/flight', {
                    templateUrl: 'angular/flight/flightList.html',
                    controller: 'FlightListCtrl'
                }).
                when('/flight/detail/:flightId', {
                    templateUrl: 'angular/flight/flightDetail.html',
                    controller: 'FlightDetailCtrl'
                }).
                when('/flight/create', {
                    templateUrl: 'angular/flight/flightCreate.html',
                    controller: 'FlightCreateCtrl'
                }). 
                when('/flightPrice', {
                    templateUrl: 'angular/flightPrice/flightPriceList.html',
                    controller: 'FlightPriceListCtrl'
                }).
                when('/flightPrice/detail/:flightPriceId', {
                    templateUrl: 'angular/flightPrice/flightPriceDetail.html',
                    controller: 'FlightPriceDetailCtrl'
                }).
                when('/flightPrice/create', {
                    templateUrl: 'angular/flightPrice/flightPriceCreate.html',
                    controller: 'FlightPriceCreateCtrl'
                }). 
                otherwise({
                    redirectTo: '/homepage'
                });
    }]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme.
});