<%-- 
    Document   : emailsubcategory
    Created on : Mar 9, 2016, 3:24:23 PM
    Author     : development
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <link rel="shortcut icon" href="images/favicon.png"/>       
    <title>Sub Category</title>  
</head>    
<body class="body-normal" ng-app  ng-controller="organizationcontroller" >
    <jsp:include page="header.jsp"/>
    <jsp:include page="addsubcategory.jsp"/>
    <div class="content-area" ng-init="organizationdetails()">
        <div class="content-area_header" ng-init="getAllCategoryDetails()">
            <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="${pageContext.request.contextPath}/admin/organizationdetails">{{organizationDetails.organizationName}}</a>  > </div>
            <div class="header_title fleft" id="categoryNameDiv">{{categoryDetails.categoryName}}</div>
            <div id="deleteCategoryButton" class="CTA_Button Button--Delete fright" ng-click="deleteCategory()">Delete Category</div>
        </div>
        <div class="slatSection"  ng-init="getAllSubCategories()">
            <div class="sectionHeader"> SubCategory </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">  SubCategory Name</span>
                </div>
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft">  External Value</span>
                </div>
            </div>
            <ul class="slatArea" >
                <li class="listItem" ng-repeat="subcategory in subCategoryDetails">
                    <div class="listCol col1of2 fleft" >
                        <span class="listCol_Text fleft">{{subcategory.subCategoryName}}</span>
                    </div>
                     <div class="listCol col1of4 fleft"  ng-init="getAllExternalSourceKeywordLookups()">
                        <span class="listCol_Text fleft">{{ExternalSourceKeywordLookups.externalSourceName}}-{{ExternalSourceKeywordLookups.externalSourceKeywordName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/emailsubcategorydetails" ng-click="setSubCategoryDetails(organizationDetails.organizationId,categoryDetails.categoryId,subcategory.subCategoryId)"><div class="CTA_Button Button--Gray fright">Manage Sub Category</div></a>
                    </div>
                </li>
            </ul>
            <div id="addSubCategory" class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        
    </div>
  
</body>