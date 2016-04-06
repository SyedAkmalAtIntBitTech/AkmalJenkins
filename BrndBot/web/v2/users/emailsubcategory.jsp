<%-- 
    Document   : subcategory
    Created on : Apr 6, 2016, 3:44:27 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Subcategory</title>
    </head>
    <body class="body-normal">
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>       
    <!--Main Content Wrap-->
    <div class="contentWrap--withSideNav noScroll">
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">
                <object type="image/svg+xml" data="/Final-Icons/BackButton.svg" class="backButton-svg" style="cursor:pointer;"> </object>
            </div>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Create an Email</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
           <!-- <div class="pane pane--catSelected pane--600px">
                <div class="cat-slat-title-selected">Category Name Here</div>  
            </div>-->
            <div class="pane pane--600px">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Please select a subcategory</div>
                </div>
                <div class="pane_content">
                    <div class="subcat-list">
                        <div class="cat-slat">
                            <a href="emailexternalsource.jsp">
                                <div class="cat-slat-title">Sub-Category Name Here</div>
                            </a>
                        </div>
                        <div class="cat-slat">
                            <a href="emailEditor.html">
                                <div class="cat-slat-title">Sub-Category Name Here</div>
                            </a>
                        </div>
                        <div class="cat-slat">
                            <div class="cat-slat-title">Sub-Category Name Here</div>
                        </div>
                        <div class="cat-slat">
                            <div class="cat-slat-title">Sub-Category Name Here</div>
                        </div>
                         <div class="cat-slat">
                            <div class="cat-slat-title">Sub-Category Name Here</div>
                        </div>
                         <div class="cat-slat">
                            <div class="cat-slat-title">Sub-Category Name Here</div>
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </div>
  
</body>
</html>
