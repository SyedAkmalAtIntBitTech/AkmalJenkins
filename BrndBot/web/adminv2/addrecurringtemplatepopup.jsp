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
                <div id="relateEmailTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relateEmailTemplateSubCategory()">Add Template</div>
                <div id="relatePrintTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relatePrintTemplateSubCategory()">Add Template</div>
                <div id="relateImageTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relateImageTemplateSubCategory()">Add Template</div>
            </div>
            <div class="slatHeaders">
                
            </div>
                <ul class="slatArea" >
                    <li class="listItem cursorpointer" ng-repeat="nonAddedprintModelsBySubCategorys in nonAddedPrintModelsBySubCategory" ng-click="select(nonAddedprintModelsBySubCategorys)" ng-class="{active: isActive(nonAddedprintModelsBySubCategorys)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedprintModelsBySubCategorys.printModelName}}</span>   
                        </div>
                    </li>                
                </ul>
            </div>
        </div> 
    </body>
</html>
