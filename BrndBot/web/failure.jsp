<%-- 
    Document   : failure
    Created on : May 20, 2015, 7:59:11 PM
    Author     : intbit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p style="color:#ff00c8;">Either the user name or the password is incorrect</p>
        <form name="frmLogin" method="post" action="">
            User Name: <input type="text" name="txtUserID" placeholder="User_id"/><br>
            Password : <input type="password" name="txtPassword" /><br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
