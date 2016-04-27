<%-- 
    Document   : emailblocktemplate
    Created on : Mar 30, 2016, 7:24:06 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body ng-app  ng-controller="organizationcontroller">
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="EmailBlockPopUp" class="popUp_background">
            <div  class="popUpAllTemplate" >
            <div class="content-area_header" >
                <div class="popUp_title fleft"> Add a Template</div>
                <div class="CTA_Button Button--Blue fright" id="addTemplateButton" ng-click="addEmailBlocktemplate()">Add Template</div>
            </div>
                <ul class="slatArea" id="noEmailTemplatesMessage" hidden="">
                    <li class="listItem cursorpointer" >
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft" id="noEmailMessage">No</span>   
                        </div>
                    </li>                
                </ul>
            <div class="slatHeaders">
                
            </div>
                
                <ul class="slatArea">
                    <li class="listItem cursorpointer" ng-repeat="nonAddedEmailBlockDetail in nonAddedEmailBlockDetails" ng-click="selectedBlockItems(nonAddedEmailBlockDetail)" ng-class="{active: isActiveBlockMode(nonAddedEmailBlockDetail)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedEmailBlockDetail.emailBlockModelName}}</span>   
                                
                        </div>
                    </li>                
                </ul>
        </div>
        </div> 
    </body>
</html>