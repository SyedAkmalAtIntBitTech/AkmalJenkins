<%-- 
    Document   : emailblockmodels
    Created on : Mar 21, 2016, 6:00:25 PM
    Author     : development.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Email-Templates</title>
    </head>
   
      <jsp:include page="adminheader.jsp"/>
    <body class="body-normal" ng-app  ng-controller="emailBlocksController" >        
      
        <div class="content-area" ng-init="emailBlocksModel()" >
        <div class="content-area_header">
            <div class="header_path fleft"> Email Block Templates Global List </div>
            <a href="editemailblockmodels.jsp?edit=no"><div class="CTA_Button Button--Blue fright" id="addTemplate" >Add New Template</div></a>
        </div>
            <div class="slatSection">
            <div class="slatHeaders">
                
            </div>
            <ul class="slatArea"  ng-repeat="emailblocks in emailBlocksModelData.slice().reverse()">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{emailblocks.emailBlockModelName}}</span>   
                    </div>
                    
                    
                    <div class="listCol col1of2 fleft">
                        <a href="editemailblockmodels.jsp?edit=yes&emailBlockModelId={{emailblocks.emailBlockModelId}}"><div class="CTA_Button Button--Gray fright">VIEW/EDIT</div></a>

                    </div>
                </li>                
            </ul>
        </div>
    </div>
  
    </body>
</html>