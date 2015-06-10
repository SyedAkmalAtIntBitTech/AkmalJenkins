<%-- 
    Document   : personality
    Created on : Jun 4, 2015, 2:21:41 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>personality</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
       
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"> 
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script src="js/configurations.js"></script>
        <script>
                function showText(brndID,brndName){
                             $("#hiddenform").val(brndID);
                             $("#image1").attr('src', 'images/Brandimages/'+brndName+'.jpeg');
                     }    
                 function sendbrndID(){
                    var brndID = $("#hiddenform").val() ;
                    var path = global_host_address + 'subbrandPersonality?brndID=' + brndID;
                    window.open(path,"_self");
                }     
  
        </script>    

        <script>

            angular.module("myapp", [])
                .controller("MyController", function($scope,$http) {
                                $scope.master = {};
                           $http({
                                    method : 'GET',
                                    url : 'getBrandPersonality'
                            }).success(function(data, status, headers, config) {
                                    $scope.First = data.first;
                            }).error(function(data, status, headers, config) {
                                    alert("No data available, problem fetching the data");
                                    // called asynchronously if an error occurs
                                    // or server returns response with an error status.
                            });
                    });
                
        </script>
         <link rel="stylesheet" href="css/main.css">
        
    </head>
    <body ng-app="myapp">
        <div class="container">
            
            <a href="lookchooser.html">go back</a>
            <a href="uploadlogo.html" class="pull-right">next</a>
             <div id="contemporarycontainer">
                 <div class="span5 col-md-offset-1 ">
                <p id="comment1">Choose a personality</p><br>
                 </div>
                    <div class="row" id="buttonlength">
                        <div class="span7">
                    <div class="col-md-7 pull-right pull-up">

                        <div class="item">
                            <img  class="img-responsive" id="image1" src="http://placehold.it/700x450" width="500" height="350"><br>
                        </div>
                    </div>
                     </div>
                        <form class="form-horizontal"  ng-controller="MyController" ng-model="brands" ng-submit="showText()">
                            <input id="hiddenform" name="hiddenform" type="hidden" ng-model="brands.brandName"><br>
                            <div ng-repeat="first in First" class="span5 col-md-offset-1 ">
                                <button type="button"  id="contemporary1" class="btn btn-default btn-lg" onclick="showText('{{first.id}}','{{first.brand_name}}')">{{first.brand_name}}</button><br><br>
                            </div>  
                            
                            <div class="span4 col-md-offset-1">
                                <button type="button" class="btn btn-info" onclick="sendbrndID()">CONTINUE</button>
                            </div>
                        </form>
                     
                </div>

                    </div>
            </div>  

    </body>

</html>
