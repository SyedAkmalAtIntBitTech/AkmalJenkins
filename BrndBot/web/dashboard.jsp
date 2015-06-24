<%@page import="com.controller.sqlMethods"%>
        <%!
            HttpServletRequest request;
            HttpServletResponse response;
            sqlMethods SM = new sqlMethods();
         %>
         <%
                SM.session = request.getSession(true);
                Integer user_id = (Integer)SM.session.getAttribute("UID");
                String org_name = (String)SM.session.getAttribute("org_name");
                String company = (String)SM.session.getAttribute("company");
         %>

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
/*                    left: 25px;
                    top: 180px;*/
            }
         #subpromotelist  li{
                list-style-type: none;
            }
         #sidebar-wrapper {
                margin-left:-80px;
                left: 60px;
                width: 100px;
                background: whitesmoke;
                position: fixed;
                height: 100%;
                z-index: 10000;
                transition: all .4s ease 0s;
             }

             
         .navbar-default {
                background-color: whitesmoke;
                border-color: whitesmoke;
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
                                      
                                      if(CatID===1){
                                          $("#subpromotelist").css("left","30px").css("top","180px");
                                      }
                                      else if(CatID===2){$("#subpromotelist").css("left","150px").css("top","180px");}
                                      else if(CatID===3){$("#subpromotelist").css("left","270px").css("top","180px");}
                                      else if(CatID===4){$("#subpromotelist").css("left","385px").css("top","180px");}
                                      else if(CatID===5){$("#subpromotelist").css("left","510px").css("top","180px");}
                                      else if(CatID===6){$("#subpromotelist").css("left","625px").css("top","180px");}
                                      else if(CatID===7){ $("#subpromotelist").css("left","30px").css("top","180px");}
                                         
                                      
                                      else if(CatID===8){$("#subpromotelist").css("left","150px").css("top","180px");}
                                      else if(CatID===9){$("#subpromotelist").css("left","270px").css("top","180px");}
                                      else if(CatID===10){$("#subpromotelist").css("left","385px").css("top","180px");}
                                      else if(CatID===11){$("#subpromotelist").css("left","510px").css("top","180px");}
                                      else if(CatID===12){$("#subpromotelist").css("left","625px").css("top","180px");}
                            };          
                        });

               



        </script>
        
        <script>

    function getSubCategories(IDNo){
        alert(IDNo);
        
    }
    
    
//                       
//                       $("#one").click(function(){
//                           alert("hi");
//                       if(c==='1'){
//                           alert("hi");
//                            $("#subpromotelist").css("top","180px");
//                       }
//                       
//                      } ) 
//                 




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
                                    <li><br><a href="signout.jsp"><p id="text2">LOG OUT</p></a><br><br><br></li> 
                                </ul>
                            </div><!-- /.navbar-collapse -->
                        </nav>
        
                    </div><!--/end left column-->
    
                    <div class="col-md-10" ng-app="myapp">
                        
                            <div class="col-md-10 " ng-controller="controllerCategories">

                                <p id="text3">  Hi <%= company %>!</p>
                                <p id="text3"> What would you like to do today?</p>
                                <ul id="promotelist">
                                    <li id="one" ng-repeat="category in categories">
                                        <a href=""><img id="promoteimage" src="images/Organizations/Categories/{{category.organizationId}}/{{category.image_name}}" class="{{category.id}}" alt="" ng-click="getSubCategories(category.id)" width="80" height="80" /></a><p id="text4"  >{{category.categoryName}}</p>
                                    </li>
                                </ul>
                                <div>
                                        <ul id="subpromotelist">
                                            <li ng-repeat="Sub in SubCategories" id="{{Sub.category_id}}"><br><a href="#"><p id="">{{Sub.sub_category_name}}</p></a></li>
                                        </ul>
                                </div>
                            </div>
                        </div>
                    </div>      
      </div>   
       
   </body>
</html>
