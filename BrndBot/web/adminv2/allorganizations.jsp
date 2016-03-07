<%-- 
    Document   : allorganizations
    Created on : Mar 2, 2016, 6:23:44 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="../js/configurations.js" type="text/javascript"></script>
        <script data-require="angular.js@*" data-semver="1.2.12" src="http://code.angularjs.org/1.2.12/angular.js"></script>
        <script src="adminjs/organisation.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="../js/angular.min.js"></script>
        <title>BrndBot-Admin Organization</title>
    </head>
    <body class="body-normal" >
        <jsp:include page="addorganization.jsp"/>
        <jsp:include page="adminheader.jsp"/>
        <div class="content-area" ng-app  ng-controller="organizationcontroller" >
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
                <li class="listItem" ng-repeat="organizations in organizationDetails">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{organizations.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="organizationdetails.jsp"><div class="CTA_Button Button--Gray fright">Manage Org</div></a>
                    </div>
                </li>                
            </ul>
        </div>
    </div>
  
    </body>
</html>
