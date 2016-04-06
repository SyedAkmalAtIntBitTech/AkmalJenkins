<%-- 
    Document   : marketcampaigndata
    Created on : Apr 6, 2016, 6:09:21 PM
    Author     : Rasim Parvez at IntBit Technologies.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/favicon.png">
        <title>Market Campaign Data</title>
    </head>
    <body class="body-normal">
         <%@include file="header.jsp" %>       
      <%@include file="navbar.jsp" %>    
      <div class="contentWrap--withSideNav noScroll">
        <div class="topNav topNav-withSubnav clear">
             <div class="topNav--BackButton fleft">
                <object type="image/svg+xml" data="/Final-Icons/BackButton.svg" class="backButton-svg" style="cursor:pointer;"> </object>
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
                        <a href="emailsequence.jsp"><div class="CTA_Button Button--Gray">Use Default</div></a>
                    </div>
                </div>
                
                <div class="pane_subheader clear">
                    <a href="emailexternalsource.jsp" class="pane_subheader-tabs fleft">From MINDBODY</a>
                    <a href="marketcampaigndata.jsp" class="pane_subheader-tabs-active fleft">From Marketing Campaigns</a>
                </div>
                
                <div class="pane_content">
                    <div class="subcat-list">
                       <a href="emailsequence.jsp"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">Marketing Campaign</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft"></div>
                            <div class="cat-slat-description col-3of10 fleft"></div>
                        </div> </a>
                        <a href="/Email_Sequence/EmailSequence-Editor.html"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">Marketing Campaign</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft"></div>
                            <div class="cat-slat-description col-3of10 fleft"></div>
                        </div> </a>
                        <a href="/Email_Sequence/EmailSequence-Editor.html"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">Marketing Campaign</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft"></div>
                            <div class="cat-slat-description col-3of10 fleft"></div>
                        </div> </a>
                        <a href="/Email_Sequence/EmailSequence-Editor.html"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">Marketing Campaign</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft"></div>
                            <div class="cat-slat-description col-3of10 fleft"></div>
                        </div> </a>
                        <a href="/Email_Sequence/EmailSequence-Editor.html"><div class="cat-slat clear">
                            <div class="cat-slat-title col-3of10 fleft">Marketing Campaign</div>
                            <div class="cat-slat-description col-2of10 fleft">Date</div>
                             <div class="cat-slat-description col-2of10 fleft"></div>
                            <div class="cat-slat-description col-3of10 fleft"></div>
                        </div> </a>
                        
                      
                    </div>
                </div>
            </div>   
        </div>
    </div>
    
    </body>
</html>
