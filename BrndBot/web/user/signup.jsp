<%-- 
    Document   : signup
    Created on : Apr 5, 2016, 11:51:46 AM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="hearderfiles.jsp"/>  
    </head>
    <body class="body-normal" ng-app ng-controller="onboardingcontroller">
        <div class="contentWrap--withNoSideNav">
        <div class="topNav clear">
            <div class="topNav--TitleBar topNav--TitleBar1 fleft">
                <span class="topNav--TitleBar--Title fleft h2">Welcome to BrndBot!</span>
                <div class="topNav--TitleBar--CTABox fright">
                    <!--<div class="CTA_Button Button--Gray" >Help!</div>-->
                </div>
            </div>
        </div>
        <div class="topNav--offset"></div>
        <div class="contentWrapInner">
            <div class="pane pane-onboarding">
                <div class="pane_header clear">
                    <div class="pane_title fleft h2">Sign Up</div>
                </div>
                <div class="pane_content">
                    <div class="inputSection col-6of10">
                        <div class="input_Label">Enter your email</div>
                        <input class="input_Field" id="emailId" placeholder="youremail@any.com"/>
                    </div>
                    <div class="inputSection col-6of10 ">
                        <div class="input_Label">Enter your password</div>
                        <input class="input_Field" id="password" type="password" placeholder="***"/>
                    </div>
                    <div class="inputSection col-6of10 ">
                        <div class="input_Label">Re Enter your password</div>
                        <input class="input_Field" id="rePassword" type="password" placeholder="***"/>
                    </div>
                    </div>
            </div>   
        </div>
    </div>
    <div class="bottomNav">
        <!--<a href="onboarding2.jsp">-->
            <div class="bottom-ContinueButton fright" ng-click="signupSuccess()">CREATE MY ACCOUNT</div>
        <!--</a>-->
    </div>
    </body>
</html>
