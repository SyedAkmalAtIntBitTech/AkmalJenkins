<%-- 
    Document   : organization
    Created on : Mar 2, 2016, 6:23:44 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="shortcut icon" href="../images/favicon.png"/>
         <script type="text/javascript" src="../adminv2/adminjs/basejs.js"/>         
        <script src="../js/alertmessage.js" type="text/javascript"></script>
        <script src="../js/configurations.js" type="text/javascript"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="../adminv2/adminjs/organization.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>BrndBot-Admin Organization</title>
    </head>
    <body class="body-normal" ng-app  ng-controller="organizationcontroller" >        
        <jsp:include page="adminheader.jsp"/>
        <jsp:include page="addorganization.jsp"/>
        <div class="content-area" >
        <div class="content-area_header">
            <div class="header_path fleft"> All Organizations </div>
            <div class="CTA_Button Button--Blue fright" id="addorg">Add Organization</div>
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
                        <a href="organizationdetails.jsp?orgId={{organizations.organizationId}}"><div class="CTA_Button Button--Gray fright">Manage Org</div></a>
                    </div>
                </li>                
            </ul>
        </div>
    </div>
  
    </body>
</html>
