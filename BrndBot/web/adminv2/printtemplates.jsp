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
        <title>BrndBot-Admin Organization</title>
    </head>
    <body class="body-normal" ng-app  ng-controller="printTemplates" >        
        <jsp:include page="adminheader.jsp"/>
        <div class="content-area" ng-init="printTemplateScope()">
        <div class="content-area_header">
            <div class="header_path fleft"> Print Templates </div>
            <a class="CTA_Button Button--Blue fright stylenone" href="addprinttemplate.jsp">Add New Templates</a>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft"></div>
            </div>
            <ul class="slatArea" ng-repeat="printDisplays in printDisplay.slice().reverse()">
                <li class="listItem" >
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{printDisplays.printModelName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft"></div>
                    <div class="listCol col1of4 fleft">
                        <a href="#"><div class="CTA_Button Button--Gray fright">View/Edit</div></a>
                    </div>
                </li>  
               
            </ul>
        </div>
    </div>
  
    </body>
</html>
