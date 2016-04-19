<%-- 
    Document   : recurringemails
    Created on : Apr 4, 2016, 7:31:27 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png"/> 
        <title>BrndBot-Admin Recurring Emails</title>
    </head>
    <body class="body-normal" ng-app  ng-controller="recurringEmail">
        <jsp:include page="header.jsp"/>
        
        
        <div class="content-area" >
        <div class="content-area_header">
            <div class="header_path fleft">Recurring Email Template Global List</div>
            <a href="recurringemailtemplate.jsp?edit=no"><div class="CTA_Button Button--Blue fright">Add New Template</div></a>
        </div>
        <div class="slatSection" ng-init="recurring()">
            <div class="slatHeaders">
                
               
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="recurringEmailDetails in recurringEmailDetail.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{recurringEmailDetails.templateName}}</span>
                        
                    </div>
                  
                    <div class="listCol col1of2 fleft">
                        <a href="recurringemailtemplate.jsp?recurringEmailTemplateId={{recurringEmailDetails.recurringEmailTemplateId}}&edit=yes"><div class="CTA_Button Button--Gray fright">View/Edit</div></a>

                    </div>
                </li>                
            </ul>
        </div>
    </div>
    </body>
</html>
