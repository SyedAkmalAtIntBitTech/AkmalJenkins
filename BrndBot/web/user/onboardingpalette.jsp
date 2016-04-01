<%-- 
    Document   : onboardingpalette
    Created on : Mar 31, 2016, 8:28:31 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <jsp:include page="hearderfiles.jsp"/>
    </head>
<body class="body-normal">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <a href="onboardinglogouploaded.jsp">
                <div class="topNav--BackButton fleft">
                <img  src="../images/userimages/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
                </div>
            </a>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 5 of 5</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane palette-pane">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Choose your color palette</div>
                </div>
                <div class="pane_content clear">
                    <div class="pane-palette_left fleft">
                        <div class="h3 font--lightGray">BrndBot syncs with your other tools. If you are not using any of these services, just select none!</div>
                         <div class="h4 font--lightGray onboarding-subtext">BrndBot syncs with your other tools. If you are not using any of these services, just select none!</div>
                        <div class="palette-wrap">
                            <div class="palette clear">
                                <div class="palette-colorswab fleft"></div>
                                 <div class="palette-colorswab fleft"></div>
                                 <div class="palette-colorswab fleft"></div>
                                 <div class="palette-colorswab fleft"></div>
                            </div>
                        </div>
                    </div>
                    <div class="pane-palette_right fright">
                        <div class="colorOptions-bar clear">
                            <div class="colorOptions-tab col-1of3 fleft">Custom Colors</div>
                            <div class="colorOptions-tab-active col-1of3 fleft">Choose from Theme</div>
                            <div class="colorOptions-tab col-1of3 fleft">Choose from Logo</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>   
    </div>
    <div class="bottomNav">
        <a href="Dashboard.html">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    
            
    </div>
    
    
    
    
</body>
</html>
