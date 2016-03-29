<%-- 
    Document   : emailblock
    Created on : Mar 14, 2016, 5:03:28 PM
    Author     : development
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <link rel="shortcut icon" href="../images/favicon.png"/>       
    <title>Email Block</title>  
</head>    
<%
String organizationID=request.getParameter("organizationId");
String emailBlockId=request.getParameter("emailBlockId");
%>
<body class="body-normal" ng-app  ng-controller="organizationcontroller" >
    <input id="emailBlockId" type="text" hidden value="<%=emailBlockId%>"/>
    <input id="organizationIdTag" type="text" hidden value="<%=organizationID%>"/>
    <jsp:include page="adminheader.jsp"/>
    <div class="content-area" ng-init="organizationdetails()">
        <div class="content-area_header"  ng-init="getEmailBlock()">
            <div  class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="organizationdetails.jsp?organizationId=<%=organizationID%>">{{organizationDetails.organizationName}}</a>  > </div>
            <div class="header_title fleft">{{emailBlocksTitle.emailBlockName}}  </div>
            <div class="CTA_Button Button--Blue fright" id="addTemplateButton" ng-click="">Add Template</div>
            <div id="deleteCategoryButton" class="CTA_Button Button--Delete fright" ng-click="deleteEmailBlock(<%=emailBlockId%>)">Delete Block</div>
            
        </div>
        <div class="slatSection" >
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">  Template Names</span>
                </div>
            </div>
            <ul class="slatArea" ng-init="getAllNonAddedEmailBlockModel()">
                <li class="listItem" ng-repeat="nonAddedemailBlockDetail in nonAddedemailBlockDetails">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{nonAddedemailBlockDetail.emailBlockModelName}}</span>
                    </div>
                    <div class="listCol col1of4 fright">
                        <div class="CTA_Button Button--Gray fright">Remove Template</div>
                    </div>
                    
                </li>
            </ul>
            
        </div>
        
    </div>
  
</body>