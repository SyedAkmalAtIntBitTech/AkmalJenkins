<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login</title>
    </head>
    <body>
        <center>
            <h1>${greeting}</h1> 
            </br>
            </br>
            <h1>Please Enter Credential Here</h1>
            </br>
            </br>
            </br>
            </br>
       
            <form action="${pageContext.request.contextPath}/login" method="POST" >
                <table bgcolor="#345690" width="300" height="200">
                    <tr>
                        <td>User ID  :</td>
                        <td><input type="text" name="username"></td>
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