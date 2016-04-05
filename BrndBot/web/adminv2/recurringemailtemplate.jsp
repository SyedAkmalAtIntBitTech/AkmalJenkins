<%-- 
    Document   : recurringemailtemplate
    Created on : Apr 5, 2016, 12:09:11 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Create New Email Recurring Template</title>
    </head>
    
       
    <body class="body-normal" ng-app ng-controller="recurringEmail">        
        <jsp:include page="adminheader.jsp"/>
       
        <div class="content-area contentEdit"  ng-init="getRecurringTemplate()">
        <div class="content-area_header">
          
            <div class="header_path fleft" id="createEmailTemplate"> Create New  Recurring Email Template</div>
             <div class="header_path fleft" id="editEmailTemplate"> Edit Recurring Email Template</div>
             <div id="deleteTemplate" class="CTA_Button Button--Delete fright" ng-click="" >Delete Template</div>
             
        </div>
            
        <div class="inputSection col1of4" id="createTemplate">
            <div class="input_Label">Name this template</div>
           <input id="emailTemplateName" class="input_Field" type="text"/>
        </div>   
            
            <div class="inputSection col1of4" id="editTemplate">
            <div class="input_Label">Edit this template</div>
           <input id="emailTemplateName" class="input_Field" type="text"/>
        </div>  
            
                      <div class="inputSection col1of7" id="addHtmlData">
                         <textarea id="htmlData" rows="25" cols="90"></textarea>
        </div>  
             <div class="inputSection col1of7" id="editHtmlData">
                          <textarea id="htmlData" rows="25" cols="90"></textarea>
        </div>  
           
           
             <a  ng-click="createEmailTemplate()" id="createNewEmailTemplate" href="#"><div class="CTA_Button Button--Blue fleft pushUp_10 createTemplate" >Create New Template</div></a>
             <a  ng-click="saveEmailTemplate()" id="saveEmailTemplate" href="#"><div class="CTA_Button Button--Blue fleft pushUp_10 createTemplate" >Update</div></a>
              </div>
      
           
        
            
    </body>
</html>