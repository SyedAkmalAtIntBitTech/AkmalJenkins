<%-- 
    Document   : onboarding4logo
    Created on : Mar 31, 2016, 8:17:10 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <jsp:include page="hearderfiles.jsp"/>
    </head>
 <body class="body-normal" ng-app ng-controller="onboardingcontroller">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <a href="onboarding3.jsp">
                <div class="topNav--BackButton fleft">
                <img  src="../images/userimages/backbutton.svg" class="backButton-svg" style="cursor:pointer;"></img>
                </div>
            </a>
            <div class="topNav--TitleBar--withBackButton fleft">
                <span class="topNav--TitleBar--Title fleft h2">Step 3 of 4</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <div class="CTA_Button Button--Gray">Help!</div>
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Upload your logo</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Upload a hi-res version of your logo. A .PNG format works the best, but you may use a .JPEG as well.</div>
                    <div class="logoUpload-wrap">
                           <img src="../images/userimages/Onboarding/uploadPhoto.svg" class="logoUpload-svg" style="cursor:pointer;"/>
                    </div>
                    <a href="onboardinglogouploaded.jsp">
                        <div class="Add_Button Button--Gray logoUpload--button">Upload Logo</div>
                    </a>
                    </div>
                </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <a href="onboardinglogouploaded.jsp">
            <div class="bottom-ContinueButton fright">CONTINUE</div>
        </a>
    </div>
    
    
    
    
</body>
</html>
