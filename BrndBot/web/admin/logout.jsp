<%-- 
    Document   : logout
    Created on : Jul 8, 2015, 2:24:19 PM
    Author     : intbit
--%>

<%@page import="admin.controller.ServletLogin"%>
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
            sqlmethods.admin_session = request.getSession(true);
            sqlmethods.admin_session.invalidate();
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
            
    %>
