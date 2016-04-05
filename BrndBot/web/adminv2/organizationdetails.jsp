<%-- 
    Document   : organisationmain
    Created on : Mar 2, 2016, 2:08:49 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="shortcut icon" href="../images/favicon.png"/>        
    <title>Organization-Details</title>       
</head>    
    <%
    String organizationId=request.getParameter("organizationId");

%>
<body class="body-normal">
    <input id="organizationIdTag" type="text" hidden value="<%=organizationId%>"/>
    <jsp:include page="adminheader.jsp"/>
     <jsp:include page="emailcategory.jsp"/>
     <jsp:include page="printcategory.jsp"/>
     <jsp:include page="addimagecategory.jsp"/>
     <jsp:include page="addemailblock.jsp"/>
     <jsp:include page="addrecurringtemplatepopup.jsp"/>
      <jsp:include page="marketingprogrampopup.jsp"/>
      
     <input type="text" id='organizationId' value="<%=organizationId%>" hidden/>
    <div class="content-area" ng-app  ng-controller="organizationcontroller" >
        <div class="content-area_header" ng-init="organizationdetails()">
            <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="organization.jsp"> Organizations</a>  > </div>

            <div class="header_title fleft" id="organizationNameDiv">{{organizationDetails.organizationName}}</div>
            <div id="deleteOrganization" class="CTA_Button Button--Delete fright" ng-click="deleteOrganization(<%=organizationId%>)">Delete Organization</div>

        </div>
        <div class="inputSection col1of4" >
            <div class="input_Label">Is this an organization or a group?</div>
                <select class="input_Field" id="organizationDetailsTypeId">
                    <option class="input_Field" value="2">organization</option>
                    <option class="input_Field" value="1">group</option>
                </select>
            <div id="updateOrganization" class="CTA_Button Button--Gray fleft pushUp_10" ng-click="updateOrganization()">Update</div>
        </div>
        <div class="slatSection" ng-init="emailcategories()">
            <div class="sectionHeader"> Email Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea" >
                <li class="listItem" ng-repeat="emails in emailDetails.slice().reverse()">
                    <div class="listCol col1of2 fleft" >
                        <span class="listCol_Text fleft" >{{emails.categoryName}}</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <a href="emailsubcategory.jsp?organizationId=<%=organizationId%>&categoryId={{emails.categoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            <div id="addOrganization" class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection" ng-init="getAllEmailBlocks()">
            <div class="sectionHeader"> Email Blocks </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> External Value </span>
                </div>
            </div>
            <ul class="slatArea" ng-repeat="blocks in emailBlocks.slice().reverse()">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> {{blocks.emailBlockName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> {{blocks.externalSourceName}}{{blocks.externalSourceKeywordName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                       <a href="emailblock.jsp?organizationId=<%=organizationId%>&emailBlockId={{blocks.emailBlockId}}"><div class="CTA_Button Button--Gray fright">Manage Block </div></a>
                    </div>
                </li>
                
                
            </ul>
            <div id="addEmailBlock" class="Add_Button Button--Blue fleft pushUp_10">Add Block</div>
        </div>
        <div class="slatSection" >
            <div class="sectionHeader"> Image Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="image in imageDetails.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{image.categoryName}}</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <a href="imagesubcategory.jsp?organizationId=<%=organizationId%>&categoryId={{image.categoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            <div id="addCategoryImage" class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Print Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="print in printDetails.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> {{print.categoryName}}</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <a href="printsubcategory.jsp?organizationId=<%=organizationId%>&categoryId={{print.categoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            <div id='addCategoryPrint' class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Marketing Program Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea"   ng-init="getAllMarketingCategory()">
                <li class="listItem" ng-repeat="marketingCategory in marketingCategories.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> {{marketingCategory.marketingCategoryName}}</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <a href="marketingcategory.jsp?organizationId=<%=organizationId%>&marketingCategoryId={{marketingCategory.marketingCategoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            <div Id="addMarketingCategory" class="Add_Button Button--Blue fleft pushUp_10">Add Category</div>
        </div>
        <div class="slatSection" ng-init="getAllRecurringByOrganizationId()">
            <div class="sectionHeader"> Recurring Email Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem" ng-repeat="recurringEmail in allRecurringEmails">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{recurringEmail.templateName}}</span>
                    </div>
                    <div class="listCol col1of2 fleft">
                        <div class="CTA_Button Button--Gray fright" ng-click="deleteOrganizationRecurringEmail(recurringEmail.organizationRecurringEmailLookupId)">Remove Template</div>
                    </div>
                </li>
            </ul>
            <div id="addRecurringEmailTemplateButton" class="Add_Button Button--Blue fleft pushUp_10" ng-click="getAllNonAddedRecurringEmail()">Add Category</div>
        </div>
    </div>
  
</body>