<%-- 
    Document   : addmarketingtemplate
    Created on : Mar 30, 2016, 1:20:33 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="addMarketingTemplatePopUp" class="popUp_background">
            <div  class="popUpAllTemplate" >
            <div class="content-area_header" >
                <div class="popUp_title fleft"> Add a Template</div>
                <div class="CTA_Button Button--Blue fright" id="addTemplateButton" id="relateNonAddedMarketingPrograms" ng-click="addMarketingtemplate()">Add Template</div>
            </div>
                <ul class="slatArea" id="noMarketingTemplatesMessage" hidden="">
                    <li class="listItem cursorpointer" >
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft" id="noMarketingMessage">No</span>   
                        </div>
                    </li>                
                </ul>
            <div class="slatHeaders">
                
            </div>
                <ul class="slatArea" >
                    <li class="listItem cursorpointer" ng-repeat="getNonAddedMarketingProgram in getNonAddedMarketingPrograms" ng-click="selectedItems(getNonAddedMarketingProgram)" ng-class="{active: isActiveMode(getNonAddedMarketingProgram)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{getNonAddedMarketingProgram.marketingProgramName}}</span>   
                                
                        </div>
                    </li>                
                </ul>
        </div>
        </div> 
    </body>
</html>
