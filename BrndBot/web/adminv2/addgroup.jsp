<%-- 
    Document   : addgroup
    Created on : Apr 1, 2016, 6:34:21 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body ng-app ng-controller="allCompaniesController">
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="addGroup" class="popUp_background">
            <div  class="popUpAllTemplate" >
            <div class="content-area_header" >
                <div class="popUp_title fleft"> Add a Group</div>
                <div class="CTA_Button Button--Blue fright" id="addTemplateButton" id="addGroupDetails" ng-click="addGroupTemplate()">Add Group</div>
            </div>
                <ul class="slatArea" id="noGroup" hidden="">
                    <li class="listItem cursorpointer" >
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft" id="noGroupMessage">No</span>   
                        </div>
                    </li>                
                </ul>
            <div class="slatHeaders">
                
            </div>
                <ul class="slatArea"  ng-init="groupsDisplay()">
                    <li class="listItem cursorpointer" ng-repeat="groupDetails in groupDetail" ng-click="selectedItemsList(groupDetails)" ng-class="{active: isActiveMode(groupDetails)}">
                        <div class="listCol col1of2 fleft">
                            <span id ="organizationGroupName" class="listCol_Text fleft">{{groupDetails.organizationName}}</span>   
                                
                        </div>
                    </li>                
                </ul>
        </div>
        </div> 
    </body>
</html>
