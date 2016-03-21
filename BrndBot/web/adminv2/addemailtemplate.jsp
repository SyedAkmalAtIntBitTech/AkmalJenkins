<%-- 
    Document   : addemailtemplate
    Created on : Mar 16, 2016, 2:03:17 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="addEmailTemplatePopUp" class="popUp_background">
            <div  class="popUpAllTemplate" >
            <div class="content-area_header" >
                <div class="popUp_title fleft"> Add a Template</div>
                <div id="relateEmailTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relateEmailTemplateSubCategory()">Add Template</div>
                <div id="relatePrintTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relatePrintTemplateSubCategory()">Add Template</div>
                <div id="relateImageTemplateAddButton"  class="CTA_Button Button--Blue fright"  ng-click="relateImageTemplateSubCategory()">Add Template</div>
            </div>
            <div class="slatHeaders">
                
            </div>
                <ul class="slatArea" ng-if="location.path() != '/printsubcategorydetails.jsp'" >
                    <li class="listItem cursorpointer" ng-repeat="nonAddedprintModelsBySubCategorys in nonAddedPrintModelsBySubCategory" ng-click="select(nonAddedprintModelsBySubCategorys)" ng-class="{active: isActive(nonAddedprintModelsBySubCategorys)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedprintModelsBySubCategorys.printModelName}}</span>   
                        </div>
                    </li>                
                </ul>
                <ul class="slatArea" ng-if="location.path() != '/imagesubcategorydetails.jsp'" >
                    <li class="listItem cursorpointer" ng-repeat="nonAddedimageModelsBySubCategorys in nonAddedImageModelsBySubCategory" ng-click="select(nonAddedimageModelsBySubCategorys)" ng-class="{active: isActive(nonAddedimageModelsBySubCategorys)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedimageModelsBySubCategorys.imageModelName}}</span>   
                        </div>
                    </li>                
                </ul>
                <ul class="slatArea" ng-if="location.path() != '/emailsubcategorydetails.jsp'" >
                    <li class="listItem cursorpointer" ng-repeat="nonAddedemailModelsBySubCategorys in nonAddedEmailModelsBySubCategory" ng-click="select(nonAddedemailModelsBySubCategorys)" ng-class="{active: isActive(nonAddedemailModelsBySubCategorys)}">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft">{{nonAddedemailModelsBySubCategorys.emailModelName}}</span>
                        </div>
                    </li>                
                </ul>
        </div>
        </div> 
    </body>
</html>
