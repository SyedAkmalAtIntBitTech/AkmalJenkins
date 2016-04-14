<%-- 
    Document   : category
    Created on : Apr 6, 2016, 3:44:27 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="shortcut icon" href="images/favicon.png">
        <title>Category</title>
    </head>
    <body class="body-normal">
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>       
   
    <!--Main Content Wrap-->
    <div class="contentWrap--withSideNav noScroll" ng-app ng-controller="userController">
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">
                 <a class="exit-button-icon" href="dashboard.jsp">
          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            
            </a>
                
            </div>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner" ng-init="displayCategory()">
            <div class="pane pane--600px">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Please select a category of email</div>
                </div>
                <div class="pane_content">
                    <div class="cat-list" ng-repeat="displayAllCategory in displayAllCategories">
                        <div class="cat-slat">
                            <a href="emailsubcategory.jsp">
                                <div class="cat-slat-title">{{displayAllCategory}}</div>
                            </a>
                        </div>
                        
                    </div>
                </div>
            </div>   
        </div>
    </div>
    
    
    
</body>
</html>
