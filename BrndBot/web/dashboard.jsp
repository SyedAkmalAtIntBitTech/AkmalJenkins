<%@page import="com.controller.SqlMethods"%>

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
         <%@ include file="fonttypekit.jsp"%>
         <%@ include file="checksession.jsp" %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
       
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        
        <script src="js/dashboard.js"></script>

        <style>
            .glyphicon.glyphicon-home,.glyphicon.glyphicon-envelope,.glyphicon.glyphicon-comment, .glyphicon.glyphicon-picture, .glyphicon.glyphicon-cog{
                font-size:20px;
                position: relative;
                left:-15px;
                padding: 7px;
            }
           
            #subpromotelist li
            {
                position:relative;
                top:-15px;
                width: 200px;
                list-style-type: none;
                font-weight: bold;
                font-family: "proxima-nova",sans-serif;
                font-weight: 600;
                font-size: 1.13em;
                color: #2d4355;
                text-align: center;
                font-style: normal;
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
        <jsp:include page="basejsp.jsp" />

    </head>

    <body>
        <div>
            <div class="row">
                <jsp:include page="mainmenu.html"/>
                <div class="col-md-11 col-md-offset-1 " ng-app="myapp">

                    <div class="col-md-10 col-md-offset-1" ng-controller="controllerCategories">

                        <p id="text3" class="company MH2">  Hi <%= company%>!<br>What would you like to do today?</p>
                        <p id="text3-1" class="mindbodyactivationstatus">{{mindbodyactivationmessage}}<br><a href="{{mindbodyactivationlink}}" target="_blank">Click here</a></p>
                       <ul id="promotelist">
                            
                            <li id="one" ng-repeat="category in categories">
                                <a href=""><img id="promoteimage" src="/BrndBot/DownloadImage?image_type=ORG_CATEGORIES&image_name={{category.image_name}}&org_id={{category.organizationId}}" class="{{category.id}}" alt="" ng-click="getSubCategories(category.id)" width="90" height="91"/></a>
                                <p id="text4" class="il2">{{category.categoryName}}</p>
                            </li>
                        </ul>
                        <div>
                            <ul id="subpromotelist">
                                <li ng-repeat="Sub in SubCategories" id="{{Sub.category_id}}"><br><p  id="{{Sub.category_id}}" onclick="setSubCategoryID('{{Sub.category_id}}', '{{Sub.id}}', '{{Sub.sub_category_name}}', '{{Sub.external_source}}')">{{Sub.sub_category_name}}</p></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>      
        </div>   

    </body>
</html>
