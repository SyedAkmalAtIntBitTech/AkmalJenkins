<%-- 
    Document   : chooseLook
    Created on : Jun 5, 2015, 4:53:50 PM
    Author     : intbit
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>look_chooser</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/configurations.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script>
            var elementid1;
            function showText(lookid){
                    $("#lookid").val(lookid);
                    elementid1 = lookid;
                    $('.step_wrapper').on('click', '.step_box', function () {
                        $(this).parent().find('.step_box').css('background-color', '');
                        $(this).css('background-color', '#DCDCDF');
                    });
            }

            function sendLookID(){
                var LookID = $("#lookid").val();
                 if (LookID == ""){
                    alert('Please select a look');
                }
                else{
                    var configuration = global_host_address + "SetLookID" + "?LookID=" + LookID;
                    window.open(configuration, "_self")
                }
            }
            
            function nextLooks(){
                var lt = 0;
                        lt = lt + 4;
            }
        </script>  
        <script>

            angular.module("myapp", [])
                .controller("MyController", function($scope, $http) {
                $http({
                method : 'GET',
                        url : 'GetLooks'
                }).success(function(data, status, headers, config) {
                        $scope.First = data.first;
                        $scope.Second = data.second;
                        if (data === error ){
                            alert(data);
                        } 
                }).error(function(data, status, headers, config) {
                alert("No data available, problem fetching the data");
                        // called asynchronously if an error occurs
                        // or server returns response with an error status.
                });
            });        
         </script>
    </head>

    <body ng-app="myapp" >
        <div class="container">

            <div id="contentdiv" class="row">
                <div  class="span3 col-md-offset-1 ">
                    <p id="comment1" class="lookcomment1">Please choose a “look”</p></<br>
                </div>
                <div class="row col-md-offset-2" >
                    <div ng-controller="MyController">
                        {{first}}
                        <div class="col-md-12 step_wrapper">
                            <div  class="col-md-2 step_box" ng-repeat="first in First" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                <img id="{{first.id}}" class="img-responsive lookchooser1 " src="images/Lookimages/{{ first.file_name}}"  onclick="showText({{first.id}})" width=250 height=150 />
                            </div>

                            <div class="col-md-12"></div>
                            <div class="col-md-2 step_box" ng-repeat="second in Second" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                <img id="{{second.id}}" class="img-responsive lookchooser1" src="images/Lookimages/{{ second.file_name}}" onclick="showText({{second.id}})" width=250 height=150 />
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="span3 col-md-offset-0 ">
                                <div  class="form-group">
                                    <input type="hidden" name="lookid" id="lookid">
                                    <div class="span3 col-md-offset-0 ">
                                        <button id="loochooserbutton" type="submit"  onclick="sendLookID()" class="btn btn-info">CONTINUE</button><br><br><br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>