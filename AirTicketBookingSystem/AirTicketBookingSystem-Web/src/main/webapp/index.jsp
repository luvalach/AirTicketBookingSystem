<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html ng-app="airTicketBookingApp">
    <head>
        <title>Air tickets booking system</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Air tickets booking system">
        <!-- JS -->
        <script src="https://code.angularjs.org/1.3.3/angular.min.js"></script>
        <script src="js/ui-bootstrap-tpls-0.12.0.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-route.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-resource.js"></script>
        <script src="angular/BookingSystemApp.js"></script>
        <script src="angular/homepage/mainCtrl.js"></script>
        <script src="angular/lib/angular-xeditable-0.1.8/js/xeditable.js"></script>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>
        <!--User JS -->
        <script src="angular/user/userCtrl.js"></script>
        <!--Set angular startup arguments -->
        <script>
            app.value("userId", "${userId}").value("isAdmin", "<sec:authorize access="hasRole('ROLE_ADMIN')">true</sec:authorize>").value("userName", "${userName}");
        </script>

        <!-- Fonts -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
        <!-- CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        <link href="css/business-casual.css" rel="stylesheet">
    </head>
    <body>
        <div class="brand">Air Tickets Booking System</div>
        <div class="address-bar">School project</div>
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/AirTicketBooking/#/homepage">Home</a>
                        </li>
                        <li>
                            <a href="/AirTicketBooking/#/user">Users</a>
                        </li>
                        <li>
                            <a href="j_spring_security_logout">Logout (${userName})</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <div ng-view></div>
    </body>
</html>