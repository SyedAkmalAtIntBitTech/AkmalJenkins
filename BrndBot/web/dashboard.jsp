<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Dashboard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
       
        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
               font-size:20px;
              position: relative;
                left:-15px;
                padding: 7px;
            }
            #subpromotelist {
                  display: inline-block;
                    position: relative;
                    left: 25px;
                    top: 180px;
            }
          #subpromotelist  li{
                list-style-type: none;
            }
     </style>
            <script>

            angular.module("myapp", [])
                .controller("controllerCategories", function($scope,$http) {
                    $scope.categories = {};
                    $scope.SubCategories = {};
                        $http({
                                        method : 'GET',
                                        url : 'getUserCategories'
                                }).success(function(data, status, headers, config) {
                                        $scope.categories  = data;
                                        
                                }).error(function(data, status, headers, config) {
                                        alert("No data available, problem fetching the data");
                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.
                                });
                                
                            $scope.getSubCategories = function(CatID){
                                    var CategoryID = {"CategoryID": CatID.toString()};
                                    $http({
                                            method: 'POST',
                                            url: getHost() +'getSubCategories',
                                            headers: {'Content-Type': 'application/json'},
                                            data:  CategoryID
                                    }).success(function (data)
                                    {
                                        $scope.SubCategories = data;
                                    })
                                        .error(function(data, status) {
                                        // called asynchronously if an error occurs
                                        // or server returns response with an error status.

                                        alert("request not succesful");
                                        console.log('request not succesful');
                                      });
                            };
                        });

        </script>
        
        <script>

                function getCategoryID(CatID){
                        var CateID = CatID;
                        alert(CateID);
                        getSubCategories('CateID');
                    }
            
        </script>

    </head>

 <body >
    <div > 
               <div class="row">
                    <div id="leftnav" class="col-md-1">
                        <nav class="navbar navbar-default " role="navigation">
                            <div class="navbar-header pull-left">
                                
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>

                            <div class="collapse navbar-collapse">
                                <ul class="nav nav-stacked">
                                    <li><br><br><img src="images/logo.png"  alt="logo" class="img-responsive" width="40"><br></li>
                                    <li><a href="dashboard.jsp"><span class="glyphicon glyphicon-home"></span></a><p id="text1">HOME</p></li>
                                    <li><a href="email.html"><span class="glyphicon glyphicon-envelope"></span></a><p id="text1">EMAIL</p></li>
                                    <li><a href="social.html"><span class="glyphicon glyphicon-comment"></span></a><p id="text1">SOCIAL</p></li>
                                    <li><a href="imagegallery.html"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
                                    <li><a href="setting.html"><span class="glyphicon glyphicon-cog"></span></a><br><br></li> 
                                    <li><br><a href="login.jsp"><p id="text2">LOG OUT</p></a><br></li> 
                                </ul>
                            </div><!-- /.navbar-collapse -->
                        </nav>
        
                    </div><!--/end left column-->
    
                    <div class="col-md-10" ng-app="myapp">
                        
                            <div class="col-md-10 " ng-controller="controllerCategories">

                                <p id="text3">  Hi FitBot Gym!</p>
                                <p id="text3"> What would you like to do today?</p>
                                <ul id="promotelist">
                                    <li id="one" ng-repeat="category in categories">
                                        <img id="promoteimage" src="images/Organizations/Categories/{{category.organizationId}}/{{category.image_name}}" class="{{category.id}}" alt="" ng-click="getSubCategories(category.id)" width="80" height="80" /><p id="text4"  >{{category.categoryName}}</p>
                                    </li>
                                </ul>
                                <div>
                                        <ul id="subpromotelist">
                                            <li id="Sub" ng-repeat="Sub in SubCategories"><br><a href="#"><p id="">{{Sub}}</p></a></li>
                                        </ul>
                                </div>
                            </div>
                            
                        </div>
        
                    </div>      
            
            
            
            
      </div>   
       
   </body>
</html>
