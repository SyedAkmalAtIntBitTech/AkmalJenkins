<%-- 
    Document   : marketingcategory
    Created on : Mar 28, 2016, 3:07:47 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marketing Program category</title>
        <link rel="shortcut icon" href="images/favicon.png"/>        
    </head>
  <%
String organizationID=request.getParameter("organizationId");
String marketingCategoryId=request.getParameter("marketingCategoryId");
%>
<body class="body-normal" ng-app  ng-controller="organizationcontroller" >
    <input id="marketingCategoryId" type="text" hidden value="<%=marketingCategoryId%>"/>
    <input id="organizationIdTag" type="text" hidden value="<%=organizationID%>"/>
    <jsp:include page="header.jsp"/>
     <jsp:include page="addmarketingtemplate.jsp"/>
    <div class="content-area" ng-init="organizationdetails()">
        <div class="content-area_header"  ng-init="getAllMarketingCategoryById()">
            <div  class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="organizationdetails.jsp?organizationId=<%=organizationID%>">{{organizationDetails.organizationName}}</a>  > </div>
            <div class="header_title fleft">{{marketingCategoryTitle.marketingCategoryName}}  </div>
            <div id="deleteCategoryButton" class="CTA_Button Button--Delete fright" ng-click="deleteMarketingCategory(<%=marketingCategoryId%>)">Delete Category</div>
            <div class="CTA_Button Button--Blue fright" id="addMarketingTemplate" ng-click="getAllNonAddedMarketingProgram()">Add Template</div>
        </div>
        <div class="slatSection" >
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">Template Names</span>
                </div>
            </div>
            <ul class="slatArea" ng-init="marketingProgramsById()">
                <li class="listItem" ng-repeat="getMarketingProgram in getMarketingPrograms">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{getMarketingProgram.marketingProgramName}}</span>
                        
                    </div>
                    <div class="listCol col1of4 fright">
                        <div ng-click="deleteMarketingCategoryProgram(getMarketingProgram.marketingCategoryProgramId)" class="CTA_Button Button--Gray fright">Remove Template</div>
                    </div>
                </li>
                
            </ul>
            
        </div>
        
    </div>
</html>
