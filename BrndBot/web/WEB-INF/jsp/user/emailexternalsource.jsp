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
        %>
    </head>
    <body class="body-normal">
        <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>    
       <div class="contentWrap--withSideNav noScroll">
           <input id="subCategoryIdTag" type="text" hidden="" value="<%=subCategoryId%>"/>
           <input id="categoryIdTag" type="text" hidden="" value="<%=categoryId%>"/>
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">
                 
                 <a class="exit-button-icon" href="emailsubcategory">
          
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
                    <div class="pane_header_CTABOX fright">
                        <a href="emaileditor"><div class="CTA_Button Button--Gray">Use Default</div></a>
                    </div>
                </div>
                
                <div class="pane_subheader clear">
                    <a href="#" class="pane_subheader-tabs-active fleft">From MINDBODY</a>
                    <a href="marketcampaigndata" class="pane_subheader-tabs fleft">From Marketing Campaigns</a>
                </div>
                
                <div class="pane_content">
                    <div class="subcat-list">
                       <a href="emaileditor?categoryId=<%=categoryId%>&subCategoryId=<%=subCategoryId%>"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">External Data Name</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft">Time</div>
                            <div class="cat-slat-description col-3of10 fleft">Teacher Name</div>
                        </div> </a>
                        <a href="emaileditor?subCategoryId=<%=subCategoryId%>"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">External Data Name</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft">Time</div>
                            <div class="cat-slat-description col-3of10 fleft">Teacher Name</div>
                        </div> </a>
                        <a href="emaileditor?subCategoryId=<%=subCategoryId%>"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">External Data Name</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft">Time</div>
                            <div class="cat-slat-description col-3of10 fleft">Teacher Name</div>
                        </div> </a>
                        <a href="emaileditor"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">External Data Name</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft">Time</div>
                            <div class="cat-slat-description col-3of10 fleft">Teacher Name</div>
                        </div> </a>
                        <a href="emaileditor"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">External Data Name</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft">Time</div>
                            <div class="cat-slat-description col-3of10 fleft">Teacher Name</div>
                        </div> </a>
                      
                    </div>
                </div>
            </div>   
        </div>
    </div>
    </body>
</html>
