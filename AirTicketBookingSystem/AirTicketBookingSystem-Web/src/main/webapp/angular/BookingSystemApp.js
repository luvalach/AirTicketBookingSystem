//Vytvoří naši angulární aplikaci, ta má dependence na kontrolery pro jednotlivé UC
var app = angular.module('airTicketBookingApp', [
    'ngRoute',
    'xeditable',
    'mainControllers',
    'userControllers',
    'userServices',
    'cityControllers',
    'cityServices',
    'ui.bootstrap'
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
                otherwise({
                    redirectTo: '/homepage'
                });
    }]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme.
});