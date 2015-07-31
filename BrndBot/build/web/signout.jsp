<%-- 
    Document   : signout
    Created on : Jun 18, 2015, 1:06:00 PM
    Author     : intbit
--%>

<%@page import="com.controller.SqlMethods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    HttpServletRequest request;
    HttpServletResponse response;
    SqlMethods sqlmethods = new SqlMethods();
    RequestDispatcher request_dispatcher;
    %>
    
    <%
        try{
            sqlmethods.session = request.getSession(true);
            sqlmethods.session.invalidate();
            request_dispatcher = request.getRequestDispatcher("/login.jsp");
            
            request_dispatcher.forward(request, response);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
            
    %>