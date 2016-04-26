<%-- 
    Document   : signup
    Created on : Apr 5, 2016, 11:51:46 AM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>BrndBot - SignUp</title>
        <jsp:include page="header.jsp"/>
    </head>
    <body class="body-normal" ng-app ng-controller="onboardingcontroller">
    <div class="Login_left-pane fleft">
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
            <img src="images/BB-Hello.svg" class="signUp-logo" style="cursor:pointer;">
            <div class="input_Label pushUp_30">Email</div>
            <input class="input_Field login-fields" id="emailId" placeholder="Email"  ng-blur="isUserUnique()"/>
            <div class="input_Label">Password</div>
            <input class="input_Field login-fields" id="password" type="password" placeholder="Password"/>
             <div class="input_Label">Confirm Password</div>
            <input class="input_Field login-fields" id="rePassword" type="password" placeholder="Confirm Password"/>
            
            <div class="CTA_Button Button--Blue fleft pushUp_30" ng-click="saveUser()">Sign Up</div>
            <div class="sign-up_container"></div>
            
             
        </div>
    </div>
</body>
</html>
