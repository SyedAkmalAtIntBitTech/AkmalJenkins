<%-- 
    Document   : addorganization
    Created on : Mar 2, 2016, 6:48:45 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
      <title>Add a new block</title>
    </head>
    <body ng-app  ng-controller="organizationcontroller">
        
        <div id="addEmailBlockPopUp" class="popUp_background">
             <div id="addOrganizationPopupDiv1" class="popUp_backgroundlayer"></div>
           
           
        <div  class="popUp">
            
             <div class="content-area_header">
                  
                <div class="popUp_title fleft"> Add a new Block </div>
            </div>
            <div class="inputSection col1of2" ng-init="getAllExternalSourceKeywordLookups()">
                <div class="input_Label">Block Name</div>
                <input id="emailBlockName" class="input_Field" type="text"/>
               
                <div class="input_Label">Optional External Source</div>
                <select class="input_Field" id="optionalExternalSource" >
                    <option ng-repeat="ExternalSourceKeywordLookup in ExternalSourceKeywordLookups" class="input_Field" value="{{ExternalSourceKeywordLookup.externalSourceKeywordId}}">{{ExternalSourceKeywordLookup.externalSourceName}}-{{ExternalSourceKeywordLookup.externalSourceKeywordName}}</option>
                </select>
                <div id="addEmail"  class="CTA_Button Button--Gray fleft pushUp_30" ng-click="addEmailBlock()">CreateBlock</div>
            </div>
        </div>
        </div> 
    </body>
</html>
