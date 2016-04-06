<%-- 
    Document   : printsubcategorydetails
    Created on : Mar 14, 2016, 12:42:17 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <link rel="shortcut icon" href="../images/favicon.png"/>       
    <title>Sub Category</title>  
</head>    
<%
String organizationID=request.getParameter("organizationId");
String categoryID=request.getParameter("categoryId");
String subCategoryID=request.getParameter("subCategoryId");
%>
<body class="body-normal" ng-app  ng-controller="organizationcontroller">
     <input id="categoryIdTag" type="text" hidden value="<%=categoryID%>"/>
    <input id="organizationIdTag" type="text" hidden value="<%=organizationID%>"/>
    <input id="subCategoryIdTag" type="text" hidden value="<%=subCategoryID%>"/>
     <jsp:include page="adminheader.jsp"/>
     <jsp:include page="addemailtemplate.jsp"/>
     <div  ng-init="organizationdetails()">
    <div class="content-area" ng-init="getAllCategoryDetails()">
        <div class="content-area_header" ng-init="getAllSubCategories()">
            <div class="header_path fleft"> <a style="text-decoration:none;color:#3E4551;" href="organizationdetails.jsp?organizationId=<%=organizationID%>">{{organizationDetails.organizationName}}</a>  > </div>
            <div class="header_path fleft"> <a style="text-decoration:none;color:#3E4551;" href="printsubcategory.jsp?organizationId=<%=organizationID%>&categoryId=<%=categoryID%>">{{categoryDetails.categoryName}}</a>  > </div>
            <div class="header_title fleft">{{subCategoryDetailsTitle.subCategoryName}}</div>
            <div class="CTA_Button Button--Blue fright" id="addTemplateButton" ng-click="getAllNonAddedPrintModelsBySubCategoryId(<%=subCategoryID%>)">Add Template</div>
            <div class="CTA_Button Button--Delete fright" ng-click="deleteSubCategory(<%=subCategoryID%>)">Delete Sub Category</div>
        </div>
        <div class="slatSection" ng-init="getPrintModelBySubCategoryId()">
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Template Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="printModel in printModels">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{printModel.printModelName}}</span>
                    </div>
                    <div class="listCol col1of4 fright">
                        <div class="CTA_Button Button--Gray fright">Remove Template</div>
                    </div>
                </li>
                <li class="listItem" ng-show="printModels==null">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">No Print Templates exist.</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>
     </div>
  
</body>