<%-- 
    Document   : chooseLook
    Created on : Jun 5, 2015, 4:53:50 PM
    Author     : intbit
--%>

<%@page import="org.apache.http.HttpResponse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>look_chooser</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/configurations.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="css/main1.css">
        <script>
            var elementid1;
                function showText(LookID){
                             $("#LookID").val(LookID);
                             elementid1=LookID;
                              
                     }    
                function sendLookID(){
                    var LookID = $("#LookID").val() ;
                    var configuration = global_host_address + "setLookID" + "?LookID=" + LookID; 
                    window.open(configuration,"_self")
                   
                }
                function nextLooks(){
                    var lt = 0;
                    lt = lt + 4;
                }
                
           
                
                
        </script>    
        <script>

            angular.module("myapp", [])
                .controller("MyController", function($scope,$http) {
                            $http({
                                    method : 'GET',
                                    url : 'getLooks'
                            }).success(function(data, status, headers, config) {
                                    $scope.First = data.first;
                                    $scope.Second = data.second;
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
           
            <div class="row pull">
                <div class="span1">
                    
                    <a href="studio.html">go back</a>
                    <a href="personality.html" class="pull-right">next</a>
                  
                </div>
            </div>
            <div id="contentdiv" class="row">
                  <div  class="span3 col-md-offset-1 ">
                      <p id="comment1" class="lookcomment1">Please choose a “look”</p></<br>
                 </div>
<!--                <div><a href="#"><img src="images/arrow_left.jpg" width="40" height="60" style="position:relative; float:left; margin-left: 80px; margin-top: 100px; left:60px"></a></div>
                <div><a href="#"><img src="images/arrow_right.jpg" onclick="" width="40" height="60" style="position:relative;  float:right;  margin-right: 80px; margin-top: 100px;"></a></div>
-->                
                <div class="row col-md-offset-2" >
                    <div ng-controller="MyController">
                        <div class="col-md-12">
                            <div class="col-md-2" ng-repeat="first in First" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                <img id="{{first.id}}" class="img-responsive lookchooser1" src="images/Lookimages/{{ first.look_name }}.png"  onclick="showText({{first.id}})" width=250 height=150 />
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-2" ng-repeat="second in Second" style="border:1px solid #dadada; border-radius: 5px; margin-left: 20px; margin-bottom: 10px;">
                                <img id="{{second.id}}" class="img-responsive lookchooser1" src="images/Lookimages/{{ second.look_name }}.png" onclick="showText({{second.id}})" width=250 height=150 />
                            </div>
                        </div> 
                       <div class="form-group">
                            <div class="span3 col-md-offset-0 ">
                                <div  class="form-group">
                                    <input type="hidden" name="LookID" id="LookID">
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
        <script>
            
            
        </script>
        
    </body>
</html>