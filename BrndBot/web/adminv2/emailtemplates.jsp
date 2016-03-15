<%-- 
    Document   : emailtemplates
    Created on : Mar 15, 2016, 1:17:34 PM
    Author     : development.
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Email-Templates</title>
    </head>
    <body class="body-normal" ng-app  ng-controller="emailTemplateController" >        
        <jsp:include page="adminheader.jsp"/>
        <div class="content-area" >
        <div class="content-area_header">
            <div class="header_path fleft"> Email Templates </div>
            <a href="editemailtemplate.jsp?edit=no"><div class="CTA_Button Button--Blue fright" id="addTemplate">Add New Template</div></a>
        </div>
        <div class="slatSection">
            <div class="slatHeaders">
                
            </div>
            <ul class="slatArea" >
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">Template1</span>   
                    </div>
                    
                    
                    <div class="listCol col1of2 fleft">
                        <a href="editemailtemplate.jsp?edit=yes"><div class="CTA_Button Button--Gray fright">VIEW/EDIT</div></a>

                    </div>
                </li>                
            </ul>
            <ul class="slatArea" >
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">Template2</span>   
                    </div>
                    
                    
                    <div class="listCol col1of2 fleft">
                        <a href="editemailtemplate.jsp"><div class="CTA_Button Button--Gray fright">VIEW/EDIT</div></a>

                    </div>
                </li>                
            </ul>
            <ul class="slatArea" >
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">Template3</span>   
                    </div>
                    
                    
                    <div class="listCol col1of2 fleft">
                        <a href="#"><div class="CTA_Button Button--Gray fright">VIEW/EDIT</div></a>

                    </div>
                </li>                
            </ul>
        </div>
    </div>
  
    </body>
</html>