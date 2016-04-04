<%-- 
    Document   : recurringemails
    Created on : Apr 4, 2016, 7:31:27 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/> 
        <title>BrndBot-Admin Recurring Emails</title>
    </head>
    <body class="body-normal" ng-app  ng-controller="organizationcontroller">
        <jsp:include page="adminheader.jsp"/>
        
        <div class="content-area" >
        <div class="content-area_header">
            <div class="header_path fleft"> All Organizations </div>
            <div class="CTA_Button Button--Blue fright" id="addOrganization">Add Organization</div>
        </div>
        <div class="slatSection" ng-init="organization()">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Organization Name </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Set Type </span>
                </div>
            </div>
            <ul class="slatArea" >
                <li class="listItem" ng-repeat="organizations in organizationDetails.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{organizations.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">{{organizations.organizationTypeName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="organizationdetails.jsp?organizationId={{organizations.organizationId}}"><div class="CTA_Button Button--Gray fright">Manage Org</div></a>

                    </div>
                </li>                
            </ul>
        </div>
    </div>
    </body>
</html>
