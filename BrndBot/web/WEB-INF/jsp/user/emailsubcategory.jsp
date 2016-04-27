<%-- 
    Document   : subcategory
    Created on : Apr 6, 2016, 3:44:27 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Sub-category</title>
    </head>
    <body class="body-normal">
        <%@include file="header.jsp" %>       
        <%@include file="navbar.jsp" %>       
        <!--Main Content Wrap-->
        <% String categoryId = request.getParameter("categoryId");%>
        <div class="contentWrap--withSideNav noScroll" ng-app ng-controller="userController">
            <input id="categoryId" value="<%=categoryId%>" type="hidden"/>
            <div class="topNav topNav-withSubnav clear">
                <div class="topNav--BackButton fleft">
                    <a class="exit-button-icon" href="emailcategory">

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
            <div class="contentWrapInner">
                <!-- <div class="pane pane--catSelected pane--600px">
                     <div class="cat-slat-title-selected">Category Name Here</div>  
                 </div>-->
                <div class="pane pane--600px" >
                    <div class="pane_header clear">
                        <div class="pane_title fleft h2">Please select a subcategory</div>
                    </div> 
                    <div class="pane_content" ng-init="displaySubCategory()">
                        <div class="subcat-list">
                            <div class="cat-slat" ng-repeat="displayAllSubCategory in displayAllSubCategories.slice().reverse()">
                                <a href="emailexternalsource?categoryId=<%=categoryId%>&subCategoryId={{displayAllSubCategory.subCategoryId}}">
                                    <div class="cat-slat-title">{{displayAllSubCategory.subCategoryName}}</div>
                                </a>
                            </div>
                            <div class="cat-slat" ng-repeat="displayAllSubCategory in displayAllSubCategories.slice().reverse()">
                                <a href="emailsubjects?categoryId=<%=categoryId%>&subCategoryId={{displayAllSubCategory.subCategoryId}}">
                                    <div class="cat-slat-title">Non MindBody Data</div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
        </div>

    </body>
</html>
