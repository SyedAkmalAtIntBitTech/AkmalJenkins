<%@page import="com.controller.SqlMethods"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    SqlMethods sqlmethods = new SqlMethods();
    String checked = "false";
    Integer user_id = 0;
    String company = "";
%>

<%
    try {
        sqlmethods.session = request.getSession(true);
        checked = (String) sqlmethods.session.getAttribute("Checked");
        if (checked == null || checked == "false") {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            user_id = (Integer) sqlmethods.session.getAttribute("UID");
            company = (String) sqlmethods.session.getAttribute("company");
        }
    } catch (Exception e) {
        out.println(sqlmethods.error);
    }finally {
        sqlmethods.closeConnection();
    }
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
        <link rel="SHORTCUT ICON" href="images/Layout-styles/logo_small.png"/>
        <script src="js/dashboard.js"></script>

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
                font-weight: bold;
                position:relative;
                left: 35px;
                font-family:ProximaNova,Semibold;
                font-size: 1.13em;
                color: #2d4355;
                font-style: normal;
                text-align: center;
                line-height: 1.21em;
                letter-spacing: 0em;

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
    </head>

    <body>
        <div>
            <div class="row">
                <jsp:include page="mainmenu.html"/>
                <div class="col-md-10" ng-app="myapp">

                    <div class="col-md-10 " ng-controller="controllerCategories">

                        <p id="text3" class="company">  Hi <%= company%>!</p>
                        <p id="text3" class="message"> What would you like to do today?</p>
                        <p id="text3" style="display: none" class="mindbodyactivationstatus">{{mindbodyactivationmessage}}<br><a href="{{mindbodyactivationlink}}">Click here</a></p>
                        <ul id="promotelist">
                            
                            <li id="one" ng-repeat="category in categories">
                                <a href=""><img id="promoteimage" src="images/Organizations/Categories/{{category.organizationId}}/{{category.image_name}}" class="{{category.id}}" alt="" ng-click="getSubCategories(category.id)" width="90" height="91" /></a><p id="text4">{{category.categoryName}}</p>
                            </li>
                        </ul>
                        <div>
                            <ul id="subpromotelist">
                                <li ng-repeat="Sub in SubCategories" id="{{Sub.category_id}}"><br><p id="{{Sub.category_id}}" onclick="setSubCategoryID('{{Sub.category_id}}', '{{Sub.id}}', '{{Sub.sub_category_name}}')">{{Sub.sub_category_name}}</p></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>      
        </div>   

    </body>
</html>
