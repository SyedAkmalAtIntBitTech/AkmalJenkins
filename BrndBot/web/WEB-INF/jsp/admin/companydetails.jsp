<%-- 
    Document   : companydetails
    Created on : Mar 2, 2016, 6:13:57 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head> 
        <link rel="shortcut icon" href="images/favicon.png"/>       
        <title>Company details</title>
    </head>
    <body class="body-normal">
     
         <jsp:include page="header.jsp"/>
          <jsp:include page="addgroup.jsp"/>
         <% String companyName= request.getParameter("companyName"); %>
          <% String companyId= request.getParameter("companyId"); %>
           <% String organizationName= request.getParameter("organizationName"); %>
        <input id="companyId" hidden value="<%=companyId %>"/>
        <input id="organizationName" hidden value="<%=organizationName %>"/>
        <div class="content-area" ng-app  ng-controller="allCompaniesController">         
        <div class="content-area_header">
               <input hidden id="organizationId" value="{{organizationId.organizationId}}"></input>
            <div class="header_path fleft"><a style="text-decoration:none;color:#3E4551;" href="${pageContext.request.contextPath}/admin/allcompanies">  All Companies > </a></div>
            <div id="companyName" class="header_title fleft"><%=companyName %> </div>
        </div>
        <div class="subnav">
            <div class="subnav-tab fleft">Overview and Brand</div>
            <div class="subnav-tab fleft">Templates</div>
        </div>
<!--        <div class="inputSection col1of4" ng-init="organizationdetails()">
            <div class="input_Label">Select an Organzation</div>
            <select class="input_Field" id="organizationGroupId">
                    <option class="input_Field" value="2">organization</option>
                    <option class="input_Field" value="1">group</option>
                </select>
            <div ng-click="updateCompanyOrganization()" class="CTA_Button Button--Gray fleft pushUp_10">Update</div>
        </div>-->
        
        <div class="input_Label pushUp_30">User Groups</div>
        <ul class="groupArea" ng-init="userGroups()">
            <li class="listItem" ng-repeat="groupNames in groupName.slice().reverse()">
                <div class="listCol col1of2 fleft">
                    <span class="listCol_Text fleft"> {{groupNames.organizationName}}</span>
                    <input id="organizationCompanyLookupId"  hidden value="{{groupNames.organizationCompanyLookupId}}">
                    
                </div>
                <div class="listCol col1of2 fleft">
                    <div ng-click="deleteGroup()" class="CTA_Button Button--Gray--text fright">Remove Group</div>
                </div>
            </li>
               <div id="addCompanyGroup" class="Add_Button Button--Blue fleft pushUp_10">Add Groups</div>
        </ul>
        <div class="slatSection" ng-init="companyEmailcategories()">
            <div class="sectionHeader"> Email Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            <ul class="slatArea" ng-repeat="channelDetails in emailCategoryNameList">
                <li class="listItem" >
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.categoryName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/emailsubcategory?organizationId={{channelDetails.organizationId}}&categoryId={{channelDetails.categoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            
        </div>
        <div>
        <div class="slatSection" ng-init="emailBlockList()">
            <div class="sectionHeader"> Email Blocks </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> External Source </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            
            <ul class="slatArea" ng-repeat="emailBlock in emailBlocks.slice().reverse()">
                <li class="listItem">
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> {{emailBlock.emailBlockName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> {{emailBlock.externalSourceName}}-{{emailBlock.externalSourceKeywordName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> {{organizationNames.organizationName}}</span>
                    </div>
                    
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/emailblock?organizationId={{emailBlock.organizationId}}&emailBlockId={{emailBlock.emailBlockId}}">  <div class="CTA_Button Button--Gray fright">Manage Block</div></a>
                    </div>
                </li>
                
               
            </ul>
           
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Image Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            <ul class="slatArea" ng-repeat="channelDetails in imageCategoryNameList.slice().reverse()">
                <li class="listItem" >
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.categoryName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/emailsubcategory?organizationId={{channelDetails.organizationId}}&categoryId={{channelDetails.categoryId}}"> <div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
           
        </div>
        <div class="slatSection">
            <div class="sectionHeader"> Print Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            <ul class="slatArea" ng-repeat="channelDetails in printCategoryNameList.slice().reverse()">
                <li class="listItem" >
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.categoryName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft">{{channelDetails.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/emailsubcategory?organizationId={{channelDetails.organizationId}}&categoryId={{channelDetails.categoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
            
            
        </div>
        </div>
        <div class="slatSection" ng-init="marketingCategory()">
            <div class="sectionHeader"> Marketing Program Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Block Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            <ul class="slatArea" >
                <li class="listItem" ng-repeat="marketingCategoryList in marketingCategoryLists.slice().reverse()">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> {{marketingCategoryList.marketingCategoryName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft" >
                        <span class="listCol_Text fleft "> {{organizationNames.organizationName}}</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <a href="${pageContext.request.contextPath}/admin/marketingcategory?organizationId={{marketingCategoryList.organizationId}}&marketingCategoryId={{marketingCategoryList.marketingCategoryId}}"><div class="CTA_Button Button--Gray fright">Manage Category</div></a>
                    </div>
                </li>
            </ul>
          
        </div>
<!--        <div class="slatSection">
            <div class="sectionHeader"> Recurring Email Template Categories </div>
            <div class="slatHeaders">
                <div class="listHeaderCol col1of2 fleft">
                    <span class="listCol_Header fleft"> Category Names </span>
                </div>
                <div class="listHeaderCol col1of4 fleft">
                    <span class="listCol_Header fleft"> Source </span>
                </div>
            </div>
            <ul class="slatArea">
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Category Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Organization Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
                <li class="listItem">
                    <div class="listCol col1of2 fleft">
                        <span class="listCol_Text fleft"> Block Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <span class="listCol_Text fleft"> Group Name</span>
                    </div>
                    <div class="listCol col1of4 fleft">
                        <div class="CTA_Button Button--Gray fright">Manage Category</div>
                    </div>
                </li>
            </ul>
           
        </div>-->
    </div>
    </body>
</html>
