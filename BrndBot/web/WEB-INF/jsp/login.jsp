<%-- 
    Document   : login
    Created on : Apr 6, 2016, 5:49:40 PM
    Author     : development
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Login</title>
       <jsp:include page="header.jsp"/>
        
    </head>
    <script>
         $("document").ready(function() {
             if(window.location.href.indexOf("signout.jsp") > -1) {
                window.location = getHost() +"login.jsp";
                
                alert(logoutmsg);
             }
        setTimeout(function(){ $("#inputemail").focus();},10);});  
        </script>
    <body class="body-normal" ng-app >
        <form class="form-horizontal" id="signform" ng-controller="loginController" ng-submit="checkUser()" >
    <div class="Login_left-pane fleft">
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
            <img src="images/BB_regular.svg" class="login-logo" style="cursor:pointer;"> 
            <div class="input_Label pushUp_30">Email</div>
            <input id="emailIdLogin" class="input_Field login-fields" type="email" required ng-model="user.emailid" >
            <div class="input_Label">Password</div>
            <input id="loginPassword" class="input_Field login-fields" type="password" required ng-model="user.password">
            <div class="forgot_password">
               <a href="resetpassword"><span>Forgot Password</span></a>
            </div>
            <button style="left:-10px;" type="submit"  class="CTA_Button Button--Blue fleft pushUp_30">Login</button>
            <div class="sign-up_container"></div>
        </div>
    </div>
             </form>
</body>
</html>
