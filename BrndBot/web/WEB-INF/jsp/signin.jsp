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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/tempalert.js"></script>
        <link rel="stylesheet" href="css/tempcss.css">
        <title>User Login</title>
    </head>
    <body>
        <center>
            <img src="images/logo.png"  width="100" height="150"/>
            </br>
            </br>
            </br>
            </br>
            <h1>Please Enter Credential Here</h1>
            </br>
            </br>  
            <form action="${pageContext.request.contextPath}/login" method="POST" >
                <table bgcolor="#345690" width="300" height="200">
                    <tr>
                        <td>User ID  :</td>
                        <td><input type="email" name="username"></td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="login"></td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
            </form>
        </center>
    </body>
</html>
