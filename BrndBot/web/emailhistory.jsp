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
         <link href="css/simplecontinuebutton.css" rel="stylesheet" type="text/css"/>
         <link href="css/textstyle.css" rel="stylesheet" type="text/css"/>
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
                    if (data == ""){
                        $scope.email_history = "No email history present";
                        $("#scrl").hide();
                        $("#email_headings").hide();
                        $("#nohistory").show();
                    }else {
                        $("#scrl").show();
                        $("#nohistory").hide();
                        $scope.email_history = data;
                    }
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
        <style>.emlhisdata li{
                display: table-cell;
                position: relative;
                width: 150px;
                text-align: center;
            }
            .emlist {
                overflow-y: scroll;
                position: relative;
                width: 900px;
                left:300px;
                font-family: "proxima-nova",sans-serif;
                font-weight: 300;
                color: #2d4355;
                font-style: normal;
                text-align: left;
                line-height: 25.9px;
                letter-spacing: 0em;
            }
            .emlOneRowData li{
                    font-family: "proxima-nova",sans-serif;
                    font-weight: 400;
                    color: #2d4355;
                    vertical-align:middle; 
                    left:30px;
                    height: 10%;
                    display: table-cell;
                    position: relative;
                    padding-top: 3%;
                    padding-bottom: 3%;
            }
            .emlOneRowData{
                   
                position: relative;
                width: 900px;
                text-align: center;
            }
            ul.L2 li{ 

                font-family: "proxima-nova",sans-serif;
                font-weight: 600;
                color: #2d4355;
                font-style: normal;
            }
            #scrl{overflow-y: scroll;overflow-x: hidden;width:950px;height:450px;}
        </style>
    </head>
    <body ng-app style="overflow: hidden;">
        <div ng-controller="emailHistory" class="container" id="container"> 
            <div class="row">
                <jsp:include page="leftmenu.html"/><!--/end left column-->
            </div>

            <jsp:include page="emailsubmenu.html"/>
            <div class="col-md-8 col-md-offset-2 " >
                
                <div class="col-md-5 col-md-offset-0">
                    <p id="hyshead" class="MH2">Email History and Analytics</p>
                    <div id="email_headings" class="col-md-4 col-md-offset-0" >
                        <ul class="emlhisdata emlist FL2">
                            <li>Number of Sent</li>
                            <li>Open Rate</li>
                            <li>Click Through Rate</li>
                            <li>Unsubscribed</li>
                        </ul>
                    </div>
                    <hr id="line" style="width:950px;height:1px;background-color:#000;position:relative;left:5px;">
                    <div id="nohistory">
                        <p>{{email_history}}</p>
                    </div>
                    <div id="scrl" class="col-md-6" ng-init="displayemailhistory()">
                        <ul class="emlOneRowData L2 LE2" ng-repeat="email in email_history">                            
                            <li style="width:450px;text-align:left;left:-35px;">{{email.tag}}</li>
                            <li style="width: 250px">{{email.sent}}</li>
                            <li style="width: 250px">{{(email.opens/email.sent)*100}}%</li>
                            <li style="width: 250px">{{(email.clicks/email.sent)*100}}%</li>
                            <li style="width: 250px">{{email.unsubs}}</li>
                        </ul>
                       
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>