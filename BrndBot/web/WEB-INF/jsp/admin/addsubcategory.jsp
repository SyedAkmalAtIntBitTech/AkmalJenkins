<%-- 
    Document   : addsubcategory
    Created on : Mar 11, 2016, 3:48:45 PM
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
        <div id="addSubCategoryPopup" class="popUp_background">
        <div  class="popUp">
            <div class="content-area_header">
                <div class="popUp_title fleft"> Add a new sub-category </div>
            </div>
            <div class="inputSection col1of2" ng-init="getAllExternalSourceKeywordLookups()">
                <div class="input_Label">Sub Category Name</div>
                <input id="subCategoryName" class="input_Field" type="text"/>
                <div class="input_Label">Optional External Source</div>
                <select class="input_Field" id="optionalExternalSource">
                    <option class="input_Field" value="{{ExternalSourceKeywordLookups.externalSourceKeywordId}}">{{ExternalSourceKeywordLookups.externalSourceName}}-{{ExternalSourceKeywordLookups.externalSourceKeywordName}}</option>
                </select>
                
                <div id="addSubCategoryButton"  class="CTA_Button Button--Gray fleft pushUp_30" ng-click="addSubCategory()">Create Sub Category</div>
            </div>
        </div>
        </div> 
    </body>
</html>
