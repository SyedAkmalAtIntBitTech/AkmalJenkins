<%-- 
    Document   : history
    Created on : Aug 25, 2015, 6:46:46 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script src="js/configurations.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/socialeditor.css" rel="stylesheet" type="text/css"/>
        <link href="css/colpick.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.autogrow-textarea.js" type="text/javascript"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">           
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link href="css/crop.css" rel="stylesheet" type="text/css"/>
        <link href="css/example.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
        <script src="js/crop.js" type="text/javascript"></script>
        <script>

            function emailHistory($scope, $http) {
                
                $scope.displayemailhistory = function (){
                $http({
                        method : 'GET',
                        url : 'GetEmailTagsServlet'
                }).success(function(data, status, headers, config) {
    //                        alert(JSON.stringify(data.user_colors));
                    $scope.email_history = data;
                    if (data === error){
                        alert(data);
                    }
                }).error(function(data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });        
                }
            }
        </script>

    </head>
    <body ng-app>
        <div ng-controller="emailHistory" class="container" id="container"> 
            <div class="row">
                <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <jsp:include page="emailsubmenu.html"/>
            <div class="col-md-8 col-md-offset-2 " >
                
                <div class="col-md-6 col-md-offset-0">
                    <p id="hyshead">Email History and Analytics</p>
                    <div>
                        <ul>
                            <li>Number of Sent</li>
                            <li>Open Rate</li>
                            <li>Click Through Rate</li>
                            <li>Unsubscribed</li>
                        </ul>
                    </div>
                    
                    <p>_____________________________________________________________</p>
                    <div ng-init="displayemailhistory()">
                        <ul ng-repeat="email in email_history">                            
                            <li>{{email.tag}}</li>
                            <li>{{email.sent}}</li>
                            <li>{{email.opens}}</li>
                            <li>{{email.clicks}}</li>
                            <li>{{email.unsubs}}</li>
                        </ul>
                        
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>