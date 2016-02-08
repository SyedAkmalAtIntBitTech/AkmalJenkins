<%-- 
    Document   : subcategory
    Created on : Dec 24, 2015, 6:51:23 PM
    Author     : Syed Akmal at IntBit Technologies.
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>BrndBot - Sub Category</title>
    <meta charset="UTF-8"/>
    <%@ include file="fonttypekit.jsp"%>
    <%@ include file="checksession.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="format-detection" content="telephone=no"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
    <link rel="stylesheet" type="text/css" href="css/style_detail_overlay-1.css"></link>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"></link>
    <link rel="shortcut icon" href="images/favicon.png"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/configurations.js"></script>
    <script src="js/angular.min.js"></script>
    <script src="js/dashboard.js"></script>
    <jsp:include page="basejsp.jsp" />
     
</head>    

<body class="" ng-app="myapp">
    <% 
        String id=request.getParameter("id");
    %>
    <%@include file="navbarv2.jsp" %> 
    <!--SideNav-->
    <div class="content-main" ng-controller="controllerCategories">
        <input type="hidden" id="id" value="<%=id%>"/>
    <!--Top Nav-->   
      <div class="top-nav">
        <div class="page-title-bar col-1of1"> 
            <a class="exit-button-icon" href="dashboard.jsp">
            <div class="exit-button-detail">
                    <img type="image/svg+xml" src="images/Icons/backbutton.svg" class="exit-button-icon" style="cursor:pointer;"></img>
            </div>
            </a>
            <div class="page-title-with-back page-title-font" id="ifnodata">What would you like to do today?</div>
        </div>   
    </div>
        <!--Main Content GENERIC--> 
        <div class="page-background">
        <div class="subcategory-content">
            <!--Inner Content Conatiner GENERIC-->
            <div class="page-inner-content-container">
                <div class="fleft content" ng-init="getSubCategories(<%=id%>)" >
                    <div class="page-content-title h2" id="promoteheading">Please select something to promote:</div>
                    <!--List Starts Here-->
                    <div class="main-container col-1of1  pushUp fleft" id="subcatdiv">
                    <ul class="subcategory-list-container fleft" >
                   <li class="subcategory-list-element col-1of1 fleft" ng-repeat="Sub in SubCategories" id="{{Sub.category_id}}" onclick="setSubCategoryID('{{Sub.category_id}}', '{{Sub.id}}', '{{Sub.sub_category_name}}', '{{Sub.external_source}}')">
                       <div class="subcat-list col-7of10 fleft">
                           {{Sub.sub_category_name}}
                       </div>
                       <div class="col-3of10 fleft">
                           <img type="image/svg+xml" src="images/Icons/nextArrow.svg" class="next-button-icon" ></img>
                       </div>
                   </li>
                </ul>
                </div>
            </div>
        </div>
    </div>
            </div>-->
        </div>
        </div>
<!--</div>-->
    </body>
</html>