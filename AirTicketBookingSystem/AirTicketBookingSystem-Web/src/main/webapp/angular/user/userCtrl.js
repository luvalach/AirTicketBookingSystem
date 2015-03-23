var userControllers = angular.module('userControllers', []);

userControllers.controller('UserListCtrl', ['$scope', '$window', 'UserService', 'isAdmin', '$log', function ($scope, $window, UserService, isAdmin, $log) {

        $scope.isAdmin = isAdmin;
        //Table will be ordered by location nick by default
        $scope.orderByField = 'nick';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.users = {};

        //refresh user list
        $scope.refreshUsers = function () {
            UserService("").query(
                    function (data, status, headers, config) {
                        $scope.users = data;
                        $log.info("List of user loaded.");
                    }, function (data, status, headers, config) {
                $log.info("An error occurred on server! List of user cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshUsers();
        
        $scope.showUserDetail = function (userId) {
            $window.location.href = '/AirTicketBooking/#/user/detail/' + userId;
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
        
        $scope.goToCreateUser = function () {
            $window.location.href = '/AirTicketBooking/#/user/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/AirTicketBooking/';
        };
        
        $scope.isAdminFunc = function () {
            if ($scope.isAdmin === "true") {
                return true;
            } else {
                return false;
            }
        }
    }]);


userControllers.controller('UserDetailCtrl', ['$scope', '$routeParams', '$window', '$log',
    'UserService', 'userId', 'isAdmin',
    function ($scope, $routeParams, $window, $log, UserService, userId, isAdmin) {
        $scope.errorMessages = {};
        
        $scope.userId = userId;
        $scope.isAdmin= isAdmin;
        $log.error("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);
        
        $scope.user = UserService($routeParams.userId).getUserDetail(
                function (data, status, headers, config) {
                    $log.info("User detail loaded.");
                    $scope.user = data;
                    $scope.userBackup = angular.copy($scope.user);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of user cannot be loaded.");
                });

        $scope.goToUserList = function () {
            $window.location.href = '/AirTicketBooking/#/user';
        };

        $scope.updateUser = function (user) {
            $log.info("Saving user with ID: " + user.id);
            UserService("").update(user,
                    function (data, status, headers, config) {
                        $log.info("User updated");
                        $scope.errorMessages = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! User cannot be updated.");
                        $scope.errorMessages = data.data;
                    });
        };

        $scope.deleteUser = function (user) {
            $log.info("Deleting user with ID: " + user.id);
            UserService(user.id).delete(
                    function (data, status, headers, config) {
                        $log.info("User deleted");
                        $scope.goToUserList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! User cannot be deleted.");
                    });
        };
        
        $scope.hasPermissionToModifyEntity = function (user) {
            if ($scope.isAdmin != "true" && user.id != $scope.userId) {
                return false;
            } else {
                return true;
            }
        };
    }]);

userControllers.controller('UserCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'UserService', function ($scope, $routeParams, $window, $log, UserService) {
        $scope.errorMessages = {
            "fieldErrors": []
        };
        
        $scope.user = {
            "id": null,
            "firstName": "",
            "Surname": "",
            "description": "",
            "nick": "",
            "role": "ROLE_USER"
        };

        $scope.goToUserList = function () {
            $window.location.href = '/AirTicketBooking/#/user';
        };

        $scope.createUser = function () {
            $log.info("Creating user with name: " + $scope.user.name);
            UserService("").create($scope.user,
                    function (data, status, headers, config) {
                        $log.info("User created");
                        $scope.errorMessages = {};
                        $scope.showUserDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! User cannot be created.");
                        $scope.errorMessages = data.data;
                    });
        };

        $scope.showUserDetail = function (userId) {
            $window.location.href = '/AirTicketBooking/#/user/detail/' + userId;
        };
    }]);

var userServices = angular.module('userServices', ['ngResource']);
userServices.factory('UserService', ['$resource', function ($resource) {
        return function (user) {
            return $resource('rest/user/' + user + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getUserDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }])
        ;