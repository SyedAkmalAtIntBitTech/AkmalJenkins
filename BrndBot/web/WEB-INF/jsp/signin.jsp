<%-- 
    Document   : login
    Created on : 12 Apr, 2016, 4:14:06 PM
    Author     : Haider Khan @ Intbit
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
         <title>User Login</title>
        <jsp:include page="header.jsp"/>
       <script>
         $("document").ready(function() {
             if(window.location.href.indexOf("signout") > -1) {
                window.location = getHost() +"login";
                alert(logoutmsg);
             }
             if(window.location.href.indexOf("accessdenied=true") > -1) {
                  $("div.alert-div").fadeIn(300).delay(2000);
                //alert(passwordMatchError);
             }
        setTimeout(function(){ $("#inputemail").focus();},10);});  
        </script>
    
    </head>
    <body class="body-normal">
        
             <form class="form-horizontal" id="signform" action="${pageContext.request.contextPath}/login" method="POST">
                 
    <div class="Login_left-pane fleft">
        <div class="Login_left-pane_vert-split"></div>
        <div class="Login_left-pane_vert-split"></div>
    </div>
    <div class="Login_right-pane fright">
        <div class="login-container">
             <div class="alert-div">The email or password that you entered don't match.</div>
            <img src="images/BB_regular.svg" class="login-logo" style="cursor:pointer;"> 
            <div class="input_Label pushUp_30">Email</div>
           
            <input name="username"  class="input_Field login-fields" type="email" required>
            <div class="input_Label">Password</div>
            <input  class="input_Field login-fields" name="password" type="password" required>
            <div class="forgot_password" style="float:left">
                <a href="resetpassword"><span>Forgot Password</span></a>
            </div>
            <div class="forgot_password" style="" >
                <a href="signup/registration"> <span style="position: relative;left: 10px;">Sign up</span></a>
            </div>
            
            <button style="left:-10px;" type="submit"  class="CTA_Button Button--Blue fleft pushUp_30">Login</button>
            <div class="sign-up_container"></div>
        </div>
    </div>
    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
             </form>
           
            
            
             
             
      
    </body>
</html>
