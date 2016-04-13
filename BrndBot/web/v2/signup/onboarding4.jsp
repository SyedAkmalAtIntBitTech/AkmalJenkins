<%-- 
    Document   : onboarding4logo
    Created on : Mar 31, 2016, 8:17:10 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <jsp:include page="header.jsp"/>
    </head>
 <body class="body-normal" ng-app ng-controller="onboardingcontroller">
    
    <!--SideNav-->
    
    <!--Main Content Wrap-->
    <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <a href="#">
                <div class="topNav--BackButton fleft">
                <img  src="images/backbutton.svg" class="backButton-svg" style="cursor:pointer;">
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
        <div class="contentWrapInner" id="uploadLogoDiv">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Upload your logo</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Upload a hi-res version of your logo. A .PNG format works the best, but you may use a .JPEG as well.</div>
                    <div class="logoUpload-wrap">
                        <img src="images/uploadPhoto.svg" class="logoUpload-svg" style="cursor:pointer;"/>
                    </div>
                    <!--<a href="onboardinglogouploaded.jsp">-->
                    <div  class="Add_Button Button--Gray logoUpload--button "><label id="uploadLogoLabel" class=" Button--Gray" for="uploadLogo">Upload Logo</label></div>
                    <input hidden="" type="file" class="Add_Button Button--Gray logoUpload--button" accept="image/png, image/jpeg, image/jpg" id="uploadLogo" value="">
                    <!--</a>-->
                    </div>
                </div>
            </div> 
<!--        <div class="contentWrapInner"  id="uploadedLogoDiv">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Upload your logo</div>
                </div>
                <div class="pane_content">
                    <div class="h3 font--lightGray">Congratulations your logo has been successfully uploaded!  </div>
                    <div class="logo-Uploaded-wrap clear">
                        <img id="uploadedLogo" src="https://daks2k3a4ib2z.cloudfront.net/562feb3ef5fe5a8c1fd02272/56b8940985f55b8d6cc5f7d7_Deafult-Image_350x350.jpg" class="logo-Uploaded fleft">
                          <img  src="images/Valid-Check.svg" class="logoUpload-Success-svg fleft" style="cursor:pointer;"/>
                    </div>
                    <div class="Add_Button Button--Gray logoUpload--button" id="changeLogoButton">Change Logo</div>
                </div>
            </div>
        </div>-->
        </div>
    <div class="bottomNav">
        <!--<a href="onboardinglogouploaded.jsp">-->
        <div id="uploadLogoContinueButton" class="bottom-ContinueButton fright" ng-click="imageValid()">CONTINUE</div>
        <!--</a>-->
    </div>
    
    
    
    
</body>
</html>
