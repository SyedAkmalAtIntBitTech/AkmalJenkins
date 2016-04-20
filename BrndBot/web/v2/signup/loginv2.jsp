<%-- 
    Document   : login
    Created on : Apr 6, 2016, 5:49:40 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <jsp:include page="header.jsp"/>
    </head>
    <body class="body-normal" ng-app ng-controller="onboardingcontroller">
    <div class="Login_left-pane fleft">
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
            <img src="../users/images/BB_regular.svg" class="login-logo" style="cursor:pointer;"> 
            <div class="input_Label pushUp_30">Email</div>
            <input id="emailIdLogin" class="input_Field login-fields" placeholder="Email"/>
            <div class="input_Label">Password</div>
            <input id="loginPassword" class="input_Field login-fields" placeholder="Password"/>
            <div class="forgot_password">
                <span>Forgot Password</span>
            </div>
            <a href="#"><div class="CTA_Button Button--Blue fleft pushUp_30" ng-click="login()">Log In</div></a>
            <div class="sign-up_container"></div>
        </div>
    </div>
</body>
</html>
