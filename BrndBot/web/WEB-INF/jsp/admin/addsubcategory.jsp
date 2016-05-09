<%-- 
    Document   : addsubcategory
    Created on : Mar 11, 2016, 3:48:45 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Add a new category</title>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        
        <div id="addSubCategoryPopup" class="popUp_background">
            <div id="addOrganizationPopupDiv" class="popUp_backgroundlayer"></div>
        <div  class="popUp">
            <div class="content-area_header">
                <div class="popUp_title fleft"> Add a new sub-category </div>
            </div>
            <div class="inputSection col1of2" ng-init="getAllExternalSourceKeywordLookups()">
                <div class="input_Label">Sub Category Name</div>
                <input id="subCategoryName" class="input_Field" type="text"/>
                <div class="input_Label">Optional External Source</div>
                <select  class="input_Field" id="optionalExternalSource">
                    <option ng-repeat="ExternalSourceKeywordLookup in ExternalSourceKeywordLookups" class="input_Field" value="{{ExternalSourceKeywordLookup.externalSourceKeywordId}}">{{ExternalSourceKeywordLookup.externalSourceName}}-{{ExternalSourceKeywordLookup.externalSourceKeywordName}}</option>
                </select>
                
                <div id="addSubCategoryButton"  class="CTA_Button Button--Gray fleft pushUp_30" ng-click="addSubCategory()">Create Sub Category</div>
            </div>
        </div>
        </div> 
    </body>
</html>
