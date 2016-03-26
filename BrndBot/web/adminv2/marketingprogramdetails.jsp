<%-- 
    Document   : marketingprogramdetials
    Created on : Mar 25, 2016, 6:23:44 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
<head>
    <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Marketing Program Details</title>
</head>     

<body class="body-normal overflowhide" data-ng-app="marketingprogramota">
<jsp:include page="adminheader.jsp"/>
<div  data-ng-controller="marketingProgramsController">
<jsp:include page="addonetimeactions.jsp"/>
    
        <div class="content-area">
            <div class="content-area_header">
                <!--<div class="header_path fleft"> Marketing Campaign Templates ></div>-->
                <div class="header_path fleft"> Marketing Programs</div>
                <div class="CTA_Button Button--Save fright">Save</div>
            </div>
            <div class="inputSection col1of1 pushUp_20">
                <div class="col1of2 fleft col-padding">
                     <div class="input_Label">Marketing Program Name</div>
                      <div class="col1of2">
                          <input class="input_Field" placeholder="Name"/>
                      </div>
                    <div class="input_Label pushUp_30">Marketing Program HTML</div>
                    <textarea class="input_Field_xlg">HTML</textarea>
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
                <div class="CTA_Button Button--Blue fright">Add Action</div>
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
                <ul class="slatArea">
                    <li class="listItem">
                        <div class="listCol col1of2 fleft">
                            <span class="listCol_Text fleft"> Action Name</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft"> 7</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <div class="CTA_Button Button--Gray fright">Manage Action</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
        <!--One Time Action Section-->
        <div class="content-area pushUp_30" >
               <div class="content-area_header">
                <div class="header_path fleft">  One Time Actions </div>
                <div id="addOneTimeActionsButton" class="CTA_Button Button--Blue fright" ng-click="getOneTimeActions()">Add Action</div>
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
                <ul class="slatArea">
                    <li class="listItem" ng-repeat="oneTimeActionData in oneTimeActionsData">
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{oneTimeActionData.actionName}}</span>
                        </div>
                         <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{oneTimeActionData.noOfDays}} days after start</span>
                        </div>
                        
                        <div class="listCol col1of4 fleft">
                            <span class="listCol_Text fleft">{{oneTimeActionData.actionType}}</span>
                        </div>
                        <div class="listCol col1of4 fleft">
                            <div class="CTA_Button Button--Gray fright" ng-click="getlistnum($index)">Manage Action</div> 
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
    
    
    