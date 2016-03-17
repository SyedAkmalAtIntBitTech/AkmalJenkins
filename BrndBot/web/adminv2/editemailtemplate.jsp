<%-- 
    Document   : editemailtemplate
    Created on : Mar 15, 2016, 4:41:56 PM
    Author     : development.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Create New Email Template</title>
    </head>
    <%
    String emailModelId=request.getParameter("emailModelId");
    %>
       
    <body class="body-normal" ng-app ng-controller="emailTemplateController">        
        <jsp:include page="adminheader.jsp"/>
        <jsp:include page="froalaeditor_styles.jsp"/>
        <input id="emailModelIdTag" type="text" hidden value="<%=emailModelId%>"/>
        <div class="content-area contentEdit"  ng-init="emailTemplateModel()">
        <div class="content-area_header">
            <div class="header_path fleft" id="editTitle">Edit Email Template</div>
            <div class="header_path fleft" id="createTitle"> Create New Email Template</div>
            
        </div>
            <div class="inputSection col1of4" id="selectOrgranization" >
            <div class="input_Label">Please  select a organization</div>
            <input  id="organizationDetailsTypeId" class="input_Field" type="text"/>
          
        </div>
        <div class="inputSection col1of4" id="nameThisTemplate">
            <div class="input_Label">Name this template</div>
           <input id="emailModelName" class="input_Field" type="text"/>
        </div>    
         
            <div id="updateTemplate" class="md-button gray-button col10f9" >Update</div>
            
            <div id="editor">
                <div id='edit' style="margin-top: 30px;">
                </div>
            </div>
            
             
        <div id="uploadOnEdit">
            <div class="inputSection col1of4">
                <div class="input_Label">Please upload an image:</div>
            </div>
            <label class="fileContainer">
                <div id="triggerFile" class="md-button gray-button col10f9" >Upload</div>
                <input name="fileName" type="file" id="imageFileName"/>
            </label>
        </div>
           
            <div class="input_Label fleft" > </div>
            <a  id="saveTemplate" href="#"><div class="CTA_Button Button--Blue fleft pushUp_10 saveEdit" >Save</div></a>
             <a  ng-click="addEmailTemplate()" id="createNewTemplate" href="#"><div class="CTA_Button Button--Blue fleft pushUp_10 createTemplate" >Create New Template</div></a>
              </div>
      
           
        
            
    </body>
</html>