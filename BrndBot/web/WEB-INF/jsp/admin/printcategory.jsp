<%-- 
    Document   : addorganization
    Created on : Mar 2, 2016, 6:48:45 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

      <title>Add a new category</title>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div id="addPrintCategoryPopup" class="popUp_background">
        <div  class="popUp">
             <div class="content-area_header">
                <div class="popUp_title fleft"> Add a new category </div>
            </div>
            <div class="inputSection col1of2">
                
                <input id="printCategory" class="input_Field" type="text" placeholder="Category Name" />
               
                
                <div id='addPrintCategory' class="CTA_Button Button--Gray fleft pushUp_30" ng-click="addPrintCategory()">Create Category</div>
            </div>
        </div>
        </div> 
    </body>
</html>
