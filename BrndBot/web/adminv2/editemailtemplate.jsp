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
       
    <body class="body-normal" >        
        <jsp:include page="adminheader.jsp"/>
        <jsp:include page="froalaeditor_styles.jsp"/>
        
        <div class="content-area contentEdit" >
        <div class="content-area_header">
            <div class="header_path fleft" id="editTitle">Edit Email Template</div>
            <div class="header_path fleft" id="createTitle"> Create New Email Template</div>
            
        </div>
        <div class="inputSection col1of4" id="selectOrgranization">
            <div class="input_Label">Please  select a organization</div>
          <select class="input_Field" id="organizationDetailsTypeId">
                    <option class="input_Field" value="2">organization</option>
                    <option class="input_Field" value="1">group</option>
                </select>
        </div>
        <div class="inputSection col1of4" id="nameThisTemplate">
            <div class="input_Label">Name this template</div>
           <input id="templateName" class="input_Field" type="text"/>
        </div>    
            
            
        <label class="fileContainer">
            <div id="triggerfile" class="md-button gray-button col10f9" >Upload</div>
            <input type="file"/>
        </label>
           
            <div id="editor">
                <div id='edit' style="margin-top: 30px;">
                </div>
            </div>
            
            
        <div id="uploadOnEdit">
            <div class="inputSection col1of4">
                <div class="input_Label">Please upload an image:</div>
            </div>
            <label class="fileContainer">
                <div id="triggerfile" class="md-button gray-button col10f9" >Upload</div>
                <input type="file"/>
            </label>
        </div>
           
            <div class="input_Label fleft" > </div>
            <a  id="saveTemplate" href="addemailtemplate.jsp"><div class="CTA_Button Button--Blue fleft pushUp_10 saveEdit" >Save</div></a>
             <a id="createNewTemplate" href="addemailtemplate.jsp"><div class="CTA_Button Button--Blue fleft pushUp_10" >Create New Template</div></a>
              </div>
      
           
        
            
    </body>
</html>