<%-- 
    Document   : emailexternalsource
    Created on : Apr 6, 2016, 4:01:51 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>External Source</title>
        <% 
            String subCategoryId=request.getParameter("subCategoryId");
            String categoryId=request.getParameter("categoryId");
            String LookupId=request.getParameter("LookupId");
            String externalSourceName=request.getParameter("externalSourceName");
        %>
    </head>
    <body class="body-normal" ng-app="mindbody">
       <%@include file="header.jsp" %>       
       <%@include file="navbar.jsp" %>    
       <div class="contentWrap--withSideNav noScroll" ng-controller="controllerGetMindBody" ng-init="showData(<%=LookupId%>)">
        <input id="subCategoryIdTag" type="text" hidden="" value="<%=subCategoryId%>"/>
        <input id="categoryIdTag" type="text" hidden="" value="<%=categoryId%>"/>
        <input id="externalSourceName" type="text" hidden="" value="<%=externalSourceName%>"/>
        <input id="LookupId" type="text" hidden="" value="<%=LookupId%>"/>
        <script src="js/mindbody.js" type="text/javascript"></script>
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">                 
                <a class="exit-button-icon" href="emailsubcategory?categoryId=<%=categoryId%>">          
                    <img type="image/svg+xml" src="images/backbutton.svg" class="exit-button-icon" style="cursor:pointer;">
                </a>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane--externalData">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Please select a workshop</div>
<!--                    <div class="pane_header_CTABOX fright">
                        <a href="emaileditor"><div class="CTA_Button Button--Gray">Use Default</div></a>
                    </div>-->
                </div>
                
<!--                <div class="pane_subheader clear">
                    <a href="#" class="pane_subheader-tabs-active fleft">From MINDBODY</a>
                    <a href="marketcampaigndata" class="pane_subheader-tabs fleft">From Marketing Campaigns</a>
                </div>-->
                
                <div class="pane_content">
                    <div class="subcat-list">
                        <a ng-repeat="mindbody in mindbodylist" href="emailsubjects?categoryId=<%=categoryId%>&subCategoryId=<%=subCategoryId%>&mindbodyId={{mindbody.id}}">
                            <div class="cat-slat clear">
                                <div class="cat-slat-title col-3of10 fleft">{{mindbody.column1}}</div>
                                <div class="cat-slat-description col-3of10 fleft">{{mindbody.column3}}</div>
                                <div class="cat-slat-description col-3of10 fleft">{{mindbody.column2}}</div>   
                            </div> 
                        </a>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    </body>
</html>
