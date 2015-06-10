<%-- 
    Document   : Login
    Created on : May 20, 2015, 8:14:00 PM
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
        <form name="frmLogin" method="post" action="<%=application.getContextPath()%>/authentication">
            User Name: <input type="text" name="txtUserID" placeholder="User_id"/><br>
            Password : <input type="password" name="txtPassword" /><br>
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>
