<%-- 
    Document   : marketingprogramsnew
    Created on : Jan 5, 2016, 12:00:42 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Marketing Programs</title>
        <meta charset="UTF-8"></meta>
        <%@ include file="fonttypekit.jsp"%>         
        <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
        <!--<link rel="stylesheet" href="css/bootstrap.min.css"></link>-->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
        <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
        <script src="js/configurations.js"></script>
        <script src="js/angular.min.js"></script>
        <link href="css/marketingprograms.css" rel="stylesheet" type="text/css"/>
        <jsp:include page="basejsp.jsp"/>
        <script src="js/marketingprogram.js" type="text/javascript"></script>
</head>
    <%!
       String category_id = ""; 
    %>
    
    <%
        category_id = request.getParameter("categoryid");
    %>
    <body ng-app >
        <input id="categoryidHidden" type="hidden" value="<%=category_id%>"></input>
            <input id="programidHidden" type="hidden" value="0"></input>
    <div class="col-md-11 col-md-offset-1 " ng-controller="controllerMarketingCategories">
    <%@include file="navbarv2.jsp" %>                    
    <!--Top Nav-->   
    <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <div id="selmarprog" class="page-title-regular page-title-font"> Select a category marketing program:</div>
            <div class="page-cta-container">
                <a id="cont" href="" class=" fleft">
                    <div  ng-click="submitclick()" class=" add-button md-button">Continue</div>    
                </a>
            </div>
        </div>
    </div>
    <div class="page-background padding0left">
        <div class="subcategory-content subcategory-content100width">
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container page-inner-content-containerfullwidth ">
                <div class="contentleft width50" ng-init="getMarketingPrograms()" >
                    <div class="page-content-title h2" id="promoteheading">Marketing Programs</div>
                    <!--List Starts Here-->
                    <div class="main-container main-container50width col-1of1  pushUp fleft" >
                    <ul class="subcategory-list-container fleft" >
                        <div ng-repeat="program in programs">
                            <li class="prognamelst subcategory-list-element subcat{{program.id}} col-1of1 fleft" ng-click="showhtmldata(program.id,program.html_data)">
                            
                                <div class="subcat-list col-7of10 fleft"  id="{{program.id}}">
                                    {{program.name}}
                                </div>
                                <div class="col-3of10 fleft">
                                    <img type="image/svg+xml" src="images/Icons/nextArrow.svg" class="next-button-icon" ></img>
                                </div>                            
                            </li>
                        </div>
                    </ul>
                    </div>
                </div>
                <div class="contentright width50" ng-init="getMarketingPrograms()" >
                    <div class="lftpaddesc">Description</div>
                    <!--List Starts Here-->
                    <div class="main-container main-container50widthright col-1of1  pushUp fright lftpad" >
                         <div class="htmldatacontainer">
                             <iframe id="html_data" ></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>       
    </body>
</html>