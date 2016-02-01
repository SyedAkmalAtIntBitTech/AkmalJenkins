<%@page import="com.controller.SqlMethods"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%-- 
    Document   : dashboardv2
    Created on : Dec 10, 2015, 11:51:40 AM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Dashboard</title>
    <meta charset="UTF-8"/>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/alert_message.js"></script>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/dashboard.js"></script>
    <jsp:include page="basejsp.jsp" />
</head>    

<body class="" >
    <!--SideNav-->  
    <div class="col-md-11 col-md-offset-1 " ng-app="myapp">
    <div class="col-md-10 col-md-offset-1" ng-controller="controllerCategories">
    <%@include file="navbarv2.jsp" %>                    
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div class="page-title-regular page-title-font">Welcome <%= company%></div>
            <div class="page-cta-container"></div>
        </div>
    </div>
        <!--Main Content GENERIC--> 
        <div class="dashboard-background" >
            <div class="dashboard-content">
                <div class="h1"> What would you like to do today?</div>
                <div class="h1 mindbodyactivationstatus">{{mindbodyactivationmessage}}<br><a href="{{mindbodyactivationlink}}" target="_blank">Click here</a></div>
                <div class="button-row col-1of1" ng-repeat="category in categories">
                    <div class="button-column fleft col-1of10 pushright hint--bottom" data-hint="{{category.categoryName}}">
                        <a href="subcategory.jsp?id={{category.id}}"><img type="image/svg+xml" src="/BrndBot/DownloadImage?image_type=ORG_CATEGORIES&image_name={{category.image_name}}&org_id={{category.organizationId}}" class="{{category.id}}" alt=""  class="big-selection-button" style="cursor:pointer;height:100%;width:100%;"/> </a>
                         <!--<p id="text4" class="il2">{{category.categoryName}}</p>-->
                    </div>
                </div>
                 <div class=" subcatlist input-field-container col-1of1"> 
                     <ul id="subpromotelist" class="input-header">
                         <li ng-repeat="Sub in SubCategories" id="{{Sub.category_id}}"><p  id="{{Sub.category_id}}" onclick="setSubCategoryID('{{Sub.category_id}}', '{{Sub.id}}', '{{Sub.sub_category_name}}', '{{Sub.external_source}}')">{{Sub.sub_category_name}}</p></li>
                     </ul>
                 </div>
            </div>
        </div>
        </div>
    </div>       
    </body>
</html>