<%-- 
    Document   : addrecurringtemplatepopup
    Created on : Apr 1, 2016, 7:16:59 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        <div id="addRecurringEmailTemplatePopUp" class="popUp_background">            
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
            <div  class="popUpAllTemplate" >
            <div class="content-area_header" >
                <div class="popUp_title fleft"> Add a Template</div>
                <div id="relateRecurringEmailTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="saveOrganizationRecurringEmail()">Add Template</div>
            </div>
            <div class="slatHeaders">
                
            </div>
                <ul class="slatArea" ng-init="getAllNonAddedRecurringEmail()">
                    <li class="listItem cursorpointer" ng-repeat="nonAddedRecurringEmail in nonAddedRecurringEmails" ng-click="select(nonAddedRecurringEmail)" ng-class="{active: isActive(nonAddedRecurringEmail)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedRecurringEmail.templateName}}</span>   
                        </div>
                    </li>    
<!--                    <li class="listItem cursorpointer" ng-repeat="nonAddedRecurringEmail in nonAddedRecurringEmails">
                        <div class="listCol col1of2 fleft">
                            <div class="listCol_Text fleft">{{nonAddedRecurringEmail.recurringEmailTemplateId}}</div>   
                        </div>
                    </li>  -->
                </ul>
            </div>
        </div> 
    </body>
</html>
