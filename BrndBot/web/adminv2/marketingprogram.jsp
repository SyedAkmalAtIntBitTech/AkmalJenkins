<%-- 
    Document   : marketingprogram
    Created on : Mar 28, 2016, 5:50:33 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="../images/favicon.png"/>
        <title>BrndBot-Admin Marketing Program</title>
    </head>
   
      <jsp:include page="adminheader.jsp"/>
    <body class="body-normal" ng-app  ng-controller="organizationcontroller" >        
      
        <div class="content-area" ng-init="gellAllMarketingProgramList()">
        <div class="content-area_header">
            <div class="header_path fleft"> Marketing Program </div>
            <a href="marketingprogramdetails.jsp"><div class="CTA_Button Button--Blue fright" id="addTemplate" >Add New Program</div></a>
        </div>
            <div class="slatSection">
            <div class="slatHeaders">
                
            </div>
            <ul class="slatArea"  ng-repeat="marketingProgramList in marketingProgramLists.slice().reverse()">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{marketingProgramList.marketingProgramName}}</span>   
                    </div>
                    
                    
                    <div class="listCol col1of2 fleft">
                        <a href="marketingprogramdetails.jsp?marketingProgramId={{marketingProgramList.marketingProgramId}}"><div class="CTA_Button Button--Gray fright">VIEW/EDIT</div></a>

                    </div>
                </li>                
            </ul>
        </div>
    </div>
  
    </body>
</html>