<%-- 
    Document   : emailsubcategory
    Created on : Mar 9, 2016, 3:24:23 PM
    Author     : development
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script src="../js/configurations.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="../images/favicon.png"/>        
    <script src="adminjs/organization.js" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <title>Organization-Details</title>   
    
</head>    
<%
String orgID=request.getParameter("orgId");
%>
<body class="body-normal">
    <input id="orgidtag" type="text" hidden value="<%=orgID%>"/>
    <jsp:include page="adminheader.jsp"/>
    <div class="content-area" ng-app  ng-controller="organizationcontroller" >
        <div class="content-area_header" ng-init="organizationdetails()">
            <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="organizationdetails.jsp?orgId=<%=orgID%>">{{organizationDetails.organizationName}}</a>  > </div>
            <div class="header_title fleft" id="orgnamediv">Category name</div>
            <div class="CTA_Button Button--Delete fright" ng-click="deletecategory(<%=orgID%>)">Delete Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> SubCategory </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">  SubCategory Name</span>
                </div>
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">  External Value</span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                     <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">None</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
            <div class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        
    </div>
  
</body>