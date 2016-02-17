<%-- 
    Document   : createmarketingprogram
    Created on : Jan 5, 2016, 11:16:45 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Marketing category</title> 
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <script src="js/configurations.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="js/angular.min.js"></script>
    <script src="js/marketingprogram.js" type="text/javascript"></script>
    <jsp:include page="basejsp.jsp"/>

</head>    

<body ng-app>
    <!--SideNav-->
    <div class="content-main" ng-controller="controllerMarketingCategories">
    <%@include file="navbarv2.jsp" %>
    
    <!--Top Nav--> 
        <!--Main Content GENERIC--> 
        <div class="dashboard-background" ng-init="getMarketingCategories()" >
            <div class="dashboard-content">
                <div class="h1">Please choose a marketing program type.</div>
                <div class="button-row col-1of1" >
                    <div class="button-column fleft col-1of10 pushright hint--bottom" ng-repeat="category in categories" data-hint="{{category.name}}">
                        <a href="marketingprogramsnew.jsp?categoryid={{category.category_id}}" class="fleft" >
                            <img type="image/svg+xml" src="{{category.image}}" class="{{category.order}} big-selection-button" style="cursor:pointer;height:100px; width:100%;" ng-click="showMarketingPrograms(category.category_id)"></img>
                        </a>
                    </div>                 
                </div>
            </div>
        </div>
    </div>
  
        <!--CTA Bar
        <div class="bottom-cta-bar">
            <div class="bottom-cta-button-container">
             REMOVE BUTTON HERE
               <div class="remove-button-detail md-button button-text-1">Delete Selected Actions</div>

            </div>-->
<!--        </div>
        </div>
</div>-->
    </body>
</html>