<%-- 
    Document   : allcompanies
    Created on : Mar 2, 2016, 5:07:27 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
       <link rel="shortcut icon" href="images/favicon.png"/>       
        <title>All Companies</title>
    </head>
    <body  class="body-normal" >
        <jsp:include page="header.jsp"/>
      <div class="content-area" ng-app ng-controller="allCompaniesController">
        <div class="content-area_header">
            <div class="header_path fleft">All Companies</div>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Company Name </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Organization </span>
                </div>
            </div>
            <ul class="slatArea"  ng-init="allCompanyDetails()">
                <li class="listItem" ng-repeat="companies in company.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{companies.companyName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">{{companies.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/companydetails" ng-click="setAllCompanyDetails(companies.companyId,companies.companyName,companies.organizationName)"><div class="CTA_Button Button--Gray fright">Manage Company</div></a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    </body>
</html>
