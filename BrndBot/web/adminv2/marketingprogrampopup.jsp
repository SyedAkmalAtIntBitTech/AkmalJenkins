<%-- 
    Document   : marketingprogrampopup
    Created on : Mar 28, 2016, 12:32:41 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

      <title>Add a new block</title>
    </head>
    <body>

        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="addMarketingPopUp" class="popUp_background" ng-app  ng-controller="organizationcontroller">
        <div  class="popUp">
             <div class="content-area_header">
                <div class="popUp_title fleft"> Add a new Category </div>
            </div>
            <div class="inputSection col1of2">
                <div class="input_Label">Marketing Category Name</div>
                <input id="marketingCategoryName" class="input_Field" type="text"/>
                <div id="addcategory"  class="CTA_Button Button--Gray fleft pushUp_30" ng-click="addEmailCategory()">Create Category</div>
            </div>
        </div>
        </div> 
 </body>
</html>