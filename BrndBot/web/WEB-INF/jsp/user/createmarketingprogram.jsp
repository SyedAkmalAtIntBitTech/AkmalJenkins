<%-- 
    Document   : createmarketingprogram
    Created on : Jan 5, 2016, 11:16:45 AM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Create Marketing Program</title> 
    <%--<%@ include file="fonttypekit.jsp"%>--%>
    <%--<%@ include file="checksession.jsp" %>--%>
    <script src="js/configurations.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <script src="js/angular.min.js"></script>
    <script src="js/marketingprogram.js" type="text/javascript"></script>
    <%--<jsp:include page="basejsp.jsp"/>--%>

</head>    

<body ng-app class="body-normal">
        <%@include file="header.jsp" %>  
    <%@include file="navbar.jsp" %>
    <!--SideNav-->
    <div class="contentWrap--withSideNav noScroll" ng-controller="controllerMarketingCategories">
    
    <!--Top Nav--> 
        <!--Main Content GENERIC--> 
        
            <div class="topNav--offset"></div>
            <div class="contentWrapInner" ng-init="getMarketingCategories()">
                <div class="pane pane--600px">
                    <div class="pane_header clear">
                        <div class="pane_title fleft h2">Please choose a marketing program type.</div>
                    </div>
                    <div class="pane_content">
                        <div class="cat-list"  ng-repeat="category in categories">
                            <div class="cat-slat">
                                <a id="categoryId" href="marketingprogram?marketingCategoryId={{category.marketingCategoryId}}" >
                                    <div class="cat-slat-title">{{category.marketingCategoryName}}</div>
                                </a>
                            </div>

                        </div>
                    </div>
                </div>   
            </div>
<!--       
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