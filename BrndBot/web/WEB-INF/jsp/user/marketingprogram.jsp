<%-- 
    Document   : Marketing category
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
  <% String marketingCategoryId = request.getParameter("marketingCategoryId"); %>
    <!--Main Content Wrap-->
    <div class="contentWrap--withSideNav noScroll" ng-app ng-controller="userController">
        <input id="marketingCategoryId" type="hidden" value="<%= marketingCategoryId %>"
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">
                 <a class="exit-button-icon" href="createmarketingprogram">
          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
            
            </a>
                
            </div>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Select a category marketing program:</span>
                <div class=" fright">
                   <div class="page-cta-container">
                       <a id="cont" class=" fleft" href="usermarketingprogram">
                        <div class=" add-button md-button">Continue</div>    
                    </a>
                    </div>
                    
                </div>
            </div>
       
        <div class="topNav--offset"></div>
        <div class="contentWrapInner" ng-init="displayMarketingProgramByCategoryId()">
            <div class="pane pane--600px" style="width:50%;float:left;margin-left: 94px;">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Marketing Programs</div>
                </div>
                
                <div class="pane_content">
                    
                    <div class="cat-list" ng-repeat="displayAllMarketingProgram in displayMarketingPrograms">
                        
                        <div  class="{{displayAllMarketingProgram.marketingProgramId}}">
                            <ul>
                                <li class="programList nameList{{displayAllMarketingProgram.marketingProgramId}}" ng-click="showhtmldata(displayAllMarketingProgram.marketingProgramId,displayAllMarketingProgram.htmlData)">
                                    <div id="{{displayAllMarketingProgram.marketingProgramId}}" class="cat-slat-titles">{{displayAllMarketingProgram.marketingProgramName}}</div>
                                </li>
                            </ul>
                        </div>
                        
                    </div>
                </div>
            </div>   
            <div class="pane pane--600px" style="width:30%;float:right;margin-right: 92px;">
                <div class="pane_header clear">
                      <div class="pane_title fleft h2">Description</div>
                </div>
                <div class="pane_content ">
                   <div class="main-container main-container50widthright col-1of1  pushUp fright lftpad" >
                         <div class="htmldatacontainer cat-slat-titles" id="htmlData">
                        </div>
                    </div>
                </div>
            </div>   
        </div>
        </div>
    
    
    
    
</body>
</html>
