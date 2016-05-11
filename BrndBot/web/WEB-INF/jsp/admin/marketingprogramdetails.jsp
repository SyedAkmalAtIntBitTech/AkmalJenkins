<%-- 
    Document   : marketingprogramdetials
    Created on : Mar 25, 2016, 6:23:44 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
<head>
    <link rel="shortcut icon" href="images/favicon.png"/>
        <title>BrndBot-Admin Marketing Program Details</title>
</head>    
<%
    String marketingProgramId=request.getParameter("marketingProgramId");

%>

<body class="body-normal overflowhide" data-ng-app="marketingprogramota">
    <input id="marketingProgramIdTag" type="text" hidden value="<%=marketingProgramId%>"/>
<jsp:include page="header.jsp"/>
<div  data-ng-controller="marketingProgramsController">
<jsp:include page="addonetimeactions.jsp"/>
<jsp:include page="addonetimeactionpopup.jsp"/>
<div ng-init="getMarketingProgramActionsById()">
    <div class="content-area" >
            <div class="content-area_header">
                <!--<div class="header_path fleft"> Marketing Campaign Templates ></div>-->
                <div class="header_path fleft"> Marketing Programs</div>
                <div id="updateMarketingProgramSaveButton" class="CTA_Button Button--Save fright"  ng-click="updateMarketingProgramActions()">Save</div>
                <div id="addMarketingProgramSaveButton" class="CTA_Button Button--Save fright"  ng-click="saveMarketingProgramActions()">Save</div>
            </div>
            <div class="inputSection col1of1 pushUp_20">
                <div class="col1of2 fleft col-padding">
                     <div class="input_Label">Marketing Program Name</div>
                      <div class="col1of2">
                          <input id="marketingProgramName" class="input_Field" placeholder="Name" value="{{getAllMarketingProgram.marketingProgramName}}"/>
                      </div>
                    <div class="input_Label pushUp_30">Marketing Program HTML</div>
                    <textarea id="marketingProgramHtml" class="input_Field_xlg">{{getAllMarketingProgram.htmlData}}</textarea>
                </div>
<!--                <div class="col1of4 fleft col-padding">
                    <div class="input_Label">Campaign Type</div>
                    <div class="input_Field">Dropdown</div>
                </div>-->                 
            </div>
        </div>
        
        <!--Recurring Action Section-->
        <div class="content-area pushUp_30">
               <div class="content-area_header">
                <div class="header_path fleft">  Recurring Actions </div>
                <div id="addRecurringActionsButton"  class="CTA_Button Button--Blue fright" ng-click="addRecurringActions()" >Add Action</div>
            </div>
            <div class="slatSection">
                <div class="slatHeaders">
                    <div class="listHeaderCol col1of2 fleft">
                        <span class="listCol_Header fleft"> Action Names </span>
                    </div>
                    <div class="listHeaderCol col1of4 fleft">
                        <span class="listCol_Header fleft"> Send after # of days </span>
                    </div>
                </div>
                <ul class="slatArea" ng-repeat="marketingAction in marketingProgramActions">
                    <li class="listItem" ng-show="marketingAction.is_recurring==true">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft"> {{marketingAction.title}}</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{marketingAction.days}}</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <div class="CTA_Button Button--Gray fright" ng-click="getlistnum($index,'recurring')">Manage Action</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
        <!--One Time Action Section-->
        <div class="content-area pushUp_30" >
               <div class="content-area_header">
                <div class="header_path fleft">  One Time Actions </div>
                <div id="addOneTimeActionsButton" class="CTA_Button Button--Blue fright" ng-click="addOneTimeActions()">Add Action</div>
            </div>
            <div class="slatSection">
                <div class="slatHeaders">
                    <div class="listHeaderCol col1of4 fleft">
                        <span class="listCol_Header fleft"> Action Names </span>
                    </div>
                    <div class="listHeaderCol col1of4 fleft">
                        <span class="listCol_Header fleft"> # of Days </span>
                    </div>
                     <div class="listHeaderCol col1of4 fleft">
                        <span class="listCol_Header fleft"> Action Type </span>
                    </div>
                </div>
                <ul class="slatArea" ng-repeat="marketingOneTimeAction in marketingProgramActions">
                    <li class="listItem" ng-show="marketingOneTimeAction.is_recurring==false">
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{marketingOneTimeAction.title}}</span>
                        </div>
                         <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{marketingOneTimeAction.days}} days after start</span>
                        </div>
                        
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{marketingOneTimeAction.type}}</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <div class="CTA_Button Button--Gray fright" ng-click="getlistnum($index,'oneTimeActions')">Manage Action</div> 
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
    
    
    