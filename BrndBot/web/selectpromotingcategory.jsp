<%-- 
    Document   : selectpromotingcategory
    Created on : Jul 20, 2015, 12:35:19 PM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Select promoting category </title>
        <meta charset="UTF-8">
        <script src="js/angular.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="js/configurations.js"></script>
        <script src="js/mindbodyutility.js" type="text/javascript"></script>
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>

        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
                font-size:20px;
                position: relative;
                left:-15px;
                padding: 7px;
            }
            .btn-info{
                background-color: #E65C00;
                width: 120px;
                border-color: #E65C00;
                border-radius: 10px; 
            }
            .btn-info:hover,.btn-info:focus{
                background-color: #B24700;
                border-color: #E65C00;
            }

            table{
                position: relative;
                top: 150px;
            }
            th{
                font-size: 18px;
                padding: 5px;
                height: 90px;
                background-color: #00CC99;
            }

            td{
                padding: 10px;
            }
            .mindbodydatadiv{
                position: relative;
                padding-bottom: 15px;
                top:  90px;
            }

            .datafromindbody li{
                display: inline-table;
                padding-left: 5px;
                font-size: 18px;



            }
            #paginationbar{
                position: relative;
                margin-top: 100px;
                margin-left: 70px;    
            }
            #continuebutton{
                margin-top: 120px;
            }
            .datafromindbody:hover{
                background-color: #00CC99;
            }
            .datafromindbody:focus{
                background-color: #00CC99;
            }
            .mindbodyOneRowData:focus {
                background-color: #00CC99;
            }

        </style>
        <%!
            SqlMethods sql_methods = new SqlMethods();
            String category_id, sub_category_name, sub_category_id;
        %>

        <%
            String title = "";
            try {
                sql_methods.session = request.getSession(true);
                category_id = request.getParameter("category_id");
                sub_category_name = request.getParameter("sub_category_name");
                sub_category_id = request.getParameter("sub_category_id");
                sql_methods.session.setAttribute("sub_category_name", sub_category_name);
                sql_methods.session.setAttribute("sub_category_id", sub_category_id);
                sql_methods.session.setAttribute("category_id", category_id);

//                if (sub_category_name.equalsIgnoreCase("promote todays class")) {
//                    title = " todays class";
//                } else if (sub_category_name.equalsIgnoreCase("promote work shop")) {
//                    title = "work shop";
//                }

            } catch (Exception e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                sql_methods.closeConnection();
            }
        %>
        <script>

                    angular.module("myapp", [])
                    .controller("controllerGetMindBody", function($scope, $http) {

                    $scope.showData = function(){

                    $scope.curPage = 0;
                            $scope.pageSize = 4;
                            $http({
                            method : 'GET',
                                    url : 'MindBodyDataServlet'
                            }).success(function(data, status, headers, config) {
                    var mindbody_data = JSON.stringify(data);
                                alert(mindbody_data);
                            $scope.datalists = mindbody_data.data;
                            title = mindbody_data.title;
                            $scope.numberOfPages = function() {
                            return Math.ceil($scope.datalists.length / $scope.pageSize);
                            };
                            if (data === error){
                    alert(data);
                    }
                    }).error(function(data, status, headers, config) {
                    alert("No data available, problem fetching the data");
                            // called asynchronously if an error occurs
                            // or server returns response with an error status.
                    });
                    };

                    });
                    angular.module('myapp').filter('pagination', function()
            {
            return function(input, start)
            {
            start = + start;
                    return input.slice(start);
            };
            });
                    var selected_id;
                    function select_category_details(id){
                    selected_id = id;
                            $("#continuebutton").prop("disabled", false);
                            //                    var configuration = global_host_address + "socialeditor.jsp" + "?id=" +id;
                            //                    window.open(configuration, "_self")
                    }
            //                $(".mindbodyOneRowData").click(function (){
            //                    alert("clicked");
            //                  $(".datafromindbody li").css("background-color","inherit");
            //             $(this).css("background-color","red");
            //                });

            function selected_category(){

            //                    alert(selected_id);
            var configuration = global_host_address + "selectpromotemedia.jsp" + "?id=" + selected_id;
                    window.open(configuration, "_self");
            }
        </script>
        

    </head>
    <body ng-app = "myapp">

        <div class="row">
            <div id="leftnav" class="col-md-1">
                <nav class="navbar navbar-default " role="navigation">
                    <div class="navbar-header">

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
                            <li><a href="emaillists.jsp"><span class="glyphicon glyphicon-envelope"></span></a><p id="text1">EMAIL</p></li>
                            <li><a href="social.html"><span class="glyphicon glyphicon-comment"></span></a><p id="text1">SOCIAL</p></li>
                            <li><a href="imagegallery.jsp"><span class="glyphicon glyphicon-picture"></span></a><p id="text1">IMAGE GALLERY</p></li>   
                            <li><a href="setting.html"><span class="glyphicon glyphicon-cog"></span></a><br></li> 
                            <li><br><a href="signout.jsp"><p id="text2">LOG OUT</p></a><br><br></li> 
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </nav>

            </div><!--/end left column-->

            <div class="col-md-11 ">

                <div class="col-md-6">
                    <p id="text3"><%= title%>  </p> </div> 
                <div class="col-md-5">  <input type="button"  id="continuebutton" class="btn btn-info col-md-offset-2" onclick="selected_category()" value="Continue" disabled="true">

                </div>
            </div>  


            <div class="col-md-11">      
                <div ng-controller="controllerGetMindBody" class="tab-pane active" id="picktheme" ng-init="showData()">

                    <div class="col-md-8 col-md-offset-1 mindbodydatadiv" ng-repeat= "jsonclass in datalists| pagination: curPage * pageSize | limitTo: pageSize" id="rep" >
                        <!--                                {{jsonclass}}-->
                        <div class='mindbodyOneRowData' onclick="select_category_details('{{jsonclass.id}}')">
                            <span style="width: 700px;">
                                <ul class="datafromindbody">

                                    <li style="width: 200px">{{jsonclass.column1}}</li>
                                    <li style="width: 200px">{{jsonclass.column2}}</li>
                                    <li style="width: 200px">{{jsonclass.column3}}</li>
                                </ul>
                            </span>
                        </div>
                        <!--                          <div id="" class="foo col-md-2"><p>{{classes.name}}</p></div>
                                                        <div id="" class="foo col-md-2"><p>{{classes.StartDateTime}}</p></div>
                                                        <div id="" class="foo col-md-2"><p>{{classes.EndDateTime}}</p></div>-->

                        <div id=' id ' name=id>
                            <p><br/></p>
                        </div>
                    </div>
                    <div class="col-md-2"></div><br><br><br><br><br><br>
                    <div id="paginationbar" class="pagination pagination-centered col-md-11 col-md-offset-2 " ng-show="datalists.length">
                        <ul class="pagination-controle pagination">
                            <li>
                                <button type="button" class="btn btn-primary" ng-disabled="curPage == 0"
                                        ng-click="curPage = curPage - 1"> &lt; PREV</button>
                            </li>
                            <li>
                                <span>Page {{curPage + 1}} of {{ numberOfPages()}}</span>
                            </li>
                            <li>
                                <button type="button" class="btn btn-primary"
                                        ng-disabled="curPage >= classes.length / pageSize - 1"
                                        ng-click="curPage = curPage + 1">NEXT &gt;</button>
                            </li>
                        </ul>
                    </div>

                </div>

            </div>      
        </div> 

    </body>
</html>
