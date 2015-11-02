<%-- 
    Document   : marketingprogram
    Created on : Oct 9, 2015, 4:12:25 PM
    Author     : Sandeep Kumar at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Marketing category</title>
    <meta charset="UTF-8">
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/dashboard.css" rel="stylesheet" type="text/css"/>
    <script src="js/configurations.js"></script>
    <link href="css/marketingcategories.css" rel="stylesheet" type="text/css"/>
    <script src="js/angular.min.js"></script>
    <script src="js/marketingprogram.js" type="text/javascript"></script>
    <jsp:include page="basejsp.jsp"/>

</head>
<body ng-app>
    <div class="row" ng-controller="controllerMarketingCategories">
        <div class="col-md-1 col-lg-1 col-sm-1 halfcol" ng-init="getMarketingCategories()" >
            <jsp:include page="leftmenu.html"/> 
        </div>
        <div class="col-md-1 col-lg-1 col-sm-1">
            <jsp:include page="marketingcatsubmenu.html"/>
        </div>
        <div class="col-md-10 col-lg-10 col-sm-10 " >
            <div class="row" >
                <div class="col-sm-12 col-lg-12 col-md-12 ">
                    <div class="marktext"> Please choose a marketing program type.</div>
                    <div class="toppad">Lorem ipsum dolor sit ametdaf, 
                        consectetur adipiscing elit, 
                        sed do eiusmod tempor incididunt ut 
                        labore et dolore magna aliqua.
                    </div>
                </div>    
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 marketinglist1">
                <ul>
                    <li id="one" class="col-lg-2 col-md-2 col-sm-2" ng-repeat="category in categories">
                        <a href="marketingprograms.jsp?categoryid={{category.category_id}}">
                        <img id="" class="marketingimage" 
                             src="{{category.image}}" 
                             class="{{category.order}}" alt="" ng-click="showMarketingPrograms(category.category_id)" /></a>
                        <p class="markimgtxt fontpnr">{{category.name}}</p>
                    </li>
                
                </ul>
                </div>
            </div>
        </div>
    </div>
        <script>
          $( document ).ready(function() {
              $("#createprog").css("color","#3f3f42").css("background-color","#f6f7f7");
          });
        </script>
</body>
</html>
